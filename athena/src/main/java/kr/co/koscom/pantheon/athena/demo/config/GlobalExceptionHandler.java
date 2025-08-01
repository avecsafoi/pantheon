package kr.co.koscom.pantheon.athena.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        String acceptHeader = request.getHeader("Accept");

        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE)) {
            return handleJsonException(ex);
        } else if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            return handleXmlException(ex);
        } else {
            return handleHtmlException(ex);
        }
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("code", ex.getErrorCode());
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(ex.getErrorCode()));
    }

    private ModelAndView handleJsonException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ModelAndView("jsonView", errorResponse);
    }

    private ModelAndView handleXmlException(Exception ex) {
        // XML 처리 로직 (예: JAXB 사용)
        // 예시:
        // return new ModelAndView("xmlView", errorResponse);
        return new ModelAndView("xmlView", Map.of("error", ex.getMessage()));
    }

    private ModelAndView handleHtmlException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("/error"); // error.html 파일을 뷰로 사용
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }


    // Custom Exception class
    @Getter
    public static class CustomException extends RuntimeException {
        private final int errorCode;

        public CustomException(String message, int errorCode) {
            super(message);
            this.errorCode = errorCode;
        }
    }

//    /* 예시 1 */
//    @Controller
//    public static class JsonView {
//        @RequestMapping(value = "/jsonView", produces = MediaType.APPLICATION_JSON_VALUE)
//        @ResponseBody
//        public Map<String, String> jsonView(@ModelAttribute("error") String error) {
//            return Map.of("error", error);
//        }
//    }
//
//    /* 예시 2 */
//    @Controller
//    public static class XmlView {
//        @RequestMapping(value = "/xmlView", produces = MediaType.APPLICATION_XML_VALUE)
//        @ResponseBody
//        public Map<String, String> xmlView(@ModelAttribute("error") String error) {
//            return Map.of("error", error);
//        }
//    }

}