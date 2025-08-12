package kr.co.koscom.olympus.pb.on.bms;

import kr.co.koscom.olympus.pb.include.PBA;
import kr.co.koscom.olympus.pb.include.data.PBData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class SPOT_ORD_TRX_SP_ST_IN implements PBData {

    @PBA(name = "주문일자", scale = 8)
    public String zOrdDt;

    @PBA(name = "주문번호")
    public long lOrdNo;

    @PBA(name = "주문시장코드", scale = 4)
    public String zOrdMktCode;

    @PBA(name = "계좌번호", scale = 20)
    public String zAcntNo;

    @PBA(name = "종목번호", scale = 9)
    public String zIsuNo;

    @PBA(name = "주문수량")
    public long lOrdQty;

    @PBA(name = "주문가격")
    public BigDecimal dOrdPrc;

    @PBA(name = "주문시간", scale = 9)
    public String zOrdTime;

    @PBA(name = "매매구분", scale = 1)
    public String zBnsTp;
}
