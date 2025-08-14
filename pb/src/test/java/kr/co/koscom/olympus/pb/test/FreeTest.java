package kr.co.koscom.olympus.pb.test;

import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.include.PBService;
import kr.co.koscom.olympus.pb.on.bms.OnbSpotOrdSP_BSMM;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class FreeTest {

    public static void main(String[] args) throws Exception {
        t2();
    }

    public static void t1() throws Exception {
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

    public static void t2() throws Exception {
        OnbSpotOrdSP_BSMM bsmm = new OnbSpotOrdSP_BSMM() {
        };
        Type sc = bsmm.getClass().getGenericSuperclass();
        Type[] ta = ((Class<?>) sc).getGenericInterfaces();
        for (Type t : ta) {
            if (t instanceof ParameterizedType p) {
                Type rt = p.getRawType();
                if (rt instanceof PBService) {
                    Type[] sx = p.getActualTypeArguments();
                    for (Type s : sx) {
                        System.out.println(s);
                    }
                }
                Class rtc = (Class) rt;
                if(PBService.class.isAssignableFrom(rtc)) {
                    System.out.println("x");
                }
            }
        }
    }
}
