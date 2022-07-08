package gentlecoffee.doctor.coffee.decent.binary.MMR;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned24P0;
import javolution.io.Struct;

public class WriteMMRRequest extends Struct {
    Unsigned8 Len;       // Length of data
    Unsigned24P0 Address;   // Address of window. Will autoincrement if set up in MapRequest
    Unsigned8[] Data;  // If data reaches past the end of a region, bytes will be zero filled

    public WriteMMRRequest() {
        Len = new Unsigned8();
        Address = new Unsigned24P0();
        Data = array(new Unsigned8[16]);
    }

    public WriteMMRRequest(byte len, long address, byte[] data) {
        Len = new Unsigned8();
        Address = new Unsigned24P0();
        Data = array(new Unsigned8[16]);

        if (data.length < len)
            throw new IllegalArgumentException();

        Len.set(len);
        Address.set(address);

        for (int i = 0; i < len; i++) {
            Data[i].set(data[i]);
        }
    }
    @Override
    public boolean isPacked() {
        return true;
    }
};
