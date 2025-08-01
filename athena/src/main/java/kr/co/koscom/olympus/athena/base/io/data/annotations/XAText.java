package kr.co.koscom.olympus.athena.base.io.data.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface XAText {

    String name();

    int size();

    boolean fix() default true;

    String format() default "";
}
