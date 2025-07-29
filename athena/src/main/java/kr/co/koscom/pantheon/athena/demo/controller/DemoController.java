package kr.co.koscom.pantheon.athena.demo.controller;

import kr.co.koscom.pantheon.athena.demo.model.DemoDataIn;
import kr.co.koscom.pantheon.athena.demo.model.DemoDataOut;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/demo")
public class DemoController {
    @PostMapping(value = "/Hello"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody DemoDataOut hello(@RequestBody DemoDataIn in) {
        return new DemoDataOut().setId("아이디").setName("이름");
    }
}
