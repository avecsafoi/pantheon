import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.List;

public class Test1 {

    public List<String> fs;

    public static void main(String[] args) {
        Method m = MethodUtils.getMethodObject(Test1.class, "test", List.class);
        ParameterizedType mt = (ParameterizedType) m.getGenericReturnType();
        Class<?> returnType = m.getReturnType();
        System.out.printf("returnType = %s", returnType.getSimpleName());
        TypeVariable<? extends Class<?>>[] tva = returnType.getTypeParameters();
        int i = 0;
        for (TypeVariable<? extends Class<?>> a : tva) {
            System.out.printf("%3d %s (%s) %s", i++, a.getName(), a.getClass().getSimpleName(), a.getGenericDeclaration());
        }
        Field f = FieldUtils.getField(Test1.class, "fs", true);
        ParameterizedType ft = (ParameterizedType) f.getGenericType();


        System.out.println("");
    }

    public List<String> test(List<String> in) {
        return in;
    }
}
