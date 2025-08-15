package kr.co.koscom.olympus.pb.ab.data.annotation;

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

    boolean skip() default false;

    public static enum Mask {
        NONE, PASSWORD, JUMIN, NAME
    }
}
