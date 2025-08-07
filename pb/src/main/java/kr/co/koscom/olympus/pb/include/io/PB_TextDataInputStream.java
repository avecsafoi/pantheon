package kr.co.koscom.olympus.pb.include.io;

import java.io.InputStream;
import java.nio.charset.Charset;

public class PB_TextDataInputStream extends PB_DataInputStream {

    public PB_TextDataInputStream(InputStream in) {
        super(in);
    }

    public PB_TextDataInputStream(InputStream in, Charset charset) {
        super(in, charset);
    }

    public PB_TextDataInputStream(byte[] b) {
        super(b);
    }

    public PB_TextDataInputStream(byte[] b, Charset charset) {
        super(b, charset);
    }

    @Override
    public <X> X readObject(Class<X> c) throws Throwable {
        // TODO Implement
        return null;
    }
}
