package kr.co.koscom.olympus.pb.test.page;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBNPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBOrder;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.db.mapper.OnTblSpotOrdMapper;
import kr.co.koscom.olympus.pb.test.pb.io.OrdQi;
import kr.co.koscom.olympus.pb.test.pb.io.OrdQiWithCPage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
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

        OrdQiWithCPage qip = new OrdQiWithCPage();
        qip.setOrdDt("19001231").setPage(cpg);

        List<OnTblSpotOrd> l01 = mapper.page01(qi1);

        List<OnTblSpotOrd> l02 = mapper.page02(qi2);

        List<OnTblSpotOrd> l03 = mapper.page03(qi1, qi2);

        List<OnTblSpotOrd> l04 = mapper.page04(qi1, qi2);

        List<OnTblSpotOrd> l40 = mapper.page40(qi1, npg);
        System.out.println(npg);

        List<OnTblSpotOrd> l50 = mapper.page50(qi1, cpg);
        System.out.println(cpg);

        List<OnTblSpotOrd> l51 = mapper.page51(cpg, qi1);
        System.out.println(cpg);

        cpg.setFirst(false);
        List<OnTblSpotOrd> l52 = mapper.page52(qip);
        System.out.println(cpg);

        Collections.shuffle(new ArrayList<>(orders)); // 순서섞기

        cpg.setFirst(false);
        List<OnTblSpotOrd> l53 = mapper.page53(qip);
        System.out.println(cpg);
    }
}
