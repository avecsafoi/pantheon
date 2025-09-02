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

	boolean skip() default false;

	String charset() default "";

	PBCheck[] check() default {};

	Class<PBValidator>[] valid() default {};
}
