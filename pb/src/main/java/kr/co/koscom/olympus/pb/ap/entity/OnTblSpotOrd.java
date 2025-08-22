package kr.co.koscom.olympus.pb.ap.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.db.entity.PBEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.math.BigDecimal;

import static kr.co.koscom.olympus.pb.apa.PBCommon.*;

/**
 * 实体类。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Table("on_tbl_spot_ord")
public class OnTblSpotOrd extends PBEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @PBA(name = "주문일자", scale = Z_DATE)
    private String ordDt;

    @Id(keyType = KeyType.Sequence, value = "SELECT NEXT VALUE FOR SEQ_ON_TBL_SPOT_ORD")
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

    @Column(ignore = true) // 테이블에 없는 컬럼
    private String mark;

}
