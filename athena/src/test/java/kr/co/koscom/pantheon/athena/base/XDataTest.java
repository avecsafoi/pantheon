package kr.co.koscom.pantheon.athena.base;

import kr.co.koscom.pantheon.athena.base.io.data.BinaryXDataInputStream;
import kr.co.koscom.pantheon.athena.base.io.data.BinaryXDataOutputStream;
import kr.co.koscom.pantheon.athena.base.io.data.TextXDataInputStream;
import kr.co.koscom.pantheon.athena.base.io.data.TextXDataOutputStream;
import kr.co.koscom.pantheon.athena.demo.model.DemoDataIn;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class XDataTest {

    Charset charset = StandardCharsets.UTF_8;

    @Test
    void text() throws IOException {
        DemoDataIn in = new DemoDataIn().id("아이디").name("이름").deposit(100);
        System.out.println(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TextXDataOutputStream tdos = new TextXDataOutputStream(baos, charset);
        tdos.writeObject(in);
        tdos.close();
        byte[] bs = baos.toByteArray();
        System.out.printf("BTYES(%d): [ %s ]%n", bs.length, new String(bs, charset));

        ByteArrayInputStream bais = new ByteArrayInputStream(bs);
        TextXDataInputStream tdis = new TextXDataInputStream(bais, charset);
        DemoDataIn out = tdis.readObject(DemoDataIn.class);

        System.out.println(out);
    }

    @Test
    void binary() throws IOException {
        DemoDataIn in = new DemoDataIn().id("아이디").name("이름").deposit(100);
        System.out.println(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryXDataOutputStream tdos = new BinaryXDataOutputStream(baos, charset);
        tdos.writeObject(in);
        tdos.close();
        byte[] bs = baos.toByteArray();
        System.out.printf("BTYES(%d): [ %s ]%n", bs.length, new String(bs, charset));

        ByteArrayInputStream bais = new ByteArrayInputStream(bs);
        BinaryXDataInputStream tdis = new BinaryXDataInputStream(bais, charset);
        DemoDataIn out = tdis.readObject(DemoDataIn.class);
        System.out.println(out);
    }
}
