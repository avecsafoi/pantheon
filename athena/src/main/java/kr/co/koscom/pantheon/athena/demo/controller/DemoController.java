package kr.co.koscom.pantheon.athena.demo.controller;

import kr.co.koscom.pantheon.athena.demo.model.DemoDataIn;
import kr.co.koscom.pantheon.athena.demo.model.DemoDataOut;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/demo")
public class DemoController {
    @PostMapping(value = "/Hello"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody DemoDataOut hello(@RequestBody DemoDataIn in) {
        return new DemoDataOut().setId("아이디").setName("이름");
    }

    @PostMapping(value = "/HelloList"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody List<DemoDataOut> helloList(@RequestBody List<DemoDataIn> in) {
        DemoDataOut a = new DemoDataOut().setId("아이디").setName("이름");
        return List.of(a, a, a, a, a);
    }

    @PostMapping(value = "/HelloArray"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody DemoDataOut[] helloArray(@RequestBody DemoDataIn[] in) {
        DemoDataOut a = new DemoDataOut().setId("아이디").setName("이름");
        return new DemoDataOut[]{a, a, a, a, a};
    }

    @PostMapping(value = "/HelloMap"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody Map<String, DemoDataOut> helloMap(@RequestBody Map<String, DemoDataIn> in) {
        return new HashMap<>();
    }
}
