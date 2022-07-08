package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned16P8;
import javolution.io.Struct;

public class WaterlevelPackage extends Struct {
    // Writes to Level will be ignored
    Unsigned16P8 Level;
    // Writes to this set the water level refill trigger point.
    // Invalid writes will be clipped to valid levels
    // To disable refill, set StartFillLevel to 0.0
    Unsigned16P8 StartFillLevel;

    public WaterlevelPackage(float refilLevel) {
        Level = inner(new Unsigned16P8());
        StartFillLevel = inner(new Unsigned16P8());

        StartFillLevel.set(refilLevel);
    }

    @Override
    public boolean isPacked() {
        return true;
    }
}
