package gentlecoffee.doctor.coffee.decent.binary.decentTypes;

import javolution.io.Struct;

public class Unsigned16FixedPoint extends Struct {
    int expBits;
    public Unsigned16 value;

    public Unsigned16FixedPoint(int expBits) {
        this.expBits = expBits;
        this.value = new Unsigned16();
    }

    public void set(float val) {
        int divider = 1 << expBits;
        if (val * divider >= 0xFFFF || val < 0)
            throw new IllegalArgumentException();

        long int_rep = (long) (val * divider);
        value.set((short) int_rep);
    }

    public float get() {
        int divider = 1 << expBits;
        return value.get() / (float) divider;
    }
}
