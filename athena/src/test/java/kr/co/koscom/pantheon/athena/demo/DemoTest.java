package kr.co.koscom.pantheon.athena.demo;

import kr.co.koscom.pantheon.athena.base.io.data.TextXDataInputStream;
import kr.co.koscom.pantheon.athena.base.io.data.TextXDataOutputStream;
import kr.co.koscom.pantheon.athena.demo.model.DemoDataIn;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DemoTest {

    @Test
    void test1() throws IOException {

        Charset charset = StandardCharsets.UTF_8;

        DemoDataIn in = new DemoDataIn().id("아이디").name("이름").deposit(100);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        TextXDataOutputStream dos = new TextXDataOutputStream(bos, charset);
        dos.writeObject(in);
        dos.close();

        byte[] b = bos.toByteArray();
        System.out.printf("(%d)[%s]%n", b.length, new String(b, charset));

        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        TextXDataInputStream dis = new TextXDataInputStream(bis, charset);

        DemoDataIn in2 = dis.readObject(DemoDataIn.class);

        System.out.println(in);
        System.out.println(in2);
    }
}
