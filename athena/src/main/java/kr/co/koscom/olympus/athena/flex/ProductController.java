package kr.co.koscom.olympus.athena.flex;

import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/flex")
public class ProductController {

    @Resource
    private ProductService ps;

    @PostMapping("/page")
    public @ResponseBody Page<Product> page(@RequestBody Page<Product> pg) {
        ps.page(pg);
        return pg;
    }

    @PostMapping("/save")
    public @ResponseBody Product save(@RequestBody Product product) {
        if (ps.save(product)) return product;
        else throw new RuntimeException("저장 실패하였습니다.");
    }

    @PostMapping("/remove")
    public @ResponseBody Product remove(@RequestBody Product product) {
        if (ps.removeById(product)) return product;
        else throw new RuntimeException("삭제 실패하였습니다.");
    }

}
