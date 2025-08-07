package kr.co.koscom.olympus.pb.include.io;

import java.io.OutputStream;
import java.nio.charset.Charset;

public class PB_TextDataOutputStream extends PB_DataOutputStream {

    public PB_TextDataOutputStream(OutputStream out) {
        super(out);
    }

    public PB_TextDataOutputStream(OutputStream out, Charset charset) {
        super(out, charset);
    }

    @Override
    public void writeObject(Object o) throws Throwable {
        // TODO Implement
    }
}
