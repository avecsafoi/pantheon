package kr.co.koscom.olympus.athena.base.io.data;

import lombok.Getter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Getter
public abstract class XDataInputStream extends DataInputStream {

    public final Charset charset;

    public XDataInputStream(InputStream in, Charset charset) {
        super(in);
        this.charset = charset;
    }

    public abstract <X> X readObject(Class<X> clazz) throws IOException;
}
