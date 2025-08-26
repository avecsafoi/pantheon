package kr.co.koscom.olympus.pb.ab.db.page;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class PBLock extends PBObject {

    public static final PBLock NONE = new PBLock(-1);
    public static final PBLock NOWAIT = new PBLock(0);
    public static final PBLock WAIT_10 = new PBLock(10);
    public static final PBLock WAIT_30 = new PBLock(30);
    public static final PBLock WAIT_60 = new PBLock(60);
    public static final PBLock FOREVER = new PBLock(Integer.MAX_VALUE);

    private int waitTime;

    public PBLock() {
        this(Integer.MAX_VALUE);
    }

    public PBLock(int waitTime) {
        this.waitTime = waitTime;
    }
}
