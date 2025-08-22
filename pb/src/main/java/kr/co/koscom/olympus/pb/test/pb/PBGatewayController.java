package kr.co.koscom.olympus.pb.test.pb;

import kr.co.koscom.olympus.pb.apa.hdr.PBDataWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pb/gw")
public class PBGatewayController {

    @PostMapping(value = "json"
            , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody PBDataWrapper json(@RequestBody PBDataWrapper js) throws Exception {
        js.processForJson();
        return js;
    }
}
