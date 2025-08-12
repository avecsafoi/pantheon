package kr.co.koscom.olympus.pb.ab.util;

import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;

import java.util.Objects;

public class PBStringUtil {

    public static String mask(String s, PBA.Mask mask) {
        if (Objects.requireNonNull(mask) == PBA.Mask.NONE) return s;
        return "**********";
    }

    public static String toString(Object o) {
        return PBToStringBuilder.toString(o);
    }
}
