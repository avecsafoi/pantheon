package kr.co.koscom.olympus.pb.test.aa;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/aa")
public class AAController {

    @Resource
    AAMapper mapper;

    @PostMapping("a1")
    public @ResponseBody List<AAD> a1(@RequestBody AAQ q) {
        return mapper.list(q);
    }
}
