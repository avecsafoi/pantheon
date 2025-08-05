package kr.co.koscom.olympus.athena.base.ut;

import jakarta.annotation.Nonnull;

public class VarChars implements CharSequence {

    private final StringBuilder b;

    public VarChars() {
        this(16);
    }

    public VarChars(int n) {
        this.b = new StringBuilder(n);
    }

    public VarChars append(Object o) {
        if (o != null) b.append(o);
        return this;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public @Nonnull CharSequence subSequence(int start, int end) {
        return b.subSequence(start, end);
    }

    @Override
    public @Nonnull String toString() {
        return b.toString();
    }
}
