package kr.co.koscom.olympus.pb.ab.util;

import kr.co.koscom.olympus.pb.ab.data.annotation.PBAMask;

public class PBStringUtil {

    public static String mask(String s, PBAMask m) {
        if (m == null) return s;
        return "**********";
    }

    public static String toString(Object o) {
        return PBToStringBuilder.toString(o);
    }
}
