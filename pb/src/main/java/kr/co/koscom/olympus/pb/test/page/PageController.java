package kr.co.koscom.olympus.pb.test.page;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.db.mapper.OnTblSpotOrdMapper;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("/page")
public class PageController {

    @Resource
    private OnTblSpotOrdMapper mapper;

    @GetMapping("/test")
    public @ResponseBody String test(@RequestBody String name) {
        return "Hello %s, it is the test for you".formatted(name);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public String page(Model model, HttpSession sess, HttpRequest rq) {

        Page<OnTblSpotOrd> pg = (Page<OnTblSpotOrd>) model.getAttribute("pg");
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
