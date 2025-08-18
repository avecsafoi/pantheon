package kr.co.koscom.olympus.pb.test.page;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBNPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBOrder;
import kr.co.koscom.olympus.pb.db.mapper.OnTblSpotOrdMapper;
import kr.co.koscom.olympus.pb.test.page.io.OrdQi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PageTest {

    @Resource
    OnTblSpotOrdMapper mapper;

    @Test
    void test() {

        OrdQi qi1 = new OrdQi(), qi2 = new OrdQi();

        qi1.setOrdDt("20250101").setLimit(10).setOffset(1);
        qi2.setOrdDt("20250707").setLimit(5).setOffset(2);

        PBNPage npg = new PBNPage();

        PBCPage cpg = new PBCPage();

        List<PBOrder> orders = List.of(
                new PBOrder("ordDt", true, "20010101"),
                new PBOrder("acntNo", true, " "),
                new PBOrder("isuNo", true, " "),
                new PBOrder("ordNo", true, 0)
        );

        cpg.setOrders(orders);

        mapper.page01(qi1);

        mapper.page02(qi2);

        mapper.page03(qi1, qi2);

        mapper.page40(qi1, npg);

        mapper.page50(qi1, cpg);
    }
}
