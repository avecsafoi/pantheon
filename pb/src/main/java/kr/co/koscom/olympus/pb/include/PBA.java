package kr.co.koscom.olympus.pb.include;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PBA {

    String name();

    int scale() default 0;

    int precision() default 0;

    boolean fix() default true;

    String format() default "";
}
