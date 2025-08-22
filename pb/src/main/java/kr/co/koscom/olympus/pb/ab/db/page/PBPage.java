package kr.co.koscom.olympus.pb.ab.db.page;

import kr.co.koscom.olympus.pb.ab.data.PBObject;

public abstract class PBPage extends PBObject {

    public abstract <T extends PBPage> T copy();
}
