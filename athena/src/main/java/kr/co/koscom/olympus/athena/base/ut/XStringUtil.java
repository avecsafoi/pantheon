package kr.co.koscom.olympus.athena.base.ut;

import kr.co.koscom.olympus.athena.base.io.data.annotations.XAPrintMask;

public class XStringUtil {

    public static String toString(Object o) {
        return XToStringBuilder.toString(o);
    }

    public static String toPrintMask(String o, XAPrintMask a) {
        return "*****";
    }
}
