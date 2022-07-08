package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned8P4;
import javolution.io.Struct;

public final class ShotDescHeader extends Struct {
    public Unsigned8 HeaderV;           // Set to 1 for this type of shot description
    public Unsigned8 NumberOfFrames;    // Total number of unextended frames.
    public Unsigned8 NumberOfPreinfuseFrames; // Number of frames that are preinfusion
    public Unsigned8P4 MinimumPressure;   // In flow priority modes, this is the minimum pressure we'll allow
    public Unsigned8P4 MaximumFlow;       // In pressure priority modes, this is the maximum flow rate we'll allow

    public ShotDescHeader() {
        this.HeaderV = new Unsigned8();
        this.NumberOfFrames = new Unsigned8();
        this.NumberOfPreinfuseFrames = new Unsigned8();
        this.MinimumPressure = inner(new Unsigned8P4());
        this.MaximumFlow = inner(new Unsigned8P4());

        this.HeaderV.set((short) 1);
    }

    public ShotDescHeader(byte numberOfFrames, byte numberOfPreinfuseFrames, float minimumPressure, float maximumFlow) {
        this.HeaderV = new Unsigned8();
        this.NumberOfFrames = new Unsigned8();
        this.NumberOfPreinfuseFrames = new Unsigned8();
        this.MinimumPressure = inner(new Unsigned8P4());
        this.MaximumFlow = inner(new Unsigned8P4());

        HeaderV.set((short) 1);
        NumberOfFrames.set(numberOfFrames);
        NumberOfPreinfuseFrames.set(numberOfPreinfuseFrames);
        MinimumPressure.set(minimumPressure);
        MaximumFlow.set(maximumFlow);
    }

    @Override
    public boolean isPacked() {
        return true;
    }
}