package kr.co.koscom.olympus.pb.ab.db.page;

public record PBLock(int waitTime) {
    public static final PBLock NONE = new PBLock(-1);
    public static final PBLock NOWAIT = new PBLock(0);
    public static final PBLock WAIT_10 = new PBLock(10);
    public static final PBLock WAIT_30 = new PBLock(30);
    public static final PBLock WAIT_60 = new PBLock(60);
    public static final PBLock FOREVER = new PBLock(Integer.MAX_VALUE);
}
