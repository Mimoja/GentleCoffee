package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned16P12;
import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned16P8;
import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned24P16;
import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned8P4;
import javolution.io.Struct;

public class ShotSample extends Struct {
    // Instant snapshot during a shot
    public Unsigned16 SampleTime;   // Time since start of shot, in halfcycles
    public Unsigned16P12 GroupPressure; // Pressure at group
    public Unsigned16P12 GroupFlow;     // Estimated Flow at group
    public Unsigned16P8 MixTemp;       // Water Temperature entering group
    public Unsigned24P16 HeadTemp;      // Temperature of water at showerhead
    public Unsigned16P12 SetMixTemp;    // Set temperature. 0 if no target.
    public Unsigned16P12 SetHeadTemp;   // Set shower head temp. 0 if no target.
    public Unsigned8P4 SetGroupPressure; // Set pressure. 0 if not set.
    public Unsigned8P4 SetGroupFlow;  // Set flow. 0 if not set.
    public Unsigned8 FrameNumber;   // Frame we are currently in.
    public Unsigned8 SteamTemp;     // Steam metal temp

    public ShotSample() {
        SampleTime = new Unsigned16();
        GroupPressure = inner(new Unsigned16P12());
        GroupFlow = inner(new Unsigned16P12());
        MixTemp = inner(new Unsigned16P8());
        HeadTemp = inner(new Unsigned24P16());
        SetMixTemp = inner(new Unsigned16P12());
        SetHeadTemp = inner(new Unsigned16P12());
        SetGroupPressure = inner(new Unsigned8P4());
        SetGroupFlow = inner(new Unsigned8P4());
        FrameNumber = new Unsigned8();
        SteamTemp = new Unsigned8();
    }

    @Override
    public boolean isPacked() {
        return true;
    }
}
