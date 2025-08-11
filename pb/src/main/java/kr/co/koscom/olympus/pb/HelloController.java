package kr.co.koscom.olympus.pb;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import kr.co.koscom.olympus.pb.include.table.ON_TBL_SPOT_ORD;
import kr.co.koscom.olympus.pb.include.table.ON_TBL_SPOT_ORD_Mapper;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("")
public class HelloController {

    @Resource
    private ON_TBL_SPOT_ORD_Mapper mapper;

    @GetMapping("/test")
    public @ResponseBody String test(@RequestBody String name) {
        return "Hello %s, it is the test for you".formatted(name);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello(Model model, HttpSession sess, HttpRequest rq) {

        Page<ON_TBL_SPOT_ORD> pg = (Page<ON_TBL_SPOT_ORD>) model.getAttribute("pg");
        QueryWrapper qw = (QueryWrapper) model.getAttribute("qw");

        if (pg == null) pg = new Page<>();
        if (qw == null) {
            qw = new QueryWrapper();
            qw.orderBy("ordDt desc", "ordQty desc");
        }
        mapper.paginate(pg, qw);
        model.addAttribute("pg", pg);
        model.addAttribute("qw", qw);

        model.addAttribute("title", "Hello World [title]");
        model.addAttribute("message", "Hello World [message]");

        return "Hello";
    }
}
