package kr.co.koscom.olympus.pb.base.table;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import kr.co.koscom.olympus.pb.base.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

import static kr.co.koscom.olympus.pb.on.bms.PBCommon.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Table("ON_TBL_SPOT_ORD")
public class ON_TBL_SPOT_ORD extends PB_TBL {

    @Id
    @PBA(name = "주문일자", scale = Z_DT)
    public String ordDt;

    @Id(keyType = KeyType.Sequence, value = "SELECT NEXT VALUE FOR SEQ_ON_TBL_SPOT_ORD")
    @PBA(name = "주문번호")
    public Long ordNo;

    @PBA(name = "주문시장코드", scale = Z_ORD_MKT_CODE)
    public String ordMktCode;

    @PBA(name = "계좌번호", scale = Z_ACNT_NO)
    public String acntNo;

    @PBA(name = "종목번호", scale = Z_ISU_NO)
    public String isuNo;

    @PBA(name = "주문수량")
    public long ordQty;

    @PBA(name = "주문가")
    public BigDecimal ordPrc;

    @PBA(name = "주문시간", scale = Z_TIME)
    public String ordTime;

    @PBA(name = "매매구분", scale = Z_BNS_TP)
    public String bnsTp;

    @PBA(name = "체결수량")
    public long execQty;

    @PBA(name = "체결금액")
    public long execAmt;

}
