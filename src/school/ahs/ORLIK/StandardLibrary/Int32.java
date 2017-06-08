package school.ahs.ORLIK.StandardLibrary;

import school.ahs.ORLIK.Runtime.Blueprint;
import school.ahs.ORLIK.Runtime.Constructor;
import school.ahs.ORLIK.Runtime.Thing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Int32 extends Thing {

    static {
        blueprint = new Blueprint(new ArrayList<>());
    }

    private static Blueprint blueprint;

    public static Blueprint getBlueprint() {
        return blueprint;
    }

    private int value;

    public Int32(int value) {
        super(blueprint, new HashSet<>(), new HashSet<>());
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Int32 int32 = (Int32) o;
        return value == int32.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
