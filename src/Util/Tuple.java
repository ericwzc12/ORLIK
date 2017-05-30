package Util;

import java.util.Objects;

public class Tuple<L, R> {

    public final L l;
    public final R r;

    public Tuple(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public R getR() {
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(l, tuple.l) &&
                Objects.equals(r, tuple.r);
    }

    @Override
    public int hashCode() {
        return Objects.hash(l, r);
    }

}
