package kr.co.koscom.olympus.pb.ab.util;

public class PBException extends Exception {

    public PBException() {
    }

    public PBException(String message) {
        super(message);
    }

    public PBException(String message, Throwable cause) {
        super(message, cause);
    }

    public PBException(Throwable cause) {
        super(cause);
    }

    public PBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
