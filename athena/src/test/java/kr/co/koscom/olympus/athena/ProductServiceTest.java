package kr.co.koscom.olympus.athena;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.athena.flex.Product;
import kr.co.koscom.olympus.athena.flex.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

@SpringBootTest
//@Commit
public class ProductServiceTest {

    @Resource
    ProductService ps;

    @Test
    void product() throws IOException {
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            Product p = new Product();
            p.prodName = "텔레비젼 %04d".formatted(i);
            p.prodModel = "TV %04d".formatted(i);
            p.prodQty = r.nextInt(1, 10);
            ps.saveOrUpdate(p);
            BigInteger n = p.prodNo;
            if (n == null || n.longValue() < 1) throw new RuntimeException("XXXXX");
        }
        QueryWrapper qw = new QueryWrapper();
        Page<Product> p1 = new Page<>(1, 5);
        while (true) {
            ps.page(p1, qw);
            System.out.println(p1);
            for (Product p : p1.getRecords()) {
                int n = p.prodQty;
                if (n < 1 || n > 10) {
                    p.prodQty = r.nextInt(1, 100);
                    ps.updateById(p);
                }
            }
            if (p1.hasNext()) p1.setPageNumber(p1.getPageNumber() + 1);
            else {
                System.out.println("BREAK");
                break;
            }
        }
    }
}
