package kr.co.koscom.olympus.pb.ab.data.io;

import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;

import java.lang.reflect.Field;

public class PBDataT {
    public PBDataT p;
    public Class<?> c;
    public Object o;
    public Field f;
    public PBA a;

    public PBDataT(PBDataT p, Class<?> c, Object o, Field f, PBA a) {
        this.p = p;
        this.c = c;
        this.o = o;
        this.f = f;
        this.a = a;
    }

    public boolean refClass(Class<?> y) {
        for (PBDataT q = p; q != null; q = q.p) if (y == q.c) return true;
        return false;
    }

    public boolean refObject(Object y) {
        for (PBDataT q = p; q != null; q = q.p) if (y == q.o) return true;
        return false;
    }
}
