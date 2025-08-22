package kr.co.koscom.olympus.pb.ab.db.page;

import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PBCPage extends PBPage {

    @PBA(name = "처음조회 여부", scale = 1)
    private boolean first = true;

    @PBA(name = "마지막 여부", scale = 1)
    private boolean last = false;

    @PBA(name = "목록건수 제한", scale = 6)
    private int limit = 50;

    @PBA(name = "연속키 조건", scale = 6)
    private List<PBOrder> orders;

    @SuppressWarnings("unchecked")
    @Override
    public <T extends PBPage> T copy() {
        PBCPage p = new PBCPage().setFirst(first).setLast(last).setLimit(limit);
        if (orders != null) {
            p.orders = new ArrayList<>(orders.size());
            for (PBOrder o : orders) p.orders.add(new PBOrder(o.getColumn(), o.isAsc(), o.getValue()));
        }
        return (T) p;
    }
}
