package gentlecoffee.doctor.coffee.decent.binary.decentTypes;

import javolution.io.Struct;


public class Unsigned10P0 extends Struct {
    Unsigned16 value;
    public Unsigned10P0() {
        value = new Unsigned16(10);
    }

    public void set(short val) {
        value.set(val & 0x3FF);
    }

    public short get(){
        return (short) value.get();
    }
}

