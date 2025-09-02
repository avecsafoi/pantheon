package kr.co.koscom.olympus.pb.ab.data.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PBV {

	Class<PBValidator>[] value();
}
