package kr.co.koscom.olympus.athena.base.db.plugins;

public record XLock(int waitTime) {
    public static final XLock NONE = new XLock(-1);
    public static final XLock NOWAIT = new XLock(0);
    public static final XLock WAIT_10 = new XLock(10);
}
