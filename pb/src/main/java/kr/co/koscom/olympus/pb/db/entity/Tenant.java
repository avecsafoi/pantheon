package kr.co.koscom.olympus.pb.db.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

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
@Table("tenant")
public class Tenant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long no;

    @Column(tenantId = true)
    private String tenantId;

    private String tenantName;

    private Timestamp created;

    private Timestamp updated;

    @Column(ignore = true) // 테이블에 없는 컬럼
    private String mark;
}
