package kr.co.koscom.pantheon.athena.base.io.data;

import kr.co.koscom.pantheon.athena.base.io.data.annotations.XABinary;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAText;
import kr.co.koscom.pantheon.athena.base.ut.ClassFieldsCache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class XDataFieldCache extends ClassFieldsCache {

    static final Map<Class<?>, Field[]> INPUT_TEXT_XDATA = new HashMap<>();
    static final Map<Class<?>, Field[]> OUTPUT_TEXT_XDATA = new HashMap<>();

    static final Map<Class<?>, Field[]> INPUT_BINARY_XDATA = new HashMap<>();
    static final Map<Class<?>, Field[]> OUTPUT_BINARY_XDATA = new HashMap<>();

    public final static Predicate<Field> TEXT_XDATA_FILTER = f -> f.getAnnotation(XAText.class) != null;

    public final static Predicate<Field> BINARY_XDATA_FILTER = f -> f.getAnnotation(XABinary.class) != null;

    public static Field[] getInputTextXDataFields(Class<XData> c) {
        return getFieldsByFilter(c, INPUT_TEXT_XDATA, TEXT_XDATA_FILTER);
    }

    public static Field[] getOutputTextXDataFields(Class<XData> c) {
        return getFieldsByFilter(c, OUTPUT_TEXT_XDATA, TEXT_XDATA_FILTER);
    }

    public static Field[] getInputBinaryXDataFields(Class<XData> c) {
        return getFieldsByFilter(c, INPUT_BINARY_XDATA, BINARY_XDATA_FILTER);
    }

    public static Field[] getOutputBinaryXDataFields(Class<XData> c) {
        return getFieldsByFilter(c, OUTPUT_BINARY_XDATA, BINARY_XDATA_FILTER);
    }
}
