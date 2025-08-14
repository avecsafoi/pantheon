import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static kr.co.koscom.olympus.athena.base.io.data.XDataUtils.createObject;


public class FreeTest {

    public static void main(String[] args) throws Throwable {
        Object o = new ArrayList<Integer>();

        Type t1 = o.getClass();
        Type t2 = o.getClass().getSuperclass();
        Type t3 = o.getClass().getGenericSuperclass();
        Type t4 = o.getClass().getSuperclass().getSuperclass();

        System.out.println(t1.getTypeName());
        System.out.println(t2.getTypeName());
        System.out.println(t3.getTypeName());
        System.out.println(t4.getTypeName());

        Class c1 = o.getClass();
        Class c2 = o.getClass().getSuperclass();
        Type c3 = o.getClass().getGenericSuperclass();

        System.out.println(c1.getTypeName());
        System.out.println(c2.getTypeName());
        System.out.println(c3.getTypeName());

        Object x = createObject(c1, null);

        System.out.println(x.getClass().getTypeName());


        Date date = new Date();

        Instant x1 = Instant.now();
        long epoch = x1.getEpochSecond();
        int nano = x1.getNano();

        System.out.printf("date: %s%n", date);
        System.out.printf("date.time: %s%n", date.getTime());
        System.out.printf("epoch: %d%n", epoch); // seconds
        System.out.printf("nano: %d%n", nano); // nano seconds
    }
}
