package kr.co.koscom.olympus.athena.base.io.data;

import lombok.Getter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@Getter
public abstract class XDataOutputStream extends DataOutputStream {

    public final Charset charset;

    public XDataOutputStream(OutputStream out, Charset charset) {
        super(out);
        this.charset = charset;
    }

    public abstract void writeObject(Object o) throws IOException;
}
