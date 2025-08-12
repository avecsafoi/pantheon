package kr.co.koscom.olympus.pb.include.table;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import kr.co.koscom.olympus.pb.ab.data.PBData;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Table("TEST_001")
public class PB_TBL_TEST_01 implements PBData {

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

    public String name3;
}
