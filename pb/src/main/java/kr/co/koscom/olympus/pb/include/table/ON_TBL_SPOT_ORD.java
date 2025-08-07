package kr.co.koscom.olympus.pb.include.table;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import kr.co.koscom.olympus.pb.include.PB_A;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

import static kr.co.koscom.olympus.pb.include.PB_COMMON.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Table("ON_TBL_SPOT_ORD")
public class ON_TBL_SPOT_ORD extends PB_TBL {

    @Id
    @PB_A(name = "주문일자", scale = Z_DT)
    public String ordDt;

    @Id(keyType = KeyType.Sequence, value = "SELECT NEXT VALUE FOR SEQ_ON_TBL_SPOT_ORD")
    @PB_A(name = "주문번호")
    public Long ordNo;

    @PB_A(name = "주문시장코드", scale = Z_ORD_MKT_CODE)
    public String ordMktCode;

    @PB_A(name = "계좌번호", scale = Z_ACNT_NO)
    public String acntNo;

    @PB_A(name = "종목번호", scale = Z_ISU_NO)
    public String isuNo;

    @PB_A(name = "주문수량")
    public long ordQty;

    @PB_A(name = "주문가")
    public BigDecimal ordPrc;

    @PB_A(name = "주문시간", scale = Z_TIME)
    public String ordTime;

    @PB_A(name = "매매구분", scale = Z_BNS_TP)
    public String bnsTp;

    @PB_A(name = "체결수량")
    public long execQty;

    @PB_A(name = "체결금액")
    public long execAmt;

}
