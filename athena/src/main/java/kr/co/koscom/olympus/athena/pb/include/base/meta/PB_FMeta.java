package kr.co.koscom.olympus.athena.pb.include.base.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PB_FMeta {

    String name();

    int size() default 0;

    Mask mask() default Mask.NONE;

    enum Mask {
        NONE, PWD
    }
}
