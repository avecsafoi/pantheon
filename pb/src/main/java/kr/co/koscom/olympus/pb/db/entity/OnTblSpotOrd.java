package kr.co.koscom.olympus.pb.db.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实体类。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("on_tbl_spot_ord")
public class OnTblSpotOrd implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String ordDt;

    @Id
    private Long ordNo;

    private String ordMktCode;

    private String acntNo;

    private String isuNo;

    private Long ordQty;

    private BigDecimal ordPrc;

    private String ordTime;

    private String bnsTp;

    private Long execQty;

    private Long execAmt;

}
