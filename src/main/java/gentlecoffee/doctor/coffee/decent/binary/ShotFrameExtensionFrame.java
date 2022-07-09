package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned8P4;
import javolution.io.Struct;

// Extension frame. The data in this section is added to existing frames. Extension frame 32 extends frame 0, 33 extends 1, etc.
public final class ShotFrameExtensionFrame extends Struct {
    Unsigned8 FrameToWrite;

    // In flow profiles, this is where the pressure OPV kicks in. In Pressure, flow starts being limited at this value.
    public Unsigned8P4 MaxFlowOrPressure;

    // This is the approximate maximum range of the flow or pressure. I suggest 10% of MaxFlowOrPressure for pressure, and 20% for flow.
    // Another way to view it: MaxFlowOrPressure is where the OPV kicks in. MaxFlowOrPressure+MaxFoPRange is the point that it is impossible to
    // exceed. The OPV will probably manage to retard the setpoint so that the output is retarded before this.
    // Another way to put it. A large range gives a soft and slow response to the OPV. A short range a very hard one. Too short will act weird,
    // as the setpoint will be retarded down to zero almost instantly, making things stop and start. Don't do it.
    public Unsigned8P4 MaxFoPRange;
    private Unsigned8[] Unused;

    public ShotFrameExtensionFrame() {
        FrameToWrite = new Unsigned8();
        MaxFlowOrPressure = inner(new Unsigned8P4());
        MaxFoPRange = inner(new Unsigned8P4());
        Unused = array(new Unsigned8[5]);
    }

    public ShotFrameExtensionFrame(byte frameIndex, short maxFlowOrPressure, short maxFoPRange) {
        this();

        FrameToWrite.set(frameIndex);
        MaxFlowOrPressure.set(maxFlowOrPressure );
        MaxFoPRange.set(maxFoPRange);
    }

    @Override
    public boolean isPacked() {
        return true;
    }
}