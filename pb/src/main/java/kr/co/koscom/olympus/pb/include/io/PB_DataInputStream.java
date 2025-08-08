package kr.co.koscom.olympus.pb.include.io;

import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
public abstract class PB_DataInputStream extends DataInputStream {

    protected final Charset charset;

    public PB_DataInputStream(InputStream in) {
        super(in);
        this.charset = StandardCharsets.UTF_8;
    }

    public PB_DataInputStream(InputStream in, Charset charset) {
        super(in);
        this.charset = charset;
    }

    public PB_DataInputStream(byte[] b) {
        super(new ByteArrayInputStream(b));
        this.charset = StandardCharsets.UTF_8;
    }

    public PB_DataInputStream(byte[] b, Charset charset) {
        super(new ByteArrayInputStream(b));
        this.charset = charset;
    }

    public abstract <X> X readObject(@Nonnull Class<X> c) throws Throwable;
}
