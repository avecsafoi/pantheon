package kr.co.koscom.olympus.pb.on.bms;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class SPOT_ORD_TRX_SP_ST_OUT extends PBObject {

    @PBA(name = "주문번호")
    public long lOrdNo;

    @PBA(name = "체결수량")
    public long lExecQty;

    @PBA(name = "체결가")
    public BigDecimal dExecPrc;

    @PBA(name = "쿼리코드")
    public long lSqlCode;

    @PBA(name = "오류메시지코드")
    public String zErrMsgCode;

}
