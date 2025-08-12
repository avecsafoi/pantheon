package kr.co.koscom.olympus.pb.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PBA {

    String name();

    int scale() default 0;

    int precision() default 0;

    boolean fix() default true;

    String format() default "";

    Mask mask() default Mask.NONE;

    public static enum Mask {
        NONE, PASSWORD, JUMIN, NAME
    }
}
