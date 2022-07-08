package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned16P8;
import javolution.io.Struct;

public class TemperaturePackage extends Struct {

    // Range is 0 - 255 deg C, with 256 steps between each degree
    public class CurrentTempatures extends Struct {
        public Unsigned16P8 WaterHeater;
        public Unsigned16P8 SteamHeater;
        public Unsigned16P8 GroupHeater;
        public Unsigned16P8 ColdWater;
        public CurrentTempatures() {
            WaterHeater = inner(new Unsigned16P8());
            SteamHeater = inner(new Unsigned16P8());
            GroupHeater = inner(new Unsigned16P8());
            ColdWater = inner(new Unsigned16P8());
        }
    }

    public class TargetTemperatures extends Struct {
        public Unsigned16P8 WaterHeater;
        public Unsigned16P8 SteamHeater;
        public Unsigned16P8 GroupHeater;
        public Unsigned16P8 ColdWater;

        public TargetTemperatures() {
            WaterHeater = inner(new Unsigned16P8());
            SteamHeater = inner(new Unsigned16P8());
            GroupHeater = inner(new Unsigned16P8());
            ColdWater = inner(new Unsigned16P8());
        }
    }

    public CurrentTempatures current;
    public TargetTemperatures target;

    public TemperaturePackage(){
        CurrentTempatures current = inner(new CurrentTempatures());
        TargetTemperatures target = inner(new TargetTemperatures());
    }
    @Override
    public boolean isPacked() {
        return true;
    }
}
