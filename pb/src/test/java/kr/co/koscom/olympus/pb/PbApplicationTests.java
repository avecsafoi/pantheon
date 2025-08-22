package kr.co.koscom.olympus.pb;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ap.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.ap.entity.Test001;
import kr.co.koscom.olympus.pb.ap.mapper.OnTblSpotOrdMapper;
import kr.co.koscom.olympus.pb.ap.mapper.Test001Mapper;
import kr.co.koscom.olympus.pb.aps.on.bms.SPOT_ORD_TRX_SP_ST;
import kr.co.koscom.olympus.pb.aps.on.bms.SPOT_ORD_TRX_SP_ST_IN;
import kr.co.koscom.olympus.pb.aps.on.bms.SPOT_ORD_TRX_SP_ST_OUT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PbApplicationTests {

    @Resource
    OnTblSpotOrdMapper mapper;

    @Resource
    Test001Mapper m1;

    @Test
    void contextLoads() {
        // NOTHING_TODO
    }

    /**
     * PROCEDURE CALL TEST
     */
    @Test
    void procedureCall() {
        SPOT_ORD_TRX_SP_ST st = new SPOT_ORD_TRX_SP_ST();
        st.setIn(new SPOT_ORD_TRX_SP_ST_IN().setZOrdDt("20010101").setLOrdNo(1));
        st.setOut(new SPOT_ORD_TRX_SP_ST_OUT());
        mapper.ON_FN_SPOT_ORD_MAIN(st);
    }

    /**
     * PK 자동입력 테스트
     * PK = (ordDt, ordNo), ordNo 값이 Null 이면 자동입력
     */
    @Test
    void insertOrUpdate() {
        OnTblSpotOrd r = new OnTblSpotOrd().setOrdDt("20121212");
        mapper.insertOrUpdate(r, true); // INSERT
    }

    @Test
    void select_continue_1() {
        OnTblSpotOrd r = new OnTblSpotOrd();
        r.setOrdDt("20010101");
        Page<OnTblSpotOrd> p1 = new Page<>(1, 3);
        QueryWrapper qw = new QueryWrapper();
        qw.where("(ord_dt > ? or (ord_dt = ? and (ord_qty > ? or (ord_qty = ? and (ord_prc > ? or (ord_prc = ? and (ord_no > ?)))))))",
                r.getOrdDt(), r.getOrdDt(), r.getOrdQty(), r.getOrdQty(), r.getOrdPrc(), r.getOrdPrc(), r.getOrdNo());
        while (true) {
            mapper.paginate(p1, qw);
            if (p1.hasNext()) p1.setPageNumber(p1.getPageNumber() + 1);
            else break;
        }
    }

    @Test
    void select_continue_2() {
        OnTblSpotOrd r = new OnTblSpotOrd();
        r.setOrdDt("20010101");
        Page<OnTblSpotOrd> p1 = new Page<>(1, 3);
        while (true) {
            QueryWrapper qw = new QueryWrapper();
            qw.where("(ord_dt > ? or (ord_dt = ? and (ord_qty > ? or (ord_qty = ? and (ord_prc > ? or (ord_prc = ? and (ord_no > ?)))))))",
                    r.getOrdDt(), r.getOrdDt(), r.getOrdQty(), r.getOrdQty(), r.getOrdPrc(), r.getOrdPrc(), r.getOrdNo());
            Page<OnTblSpotOrd> p2 = mapper.paginate(p1, qw);
            for (OnTblSpotOrd x : p2.getRecords()) System.out.println(x);
            if (p2.hasNext() && !p2.getRecords().isEmpty()) {
                r = p2.getRecords().getLast();
                p2.setTotalRow(-1);
            } else break;
        }
    }

    @Test
    void test001() {
        Test001 t = new Test001();
        t.setId1("Orange").setId2("purple").setId3("코스콤").setNo1(1L).setNo2(2L);
        m1.insertOrUpdate(t, false);
        m1.insertOrUpdate(t, false);
    }
}
