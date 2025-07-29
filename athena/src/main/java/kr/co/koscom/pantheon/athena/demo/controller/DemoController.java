package kr.co.koscom.pantheon.athena.demo.controller;

import kr.co.koscom.pantheon.athena.demo.model.DemoDataIn;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller("/demo")
public class DemoController {
    @PostMapping(value = "/Hello"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody DemoDataIn hello(@RequestBody DemoDataIn in) {
        return in;
    }

    @PostMapping(value = "/HelloList"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody List<DemoDataIn> helloList(@RequestBody List<DemoDataIn> in) {
        return in;
    }

    @PostMapping(value = "/HelloArray"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody DemoDataIn[] helloArray(@RequestBody DemoDataIn[] in) {
        return in;
    }

    @PostMapping(value = "/HelloMap"
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody Map<String, DemoDataIn> helloMap(@RequestBody Map<String, DemoDataIn> in) {
        return in;
    }
}
