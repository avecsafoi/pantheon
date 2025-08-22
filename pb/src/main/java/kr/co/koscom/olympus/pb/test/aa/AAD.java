package kr.co.koscom.olympus.pb.test.aa;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class AAD extends PBObject {

    private String id;

    private String name;
}
