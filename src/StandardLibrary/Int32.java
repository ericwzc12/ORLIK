package StandardLibrary;

import java.util.Objects;

public class Int32 {

    public final int value;

    public Int32(String statement, Runtime runtime) {
        value = Integer.parseInt(statement);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Int32 int32 = (Int32) o;
        return value == int32.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
