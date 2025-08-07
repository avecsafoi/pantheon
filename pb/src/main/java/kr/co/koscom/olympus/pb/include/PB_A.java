package kr.co.koscom.olympus.pb.include;

public @interface PB_A {

    String name();

    int scale() default 0;

    int precision() default 0;

    String format() default "";
}
