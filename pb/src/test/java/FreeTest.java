import kr.co.koscom.olympus.pb.include.table.ON_TBL_SPOT_ORD;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public class FreeTest {

    public static void main(String[] args) {
        Field f = FieldUtils.getField(ON_TBL_SPOT_ORD.class, "ordQty");
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
