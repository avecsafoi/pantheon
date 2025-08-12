package kr.co.koscom.olympus.pb.include.table;

import com.mybatisflex.annotation.*;
import com.mybatisflex.core.mask.Masks;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Table("TEST_001")
public class PB_TBL_TEST_001 extends PB_TBL {

    @Id
    public Long no1;

    @Id
    public Long no2;

    @Id(keyType = KeyType.Sequence, value = "SELECT NEXT VALUE FOR SEQ_ON_TBL_SPOT_ORD")
    public Long no3;

    @Id
    public String id1;

    @Id
    public String id2;

    @Id
    public String id3;

    public String name1;

    public String name2;

    @ColumnMask(Masks.EMAIL)
    public String name3;

    @Column(onInsertValue = "now()")
    public Date created;

    @Column(ignore = true)
    public String remark; // 테이블에 존재하지 않는 컬럼 (데이터 송수신할 때만 사용)
}
