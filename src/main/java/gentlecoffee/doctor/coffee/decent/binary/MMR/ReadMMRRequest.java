package gentlecoffee.doctor.coffee.decent.binary.MMR;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned24P0;
import javolution.io.Struct;

public class ReadMMRRequest extends Struct {
    Unsigned8  Len;       // Length of data to read, in words-1. ie. 0 = 4 bytes, 1 = 8 bytes, 255 = 2014 bytes, etc.
    Unsigned24P0 Address;   // Address of window. Will autoincrement if set up in MapRequest
    Unsigned8[]  Data;  // If data reaches past the end of a region, bytes will be zero filled

    public ReadMMRRequest(){
        Len = new Unsigned8();
        Address = new Unsigned24P0();
        Data = array(new Unsigned8[16]);
    }

    public ReadMMRRequest(byte wordcount, long address){
        Len = new Unsigned8();
        Address = new Unsigned24P0();
        Data = array(new Unsigned8[16]);

        Len.set(wordcount);
        Address.set(address);
    }
    @Override
    public boolean isPacked() {
        return true;
    }
};
