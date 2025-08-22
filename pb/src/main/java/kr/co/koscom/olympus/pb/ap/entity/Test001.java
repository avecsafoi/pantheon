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

import java.time.LocalDateTime;

import static kr.co.koscom.olympus.pb.apa.PBCommon.ZS_CHARSET_CHINA;
import static kr.co.koscom.olympus.pb.apa.PBCommon.ZS_CHARSET_UTF8;

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

    @PBA(name = "한국이름", scale = 20, charset = ZS_CHARSET_UTF8)
    private String name1;

    @PBA(name = "인도이름", scale = 20, charset = ZS_CHARSET_CHINA)
    private String name2;

    @PBA(name = "중국이름", scale = 20, charset = ZS_CHARSET_CHINA)
    private String name3;

    // @Schema(description = "생성일자", type = "LocalDateTime", example = "2025-01-01 00:00:00")
    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @Column(ignore = true) // 테이블에 없는 컬럼
    private String mark;
}
