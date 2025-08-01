import kr.co.koscom.pantheon.athena.base.io.data.TextXData;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 테스트 {

    public List<String> fs;

    public static void main(String[] args) {
        ArrayList<TextXData> o = new ArrayList<TextXData>();
        test(o.getClass(), 0);
        TextXData[] tx = {};
        test(tx.getClass(), 0);
        Integer x = 0;
        test(x.getClass(), 0);
    }

    public static void test(Type type, int i) {

        System.out.println();

        String ds = StringUtils.repeat(' ', i * 4);

        System.out.println(ds + "TEST: " + type.getTypeName());

        if (type instanceof ParameterizedType pt) {
            System.out.println(ds + "ParameterizedTypetype: " + pt.getTypeName());
            Type rt = pt.getRawType();
            Class<?> rtc = rt.getClass();
            for (Type t : pt.getActualTypeArguments())
                test(t, i + 1);
        }

        if (type instanceof GenericArrayType gat) {
            System.out.println(ds + "GenericArrayType: " + gat.getTypeName());
        }

        if (type instanceof Class<?> c) {
            TypeVariable<? extends Class<?>>[] tva = c.getTypeParameters();
            System.out.println(ds + "getTypeParameters: " + Arrays.toString(tva));

            Class<?> s = c.getComponentType();
            if (s != null) System.out.println(ds + "getComponentType: " + s.getCanonicalName());

            System.out.println("Class: " + c.getCanonicalName());
        } else {
            throw new RuntimeException("NOT CLASS: " + type.getTypeName());
        }
    }
}
