package kr.co.koscom.pantheon.athena.base.db.plugins;

public class XLock {

    public static final XLock NONE = new XLock(-1);
    public static final XLock NOWAIT = new XLock(0);
    public static final XLock WAIT_10 = new XLock(10);

    private final int waitTime;

    public XLock(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getWaitTime() {
        return waitTime;
    }
}
