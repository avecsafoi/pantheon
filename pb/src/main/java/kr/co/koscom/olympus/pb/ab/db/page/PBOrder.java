package kr.co.koscom.olympus.pb.ab.db.page;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PBOrder extends PBObject {
    private String column;
    private boolean asc;
    private Object value;
    // private boolean nullsFirst;
}
