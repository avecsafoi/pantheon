package kr.co.koscom.olympus.pb.ab.data.io;

import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
public abstract class PBDataInputStream extends DataInputStream {

    protected final Charset charset;

    public PBDataInputStream(InputStream in) {
        super(in);
        this.charset = StandardCharsets.UTF_8;
    }

    public PBDataInputStream(InputStream in, Charset charset) {
        super(in);
        this.charset = charset;
    }

    public PBDataInputStream(byte[] b) {
        super(new ByteArrayInputStream(b));
        this.charset = StandardCharsets.UTF_8;
    }

    public PBDataInputStream(byte[] b, Charset charset) {
        super(new ByteArrayInputStream(b));
        this.charset = charset;
    }

    public abstract <X> X readObject(@Nonnull Class<X> c) throws IOException;

    public abstract void readPBData(@Nonnull Class<?> c, Object o) throws IOException;
}
