package gentlecoffee.doctor.coffee.decent.binary;

import javolution.io.Struct;

public final class ShotDescHeader extends Struct {
    Unsigned8 HeaderV;           // Set to 1 for this type of shot description
    Unsigned8 NumberOfFrames;    // Total number of unextended frames.
    Unsigned8 NumberOfPreinfuseFrames; // Number of frames that are preinfusion
    Unsigned8 MinimumPressure;   // In flow priority modes, this is the minimum pressure we'll allow
    Unsigned8 MaximumFlow;       // In pressure priority modes, this is the maximum flow rate we'll allow

    public ShotDescHeader() {
        this.HeaderV = new Unsigned8();
        this.HeaderV.set((short) 1);
        this.NumberOfFrames = new Unsigned8();
        this.NumberOfPreinfuseFrames = new Unsigned8();
        this.MinimumPressure = new Unsigned8();
        this.MaximumFlow = new Unsigned8();
    }

    public ShotDescHeader(short numberOfFrames, short numberOfPreinfuseFrames, int minimumPressure, int maximumFlow) {
        this.HeaderV = new Unsigned8();
        this.HeaderV.set((short) 1);
        this.NumberOfFrames = new Unsigned8();
        this.NumberOfPreinfuseFrames = new Unsigned8();
        this.MinimumPressure = new Unsigned8();
        this.MaximumFlow = new Unsigned8();
        NumberOfFrames.set(numberOfFrames);
        NumberOfPreinfuseFrames.set(numberOfPreinfuseFrames);
        if(minimumPressure > 0xFF * 16 || minimumPressure < 0)
            throw  new IllegalArgumentException();
        MinimumPressure.set((short) (minimumPressure / 16));
        if(maximumFlow > 0xFF * 16|| maximumFlow < 0)
            throw  new IllegalArgumentException();
        MaximumFlow.set((short) (maximumFlow / 16));
    }
}