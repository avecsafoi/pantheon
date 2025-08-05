package kr.co.koscom.olympus.athena.base.ut;

import jakarta.annotation.Nonnull;

import java.nio.CharBuffer;

public class FixChars implements CharSequence {

    private final CharBuffer b;

    public FixChars(int n) {
        this.b = CharBuffer.allocate(n);
    }

    public int limit() {
        return b.limit();
    }

    public FixChars append(Object o) {
        if (o != null) b.append(o.toString());
        return this;
    }

    @Override
    public int length() {
        return b.length();
    }

    @Override
    public char charAt(int index) {
        return b.charAt(index);
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
