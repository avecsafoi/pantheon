package kr.co.koscom.olympus.athena.base.io.data;

import kr.co.koscom.olympus.athena.base.ut.XToStringBuilder;

import java.io.Serializable;

public interface XData extends Serializable {

    default String toString(Object o) {
        return XToStringBuilder.toString(o);
    }
}
