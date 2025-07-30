package kr.co.koscom.pantheon.athena.base.io.data;

import kr.co.koscom.pantheon.athena.base.io.data.annotations.XABinary;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAText;
import kr.co.koscom.pantheon.athena.base.ut.XFieldsCache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class XDataFieldsCache extends XFieldsCache {

    public final static Predicate<Field> TEXT_XDATA_FILTER = f -> f.getAnnotation(XAText.class) != null;
    public final static Predicate<Field> BINARY_XDATA_FILTER = f -> f.getAnnotation(XABinary.class) != null;
    static final Map<Class<?>, Field[]> INPUT_TEXT_XDATA = new HashMap<>();
    static final Map<Class<?>, Field[]> OUTPUT_TEXT_XDATA = new HashMap<>();
    static final Map<Class<?>, Field[]> INPUT_BINARY_XDATA = new HashMap<>();
    static final Map<Class<?>, Field[]> OUTPUT_BINARY_XDATA = new HashMap<>();

    public static Field[] getInputTextXDataFields(Class<?> c) {
        return getFieldsByFilter(c, INPUT_TEXT_XDATA, TEXT_XDATA_FILTER);
    }

    public static Field[] getOutputTextXDataFields(Class<?> c) {
        return getFieldsByFilter(c, OUTPUT_TEXT_XDATA, TEXT_XDATA_FILTER);
    }

    public static Field[] getInputBinaryXDataFields(Class<?> c) {
        return getFieldsByFilter(c, INPUT_BINARY_XDATA, BINARY_XDATA_FILTER);
    }

    public static Field[] getOutputBinaryXDataFields(Class<?> c) {
        return getFieldsByFilter(c, OUTPUT_BINARY_XDATA, BINARY_XDATA_FILTER);
    }
}
