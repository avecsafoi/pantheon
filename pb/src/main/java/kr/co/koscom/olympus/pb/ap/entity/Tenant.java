package kr.co.koscom.olympus.pb.ap.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import kr.co.koscom.olympus.pb.ab.db.entity.PBEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.Date;

/**
 * 实体类。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Table("tenant")
public class Tenant extends PBEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long no;

    @Column(tenantId = true)
    private String tenantId;

    private String tenantName;

    private Date created;

    private Date updated;

    @Column(ignore = true) // 테이블에 없는 컬럼
    private String mark;
}
