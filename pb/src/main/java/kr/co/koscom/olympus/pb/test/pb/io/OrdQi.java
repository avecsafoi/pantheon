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
public class OrdQi extends PBObject {

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
