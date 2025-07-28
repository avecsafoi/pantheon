package kr.co.koscom.pantheon.athena.base.io.data.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KABinary {

    String name();

    int size();

    boolean fix() default true;

    boolean unsigned();

    String format();
}
