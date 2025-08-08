package kr.co.koscom.olympus.pb.include.io;

import lombok.Getter;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
public abstract class PB_DataOutputStream extends DataOutputStream {

    protected final Charset charset;

    public PB_DataOutputStream(OutputStream out) {
        super(out);
        this.charset = StandardCharsets.UTF_8;
    }

    public PB_DataOutputStream(OutputStream out, Charset charset) {
        super(out);
        this.charset = charset;
    }

    public abstract void writeObject(Object o) throws Throwable;
}
