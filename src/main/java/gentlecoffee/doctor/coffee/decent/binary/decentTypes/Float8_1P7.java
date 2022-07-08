package gentlecoffee.doctor.coffee.decent.binary.decentTypes;

import javolution.io.Struct;


public class Float8_1P7 extends Struct {
    BitField exponent;
    BitField mantissa;

    public Float8_1P7() {
        exponent = new BitField(1);
        mantissa = new BitField(7);
    }

    public void set(float val) {
        if (val > 12.7) {
            exponent.set(0);
            mantissa.set((long) (val / 10));
        } else {
            exponent.set(1);
            mantissa.set((long) (val));
        }
    }

    public float get() {
        float val = mantissa.byteValue();
        if (exponent.byteValue() != 0) {
            val /= 10.0f;
        }
        return val;
    }
}

