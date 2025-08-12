package kr.co.koscom.olympus.pb.test.text;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/pb/test")
public class TestController {

    @PostMapping(value = "/test1"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody TestVo test1(@RequestBody TestVo o) {
        return o;
    }

    @PostMapping(value = "/test2"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody ResponseEntity<TestVo> test2(@RequestBody TestVo o) {
        ResponseEntity<TestVo> response = ResponseEntity.noContent().cacheControl(CacheControl.noCache()).build();
        return ResponseEntity.ok().header("Content-Type", MediaType.APPLICATION_JSON_VALUE).body(o);
    }
}
