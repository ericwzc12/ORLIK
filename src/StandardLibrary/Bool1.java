package StandardLibrary;

import java.util.Objects;
import school.ahs.ORLIK.Runtime.Thing;

public class Bool1 extends Thing {

    public final boolean value;

    public Bool1(String statement, Runtime runtime) {
        this.value = Boolean.parseBoolean(statement);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bool1 bool1 = (Bool1) o;
        return value == bool1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
