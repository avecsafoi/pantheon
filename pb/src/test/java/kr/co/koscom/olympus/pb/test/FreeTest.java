package kr.co.koscom.olympus.pb.test;

import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public class FreeTest {

    public static void main(String[] args) {
        Field f = FieldUtils.getField(OnTblSpotOrd.class, "ordQty");
        Class<?> c = f.getType();
        System.out.println(c.getSimpleName());

        if (Number.class.isAssignableFrom(c)) {
            System.out.println("Number");
        }
        if (int.class.isAssignableFrom(c)) {
            System.out.println("int");
        }
        if (long.class.isAssignableFrom(c)) {
            System.out.println("long");
        }
    }
}
