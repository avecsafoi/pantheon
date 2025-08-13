package kr.co.koscom.olympus.pb.test.text;

import kr.co.koscom.olympus.pb.ab.data.io.PBTextDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBTextDataOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class TextTest {

    public static void main(String[] args) throws Throwable {
        TestVo v1 = new TestVo();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
        PBTextDataOutputStream dos = new PBTextDataOutputStream(baos);
        dos.writeObject(v1);
        dos.close();
        byte[] b = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        PBTextDataInputStream dis = new PBTextDataInputStream(bais);
        TestVo v2 = dis.readObject(TestVo.class);
        System.out.println(v1.equals(v2));
        assert v1.equals(v2);
    }
}
