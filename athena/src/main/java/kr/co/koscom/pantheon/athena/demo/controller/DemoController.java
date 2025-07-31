package kr.co.koscom.pantheon.athena.demo.controller;

import kr.co.koscom.pantheon.athena.demo.model.DemoDataIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller("/demo")
public class DemoController {

    @PostMapping(value = "/Hello")
    public @ResponseBody DemoDataIn hello(@RequestBody DemoDataIn in) {
        return in;
    }

    @PostMapping(value = "/HelloList")
    public @ResponseBody List<DemoDataIn> helloList(@RequestBody List<DemoDataIn> in) {
        return in;
    }

    @PostMapping(value = "/HelloArray")
    public @ResponseBody DemoDataIn[] helloArray(@RequestBody DemoDataIn[] in) {
        return in;
    }

    @PostMapping(value = "/HelloMap")
    public @ResponseBody Map<String, DemoDataIn> helloMap(@RequestBody Map<String, DemoDataIn> in) {
        return in;
    }
}
