package kr.co.koscom.olympus.athena.demo.controller;

import kr.co.koscom.olympus.athena.demo.model.DemoDataIn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@Controller("/demo")
public class DemoController {

    @PostMapping(value = "/Hello"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody ResponseEntity<DemoDataIn> hello(@RequestBody DemoDataIn in) {
        return ResponseEntity.ok(in);
    }

    @PostMapping(value = "/HelloList"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody List<DemoDataIn> helloList(@RequestBody List<DemoDataIn> in) {
        return in;
    }

    @PostMapping(value = "/HelloArray"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody DemoDataIn[] helloArray(@RequestBody DemoDataIn[] in) {
        return in;
    }

    @PostMapping(value = "/HelloMap"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public @ResponseBody Map<String, DemoDataIn> helloMap(@RequestBody Map<String, DemoDataIn> in) {
        return in;
    }

    @PostMapping(value = "/HTS"
            , consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE}
            , produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE}
    )
    public @ResponseBody byte[] helloMap(@RequestBody byte[] in, @RequestHeader HttpHeaders headers) {
        ByteArrayInputStream bais = new ByteArrayInputStream(in);
        return in;
    }
}
