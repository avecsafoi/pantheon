package kr.co.koscom.olympus.pb.ab.data;

import kr.co.koscom.olympus.pb.ab.data.io.PBDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBDataOutputStream;

import java.io.IOException;
import java.io.Serializable;

public interface PBData extends Serializable {

    default void readPBData(PBDataInputStream is) throws IOException {
        is.readPBData(this.getClass(), this);
    }

    default void writePBData(PBDataOutputStream os) throws IOException {
        os.writePBData(this.getClass(), this);
    }
}
