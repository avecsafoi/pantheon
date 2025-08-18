package kr.co.koscom.olympus.pb.test.page;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBNPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBOrder;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
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

        qi1.setOrdDt("20250101").setLimit(10).setOffset(2);
        qi2.setOrdDt("20250707").setLimit(5).setOffset(4);

        PBNPage npg = new PBNPage();

        PBCPage cpg = new PBCPage();

        List<PBOrder> orders = List.of(
                new PBOrder("ordDt", true, "19001231"),
                new PBOrder("acntNo", true, " "),
                new PBOrder("isuNo", true, " "),
                new PBOrder("ordNo", true, 0)
        );

        cpg.setFirst(false);
        cpg.setOrders(orders);

        List<OnTblSpotOrd> l01 = mapper.page01(qi1);

        List<OnTblSpotOrd> l02 = mapper.page02(qi2);

        List<OnTblSpotOrd> l03 = mapper.page03(qi1, qi2);

        List<OnTblSpotOrd> l40 = mapper.page40(qi1, npg);

        List<OnTblSpotOrd> l50 = mapper.page50(qi1, cpg);

        System.out.println("OK");
    }
}
