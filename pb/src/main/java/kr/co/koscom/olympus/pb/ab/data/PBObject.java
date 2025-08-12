package kr.co.koscom.olympus.pb.ab.data;

import kr.co.koscom.olympus.pb.ab.util.PBStringUtil;

public class PBObject implements PBData {

    @Override
    public String toString() {
        return PBStringUtil.toString(this);
    }
}
