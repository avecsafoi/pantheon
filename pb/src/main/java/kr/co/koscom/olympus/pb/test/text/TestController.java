package kr.co.koscom.olympus.pb.test.text;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/pb/text")
public class TestController {

    @PostMapping(value = "t1"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody TestVo test1(@RequestBody TestVo o) {
        return o;
    }

    @PostMapping(value = "t2"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_XML_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public @ResponseBody ResponseEntity<TestVo> test2(@RequestBody TestVo o, Model model, HttpServletRequest request1, HttpRequest request2, HttpSession session1) throws IOException {
    public @ResponseBody ResponseEntity<TestVo> test2(@RequestBody TestVo o) throws IOException {
//        HttpSession session2 = request1.getSession();
//        boolean b1 = session1 == session2;
//        boolean b2 = request1 == request2;
//        System.out.printf("b1 = %s, b2 = %s %n", b1, b2);
//        request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<TestVo> response = ResponseEntity.noContent().cacheControl(CacheControl.noCache()).build();
        // return ResponseEntity.ok().header("Content-Type", MediaType.APPLICATION_XML_VALUE).body(o);
        return ResponseEntity.ok().body(o);
    }
}
