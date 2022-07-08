package gentlecoffee.doctor.coffee.decent.binary.decentTypes;

import javolution.io.Struct;

public class Unsigned8FixedPoint extends Struct {
    int expBits;
    public Unsigned8 value;

    public Unsigned8FixedPoint(int expBits) {
        this.expBits = expBits;
        this.value = new Unsigned8();
    }

    public void set(float val) {
        int divider = 1 << expBits;
        if (val * divider >= 0xFF || val < 0)
            throw new IllegalArgumentException();

        float int_rep = val * divider;
        value.set((short) int_rep);
    }

    public float get() {
        int divider = 1 << expBits;
        return value.get() / (float) divider;
    }
}
