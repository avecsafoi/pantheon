package kr.co.koscom.pantheon.athena.base;

import kr.co.koscom.pantheon.athena.base.io.data.TextXDataInputStream;
import kr.co.koscom.pantheon.athena.base.io.data.TextXDataOutputStream;
import kr.co.koscom.pantheon.athena.demo.model.DemoDataIn;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TextXData_Test {

    Charset charset = StandardCharsets.UTF_8;

    @Test
    void text() throws IOException {
        DemoDataIn in = new DemoDataIn().setId("아이디").setName("이름").setDeposit(100);
        System.out.println(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TextXDataOutputStream tdos = new TextXDataOutputStream(baos, charset);
        tdos.writeObject(in);
        tdos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        TextXDataInputStream tdis = new TextXDataInputStream(bais, charset);
        DemoDataIn out = tdis.readObject(DemoDataIn.class);

        System.out.println(out);
    }
}
