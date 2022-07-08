package gentlecoffee.doctor.coffee.decent.binary;

import javolution.io.Struct;

// Tail extension frame. One of these goes after the sequence of frames, and has extra global info about t
public final class ShotFrameTail extends Struct {
    Unsigned8 FrameToWrite;
    // High bit of the U16 is 1 if we want to ignore preinfusion volume after preinfusion is done.
    // This is to prevent preinfusion from exceeding the max volume.
    // The total allowed volume of the shot
    public BitField IgnorePreinfusionVolume;
    private BitField Padding;
    public BitField MaxTotalVolume;
    private Unsigned8[] Unused;

    public ShotFrameTail() {
        FrameToWrite = new Unsigned8();
        IgnorePreinfusionVolume = new BitField(1);
        Padding = new BitField(5);
        MaxTotalVolume = new BitField(10);
        Unused = array(new Unsigned8[5]);
    }

    public ShotFrameTail(byte frameIndex, boolean ignorePreinfusionVolume, int maxTotalVolume) {
        FrameToWrite = new Unsigned8();
        IgnorePreinfusionVolume = new BitField(1);
        Padding = new BitField(5);
        MaxTotalVolume = new BitField(10);
        Unused = array(new Unsigned8[5]);

        FrameToWrite.set(frameIndex);
        IgnorePreinfusionVolume.set((short) (ignorePreinfusionVolume ? 0x1 : 0x0));
        MaxTotalVolume.set((short) (maxTotalVolume & 0x3FF));
    }

    @Override
    public boolean isPacked() {
        return true;
    }
}