package kr.co.koscom.olympus.pb.ab.data.annotation;

public @interface PBAMask {

    Type type();

    enum Type {
        PWD, NAME, JUMIN
    }
}
