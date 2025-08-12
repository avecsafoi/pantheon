package kr.co.koscom.olympus.pb.ab.data;

import kr.co.koscom.olympus.pb.ab.data.io.PBDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataOutputStream;

import java.io.Serializable;

public interface PBData extends Serializable {

    default void readPBData(PBDataInputStream in) throws Throwable {
        in.readObject(this);
    }

    default void writePBData(PBDataOutputStream os) throws Throwable {
        os.writeObject(this);
    }
}
