package kr.co.koscom.olympus.athena.base;

import jakarta.annotation.Nonnull;

public class CharPointer implements CharSequence {

    private final StringBuilder sb;

    public CharPointer(CharSequence cs) {
        this.sb = new StringBuilder();
        this.sb.append(cs);
    }

    public CharPointer append(Object o) {
        sb.append(o);
        return this;
    }

    public CharPointer clear() {
        this.sb.delete(0, this.sb.length());
        return this;
    }

    @Override
    public int length() {
        return sb.length();
    }

    @Override
    public char charAt(int index) {
        return sb.charAt(index);
    }

    @Override
    public @Nonnull CharSequence subSequence(int start, int end) {
        return new CharPointer(sb.subSequence(start, end));
    }

    @Override
    public @Nonnull String toString() {
        return sb.toString();
    }
}
