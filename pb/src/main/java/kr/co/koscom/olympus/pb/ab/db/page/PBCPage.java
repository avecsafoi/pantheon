package kr.co.koscom.olympus.pb.ab.db.page;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PBCPage extends PBPage {

    private boolean first = true;

    private boolean last = false;

    private int limit = 100;

    private List<PBOrder> orders;
}
