import java.lang.reflect.Type;
import java.util.ArrayList;

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

        Object x = o.getClass().getDeclaredConstructor().newInstance();

        System.out.println(x.getClass().getTypeName());

    }
}
