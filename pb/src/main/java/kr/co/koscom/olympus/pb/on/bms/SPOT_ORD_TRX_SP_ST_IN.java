package kr.co.koscom.olympus.pb.on.bms;

import kr.co.koscom.olympus.pb.include.PB_A;
import kr.co.koscom.olympus.pb.include.PB_Object;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class SPOT_ORD_TRX_SP_ST_IN extends PB_Object {

    @PB_A(name = "주문일자", scale = 8)
    public String zOrdDt;

    @PB_A(name = "주문번호")
    public long lOrdNo;

    @PB_A(name = "주문시장코드", scale = 4)
    public String zOrdMktCode;

    @PB_A(name = "계좌번호", scale = 20)
    public String zAcntNo;

    @PB_A(name = "종목번호", scale = 9)
    public String zIsuNo;

    @PB_A(name = "주문수량")
    public long lOrdQty;

    @PB_A(name = "주문가격")
    public BigDecimal dOrdPrc;

    @PB_A(name = "주문시간", scale = 9)
    public String zOrdTime;

    @PB_A(name = "매매구분", scale = 1)
    public String zBnsTp;
}
