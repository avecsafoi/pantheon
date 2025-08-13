package kr.co.koscom.olympus.pb.test.text;

import kr.co.koscom.olympus.pb.ab.data.io.PBTextDataInputStream;
import kr.co.koscom.olympus.pb.ab.data.io.PBTextDataOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class TextTest {

    public static void main(String[] args) throws Throwable {
        TestVo v1 = new TestVo();
        v1.setMark("ornage");
        System.out.println(v1);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
        PBTextDataOutputStream dos = new PBTextDataOutputStream(baos);
        dos.writeObject(v1);
        baos.close();
        byte[] b = baos.toByteArray();
        System.out.printf("(%d) [ %s ]%n", b.length, new String(b));

        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        PBTextDataInputStream dis = new PBTextDataInputStream(bais);
        TestVo v2 = dis.readObject(TestVo.class);
        System.out.println(v2);

        String s1 = v1.toString();
        String s2 = v2.toString();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1.equals(s2));
    }
}
