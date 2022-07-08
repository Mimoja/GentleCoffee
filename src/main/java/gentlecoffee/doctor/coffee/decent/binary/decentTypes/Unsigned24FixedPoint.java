package gentlecoffee.doctor.coffee.decent.binary.decentTypes;

import javolution.io.Struct;

public abstract class Unsigned24FixedPoint extends Struct {
    public Unsigned8 Hi;
    public Unsigned8 Mi;
    public Unsigned8 Lo;
    int expBits;

    public Unsigned24FixedPoint(int expBits) {
        this.expBits = expBits;
        this.Hi = new Unsigned8();
        this.Mi = new Unsigned8();
        this.Lo = new Unsigned8();
    }

    public long getRaw() {
        return (Hi.get() << 16) + (Mi.get() << 8) + (Lo.get() << 0);
    }

    public void setRaw(long val) {
        Hi.set((short) ((val >> 16) & 0xFF));
        Mi.set((short) ((val >>  8) & 0xFF));
        Lo.set((short) ((val >>  0) & 0xFF));
    }

    public void set(double val) {
        int divider = 1 << expBits;
        if (val * divider >= 0xFFFFFF || val < 0)
            throw new IllegalArgumentException();

        long int_rep = (long) (val * divider);
        setRaw(int_rep);
    }

    public double get() {
        int divider = 1 << expBits;
        return getRaw() / (float) divider;
    }

    @Override
    public boolean isPacked() {
        return true;
    }

    public void set(long val){
        setRaw(val);
    }
}
