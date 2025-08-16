package kr.co.koscom.olympus.pb.ab.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybatisflex.annotation.Id;
import kr.co.koscom.olympus.pb.ab.data.PBObject;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PBEntity extends PBObject {

    @JsonIgnore
    public Object[] getPKs() {
        List<Field> l = FieldUtils.getAllFieldsList(this.getClass());
        List<Object> r = new ArrayList<>();
        for (Field f : l) {
            if (f.getAnnotation(Id.class) != null) {
                boolean b = !f.canAccess(this);
                if (b) f.setAccessible(true);
                try {
                    r.add(f.get(this));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return r.toArray();
    }

    @JsonIgnore
    public void setPKs(Object[] pks) {
        List<Field> l = FieldUtils.getAllFieldsList(this.getClass());
        int i = 0, z = pks.length;
        for (Field f : l) {
            if (f.getAnnotation(Id.class) != null) {
                boolean b = !f.canAccess(this);
                if (b) f.setAccessible(true);
                try {
                    f.set(this, pks[i++]);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (i != z)
            throw new IndexOutOfBoundsException("Incorrect %s's Id length(%d): in:(%d)[%s]".formatted(this.getClass().getSimpleName(), l.size(), pks.length, pks));
    }
}
