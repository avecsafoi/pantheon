package kr.co.koscom.olympus.pb.auto.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.sql.Timestamp;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
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

    private String tenantId;

    private String tenantName;

    private Timestamp created;

    private Timestamp updated;

}
