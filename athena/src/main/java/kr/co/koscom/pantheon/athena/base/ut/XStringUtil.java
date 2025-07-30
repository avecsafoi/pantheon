package kr.co.koscom.pantheon.athena.base.ut;

import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAPrintMask;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class XStringUtil {

    public static String toString(Object o) {
        ToStringBuilder b = new ToStringBuilder(o);
        return null;
    }

    public static String toPrintMask(String o, XAPrintMask a) {
        return "*****";
    }
}
