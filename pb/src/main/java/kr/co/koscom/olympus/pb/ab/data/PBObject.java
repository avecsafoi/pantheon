package kr.co.koscom.olympus.pb.ab.data;

import kr.co.koscom.olympus.pb.ab.util.PBToStringBuilder;

public class PBObject implements PBData {

    @Override
    public String toString() {
        return PBToStringBuilder.toString(this);
    }

    public StringBuilder toString(StringBuilder sb) {
        sb.append(toString());
        return sb;
    }
}
