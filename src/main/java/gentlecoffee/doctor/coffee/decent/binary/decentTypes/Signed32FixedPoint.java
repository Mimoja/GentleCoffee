package gentlecoffee.doctor.coffee.decent.binary.decentTypes;

import javolution.io.Struct;

public class Signed32FixedPoint extends Struct {
    int expBits;
    public Signed32 value;

    public Signed32FixedPoint(int expBits) {
        this.expBits = expBits;
        this.value = new Signed32();
    }

    public void set(double val) {
        long divider = 1 << expBits;
        if (Math.abs(val * divider) >= 0xEFFFFFFF)
            throw new IllegalArgumentException();

        long int_rep = (long) (val * divider);
        value.set((int)int_rep);
    }

    public double get() {
        int divider = 1 << expBits;
        return value.get() / (double) divider;
    }
}
