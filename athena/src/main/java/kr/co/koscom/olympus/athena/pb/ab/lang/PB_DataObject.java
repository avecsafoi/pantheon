package kr.co.koscom.olympus.athena.pb.ab.lang;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class PB_DataObject implements PB_Data {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
