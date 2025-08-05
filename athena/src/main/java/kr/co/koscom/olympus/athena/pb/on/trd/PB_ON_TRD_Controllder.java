package kr.co.koscom.olympus.athena.pb.on.trd;

import io.swagger.v3.core.util.Json;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.ByteBuffer;

@Controller("/pb/on")
public class PB_ON_TRD_Controllder {

    @PostMapping("/trd")
    public @ResponseBody byte[] trd(@RequestBody byte[] body, @RequestHeader HttpHeaders headers) {
        ByteBuffer bb = ByteBuffer.wrap(body);
        int sss = bb.getShort();
        return null;
    }
}
