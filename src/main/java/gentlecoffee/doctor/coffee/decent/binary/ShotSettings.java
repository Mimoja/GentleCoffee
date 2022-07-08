package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned16P8;
import javolution.io.Struct;

public class ShotSettings extends Struct {

    // Settings for steam and hot water
    Unsigned8 SteamSettings; // Defines the steam shot
    Unsigned8 TargetSteamTemp; // Valid range is 140 - 160
    Unsigned8 TargetSteamLength;  // Length in seconds of steam
    Unsigned8 TargetHotWaterTemp; // Temperature of the mixed hot water
    Unsigned8 TargetHotWaterVol;     // How much water we'll need for hot water (so we know if we have enough)
    Unsigned8 TargetHotWaterLength;  // (DE1 only) Length of time for a shot (water vol is ignored)
    Unsigned8 TargetEspressoVol;     // So we know if we have enough water
    Unsigned16P8 TargetGroupTemp;       // So we know what to set the group to

    public ShotSettings(SteamSetting start, SteamSetting power, byte targetSteamTemp, byte targetSteamLength, byte targetHotWaterTemp, byte targetHotWaterVol , byte targetEspressoVol, float targetGroupTemp) {
        SteamSettings = new Unsigned8();
        TargetSteamTemp = new Unsigned8();
        TargetSteamLength = new Unsigned8();
        TargetHotWaterTemp = new Unsigned8();
        TargetHotWaterVol = new Unsigned8();
        TargetHotWaterLength = new Unsigned8();
        TargetEspressoVol = new Unsigned8();
        TargetGroupTemp = inner(new Unsigned16P8());

        SteamSettings.set((short) (start.val() | power.val()));
        TargetSteamTemp.set(targetSteamTemp);
        TargetSteamLength.set(targetSteamLength);
        TargetHotWaterTemp.set(targetHotWaterTemp);
        TargetHotWaterVol.set(targetHotWaterVol);
        TargetEspressoVol.set(targetEspressoVol);
        TargetGroupTemp.set(targetGroupTemp);
    }


    public enum SteamSetting {
        FastStart(0x80),  // Start the steam quickly and at higher pressure
        SlowStart(0x00),  // Start the steam slowly and at lower pressure (ie. No Bit set)
        HighPower(0x40),  // Run the steam at higher pressure
        LowPower(0x00);  // Run the steam at lower pressure
        private byte numVal;

        SteamSetting(int numVal) {
            this.numVal = (byte) numVal;
        }

        public byte val() {
            return numVal;
        }
    }

    @Override
    public boolean isPacked() {
        return true;
    }
}
