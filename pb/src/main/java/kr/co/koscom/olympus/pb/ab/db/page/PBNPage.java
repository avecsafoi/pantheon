package kr.co.koscom.olympus.pb.ab.db.page;

import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PBNPage extends PBPage {

    @PBA(name = "목록건수 제한", scale = 6)
    private int limit = 50;

    @PBA(name = "오프셋 할 건수", scale = 6)
    private int offset;

    @PBA(name = "마지막 여부", scale = 1)
    private boolean last = false;

    @SuppressWarnings("unchecked")
    @Override
    public <T extends PBPage> T copy() {
        return (T) new PBNPage().setLimit(limit).setOffset(offset).setLast(last);
    }
}
