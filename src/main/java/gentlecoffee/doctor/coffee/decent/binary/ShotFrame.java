package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Float8_1P7;
import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned10P0;
import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned8P1;
import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned8P4;
import javolution.io.Struct;

public class ShotFrame extends Struct {
    Unsigned8 FrameToWrite;
    Unsigned8 Flag;       // See T_E_FrameFlags
    Unsigned8P4 SetVal;     // SetVal is a 4.4 fixed point number, setting either pressure or flow rate, as per mode
    Unsigned8P1 Temp;       // Temperature in 0.5 C steps from 0 - 127.5
    Float8_1P7 FrameLen;   // FrameLen is the length of this frame in seconds. It's a 1/7 bit floating point number as described in the F8_1_7 a struct
    Unsigned8P4 TriggerVal; // Trigger value. Could be a flow or pressure.
    Unsigned10P0 MaxVol;     // Exit current frame if the total shot volume/weight exceeds this value. 0 means ignore


    public ShotFrame() {
        FrameToWrite = new Unsigned8();
        Flag = new Unsigned8();
        SetVal = inner(new Unsigned8P4());
        Temp = inner(new Unsigned8P1());
        FrameLen = inner(new Float8_1P7());
        TriggerVal = inner(new Unsigned8P4());
        MaxVol = inner(new Unsigned10P0());
    }

    public ShotFrame(byte frameIndex, ShotFrameFlag flag, short targetVal, float temperature, float seconds, short triggerVal, short maxVolume) {
        this();

        FrameToWrite.set(frameIndex);
        Flag.set(flag.val());
        SetVal.set(targetVal);
        Temp.set(temperature);
        FrameLen.set(seconds);
        TriggerVal.set(triggerVal);
        MaxVol.set(maxVolume);
    }
    @Override
    public boolean isPacked() {
        return true;
    }
}