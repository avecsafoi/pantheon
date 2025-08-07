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
public class SPOT_ORD_TRX_SP_ST_OUT extends PB_Object {

    @PB_A(name = "주문번호")
    public long lOrdNo;

    @PB_A(name = "체결수량")
    public long lExecQty;

    @PB_A(name = "체결가")
    public BigDecimal dExecPrc;

    @PB_A(name = "쿼리코드")
    public long lSqlCode;

    @PB_A(name = "오류메시지코드")
    public String zErrMsgCode;

}
