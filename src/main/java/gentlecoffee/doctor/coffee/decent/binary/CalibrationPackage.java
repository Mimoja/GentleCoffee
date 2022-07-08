package gentlecoffee.doctor.coffee.decent.binary;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Signed32P16;
import javolution.io.Struct;

public class CalibrationPackage extends Struct {
    // To read values:
    //   Enable NOTIFY on this characteristic
    //   Write zeros to all fields except the CalTarget
    //   Wait for a notify with the information you have requested.
    //   Ratiometric values will have 1.0 in the "DE1ReportedVal" field, and the measured value will be relative to that.
    //   Offset values will have 0.0 in the reported value, and the measured value will be relative to that.

    // To read factory values:
    //   Do the same as for a read, but use ReadFactory as the CalCommand

    // To write values:
    //   Set WriteKey to 0xCAFEF00D
    //   Set CalCommand to "Write"
    //   Set CalTarget to the target you want to change
    //   Set DE1Reported value to the value reported by the DE1
    //   Set Measured value to the value you measured
    //   A notify will be sent to show the results of the attempted write, if notifies are enabled

    // To reset values:
    //   Set WriteKey to 0xCAFEF00D
    //   Set CalCommand to "Reset"
    //   Set CalTarget to the target you want to change
    //   Leave everything else set to zero
    //   A notify will be sent to show the results of the attempted reset, if notifies are enabled
    public Unsigned32 WriteKey;   // Set this to 0xCAFEFOOD or your write will be ignored
    public Enum8 CalCommand; // See T_E_CalCommand
    public Unsigned8 CalTarget;  // See T_E_CaTargets

    public Signed32P16 DE1ReportedVal; // The value reported by the DE1
    public Signed32P16 MeasuredVal;    // The external calibrated value we measured


    public CalibrationPackage() {
        WriteKey = new Unsigned32();
        CalCommand = new Enum8<Command>(Command.values());
        CalTarget = new Unsigned8();
        DE1ReportedVal = inner(new Signed32P16());
        MeasuredVal = inner(new Signed32P16());

        WriteKey.set(0xCAFEF00D);
    }

    public CalibrationPackage(Target target, Command command) {
        WriteKey = new Unsigned32();
        CalCommand = new Enum8<>(Command.values());
        CalTarget = new Unsigned8();
        DE1ReportedVal = inner(new Signed32P16());
        MeasuredVal = inner(new Signed32P16());

        WriteKey.set(0xCAFEF00D);
        CalTarget.set(target.val());
        CalCommand.set(command);
    }

    public enum Command {
        Read,  // Read a calibration value
        Write,  // Write a calibration value
        Reset,  // Reset a calibration value to factory setting
        ReadFactory,  // Read the factory value of a calibration value
    }

    public enum Target {
        CalFlow(0),     // Flow, ratiometric, units don't matter.
        CalPressure(1), // Pressure, ratiometric, units don't matter.
        CalTemp(2),     // Temperature, offset, units matter. Use Celcius.
        CalError(255);   // Used to tell the app that it did something wrong.
        private byte numVal;

        Target(int numVal) {
            this.numVal = (byte) numVal;
        }

        public byte val() {
            return numVal;
        }
    }


}
