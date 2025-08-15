package kr.co.koscom.olympus.pb.db.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import kr.co.koscom.olympus.pb.ab.db.entity.PBEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * 实体类。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Table("test_001")
public class Test001 extends PBEntity {

    @Id
    private Long no1;

    @Id
    private Long no2;

    @Id(keyType = KeyType.Sequence, value = "SELECT NEXT VALUE FOR SEQ_ON_TBL_SPOT_ORD")
    private Long no3;

    @Id
    private String id1;

    @Id
    private String id2;

    @Id
    private String id3;

    private String name1;

    private String name2;

    private String name3;

    private Timestamp created;

    @Column(ignore = true) // 테이블에 없는 컬럼
    private String mark;
}
