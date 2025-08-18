package kr.co.koscom.olympus.pb.test.pb;

import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBPageData;
import kr.co.koscom.olympus.pb.db.service.impl.OnTblSpotOrdServiceImpl;
import kr.co.koscom.olympus.pb.include.PBST;
import kr.co.koscom.olympus.pb.include.PBService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static kr.co.koscom.olympus.pb.include.PBCommon.*;

public class io {
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @Data
    public static class OrdDo extends PBObject {

        @PBA(name = "주문일자", scale = Z_DT)
        private String ordDt;

        @PBA(name = "주문번호")
        private Long ordNo;

        @PBA(name = "주문시장코드", scale = Z_ORD_MKT_CODE)
        private String ordMktCode;

        @PBA(name = "계좌번호", scale = Z_ACNT_NO)
        private String acntNo;

        @PBA(name = "종목번호", scale = Z_ISU_NO)
        private String isuNo;

        @PBA(name = "주문수량")
        private Long ordQty;

        @PBA(name = "주문가")
        private BigDecimal ordPrc;

        @PBA(name = "주문시간", scale = Z_TIME)
        private String ordTime;

        @PBA(name = "매매구분", scale = Z_BNS_TP)
        private String bnsTp;

        @PBA(name = "체결수량")
        private Long execQty;

        @PBA(name = "체결금액")
        private Long execAmt;

        @PBA(name = "비고")
        private String mark;
    }

    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @Data
    public static class OrdQi extends PBObject {

        @PBA(name = "주문일자", scale = Z_DT)
        private String ordDt;

        @PBA(name = "주문번호")
        private Long ordNo;

        @PBA(name = "계좌번호", scale = Z_ACNT_NO)
        private String acntNo;

        @PBA(name = "종목번호", scale = Z_ISU_NO)
        private String isuNo;

        @PBA(name = "주문수량")
        private Long ordQty;

        @PBA(name = "주문가")
        private BigDecimal ordPrc;

        @PBA(name = "비고")
        private String mark;

        @PBA(name = "리미트")
        private int limit = 10;

        @PBA(name = "오프셋")
        private int offset;

    }

    public static class ST_10001001 extends PBST<ST_10001001_In, ST_10001001_Out> {

    }

    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @Data
    public static class ST_10001001_In extends PBObject implements PBPageData<PBCPage> {

        @PBA(name = "계좌번호", scale = Z_ACNT_NO)
        private String acno;

        @PBA(name = "종목정보", scale = Z_ISU_NO)
        private String isu;

        @PBA(name = "페이지")
        private PBCPage page;
    }

    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @Data
    public static class ST_10001001_Out extends PBObject implements PBPageData<PBCPage> {

        List<OrdDo> list;

        PBCPage page;
    }

    @Service("PB_SID 10001001")
    public static class ST_10001001_SVC implements PBService<ST_10001001> {

        @Resource
        OnTblSpotOrdServiceImpl service;

        @Override
        public int process(ST_10001001 st) {
            service.getMapper().cpage(st.getIn().getPage());
            return 0;
        }
    }
}
