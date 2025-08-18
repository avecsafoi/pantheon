package kr.co.koscom.olympus.pb.test.pb.io;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

import static kr.co.koscom.olympus.pb.include.PBCommon.*;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class OrdDo extends PBObject {

    @PBA(name = "주문일자", scale = Z_DT)
    private String ordDt;

    @PBA(name = "주문번호", scale = Z_LONG)
    private Long ordNo;

    @PBA(name = "주문시장코드", scale = Z_ORD_MKT_CODE)
    private String ordMktCode;

    @PBA(name = "계좌번호", scale = Z_ACNT_NO)
    private String acntNo;

    @PBA(name = "종목번호", scale = Z_ISU_NO)
    private String isuNo;

    @PBA(name = "주문수량", scale = Z_LONG)
    private Long ordQty;

    @PBA(name = "주문가", scale = Z_LONG)
    private BigDecimal ordPrc;

    @PBA(name = "주문시간", scale = Z_TIME)
    private String ordTime;

    @PBA(name = "매매구분", scale = Z_BNS_TP)
    private String bnsTp;

    @PBA(name = "체결수량", scale = Z_LONG)
    private Long execQty;

    @PBA(name = "체결금액", scale = Z_LONG)
    private Long execAmt;

    @PBA(name = "비고", scale = Z_REMARK)
    private String mark;
}
