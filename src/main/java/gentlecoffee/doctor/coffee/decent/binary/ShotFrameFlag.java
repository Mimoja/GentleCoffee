package gentlecoffee.doctor.coffee.decent.binary;

public enum ShotFrameFlag {

    // FrameFlag of zero and pressure of 0 means end of shot, unless we are at the tenth frame, in which case it's the end of shot no matter what
    CtrlF(0x01), // Are we in Pressure or Flow priority mode?
    DoCompare(0x02), // Do a compare, early exit current frame if compare true
    DC_GT(0x04), // If we are doing a compare, then 0 = less than, 1 = greater than
    DC_CompF(0x08), // Compare Pressure or Flow?
    TMixTemp(0x10), // Disable shower head temperature compensation. Target Mix Temp instead.
    Interpolate(0x20), // Hard jump to target value, or ramp?
    IgnoreLimit(0x40), // Ignore minimum pressure and max flow settings

    // Inverse of the flags above
    CtrlP,
    NoCompare,
    DC_LT,
    DC_CompP,
    TBasketTemp,
    DontInterpolate,
    RespectLimit;

    private short numVal;

    ShotFrameFlag(int numVal) {
        this.numVal = (short) numVal;
    }

    ShotFrameFlag() {
        this.numVal = 0;
    }

    public short val() {
        return numVal;
    }

}
