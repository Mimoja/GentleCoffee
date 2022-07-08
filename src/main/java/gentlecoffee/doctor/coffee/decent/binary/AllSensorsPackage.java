package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned16P8;
import javolution.io.Struct;

public class AllSensorsPackage extends Struct {
        // The whole bang tooty
        Unsigned16P8 ColdWater;
        Unsigned16P8 HotWater;
        Unsigned16P8 MixedWater;
        Unsigned16P8 EspressoWater;
        Unsigned16P8 InCaseAmbient;
        Unsigned16P8 HeatSink;
        Unsigned16P8 SteamHeater;
        Unsigned16P8 WaterHeater;

        public AllSensorsPackage() {
                ColdWater = inner(new Unsigned16P8());
                HotWater = inner(new Unsigned16P8());
                MixedWater = inner(new Unsigned16P8());
                EspressoWater = inner(new Unsigned16P8());
                InCaseAmbient = inner(new Unsigned16P8());
                HeatSink = inner(new Unsigned16P8());
                SteamHeater = inner(new Unsigned16P8());
                WaterHeater = inner(new Unsigned16P8());
        }
        @Override
        public boolean isPacked() {
                return true;
        }
}
