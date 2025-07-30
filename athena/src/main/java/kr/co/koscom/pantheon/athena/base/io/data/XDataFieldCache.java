package kr.co.koscom.pantheon.athena.base.io.data;

import kr.co.koscom.pantheon.athena.base.io.data.annotations.XABinary;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAText;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class XDataFieldCache extends ClassFieldsCache {

    static final Map<Class<?>, Field[]> INPUT_TEXT_XDATA = new HashMap<>();
    static final Map<Class<?>, Field[]> OUTPUT_TEXT_XDATA = new HashMap<>();

    static final Map<Class<?>, Field[]> INPUT_BINARY_XDATA = new HashMap<>();
    static final Map<Class<?>, Field[]> OUTPUT_BINARY_XDATA = new HashMap<>();

    public static Field[] getInputTextXDataFields(Class<XData> c) {
        return getFields(INPUT_TEXT_XDATA, c, XAText.class);
    }

    public static Field[] getOutputTextXDataFields(Class<XData> c) {
        return getFields(OUTPUT_TEXT_XDATA, c, XAText.class);
    }

    public static Field[] getInputBinaryXDataFields(Class<XData> c) {
        return getFields(INPUT_BINARY_XDATA, c, XABinary.class);
    }

    public static Field[] getOutputBinaryXDataFields(Class<XData> c) {
        return getFields(OUTPUT_BINARY_XDATA, c, XABinary.class);
    }
}
