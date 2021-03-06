package gentlecoffee.doctor.coffee.decent.binary.MMR;

/**
 * // ENTRY(addr, rangenum, name, len, min, max, rw, mult, auto, desc)
 * // Min, max, mult only apply to len = 4 entries
 * // Min and max are after the value has been divided by mult, on a write.
 * // For example. Min 0.5, max 8.0, mult 10, means value written is /mult then checked against ranges. So actual limit is 5 - 80
 *
 * // Auto controls if a C macro will be used to generate code for this MMR
 * // 0 means no autogeneration.
 * // 1 means save the variable to a Model integer variable with the given name
 * // 2 means save the variable to a Model float variable with the given name
 *
 * // NOT_APP is defined in the .h file that includes this, so that it can be redefined as necessary.
 * // It means "NOT APPLICABLE"
 *
 *
 * App feature flags.
 *
 * These are used by the app to report what features it supports, so that the machine can decline to do
 * things that would crash old versions of the app.
 *
 * 0x0000_0001 : App supports "UserNotPresent" substate.
 *
 * The top bit of the flags is not available for use as parts of the system see it as signed.
 *
 * // Min, max, mult, only apply to len = 4 entries
 * // ENTRY(addr   , rangenum, name                , len    ,     min,        max,         rw,    mult, auto,   varname,  fname, function, desc)
 * ENTRY(0x00000000,        0, ExternalFlash       , 0xFFFFF, NOT_APP,    NOT_APP,    PERM_RW,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "Flash RW")
 * ENTRY(0x00800000,        1, HWConfig            ,       4, NOT_APP,    NOT_APP,  PERM_READ,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "HWConfig")
 * ENTRY(0x00800004,        2, Model               ,       4, NOT_APP,    NOT_APP,  PERM_READ,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "Model")
 * ENTRY(0x00800008,       19, CPUBoardModel       ,       4, NOT_APP,    NOT_APP,  PERM_READ,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "CPU Board Model * 1000. eg: 1100 = 1.1")
 * ENTRY(0x0080000C,       20, v13Model            ,       4, NOT_APP,    NOT_APP,  PERM_READ,     1000, 0, SFP::Unused, UNUSED, F_NoOp, "v1.3+ Firmware Model (Unset = 0, DE1 = 1, DE1Plus = 2, DE1Pro = 3, DE1XL = 4, DE1Cafe = 5)")
 * ENTRY(0x00800010,       21, CPUFirmwareBuild    ,       4, NOT_APP,    NOT_APP,  PERM_READ,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "CPU Board Firmware build number. (Starts at 1000 for 1.3, increments by 1 for every build)")
 * ENTRY(0x00802800,        3, DebugLen            ,       4,       0,       4096,    PERM_RT,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "How many characters in debug buffer are valid. Accessing this pauses BLE debug logging.")
 * ENTRY(0x00802804,        4, DebugBuffer         ,  0x1000, NOT_APP,    NOT_APP,    PERM_RT,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "Last 4K of output. Zero terminated if buffer not full yet. Pauses BLE debug logging.")
 * ENTRY(0x00803804,        5, DebugConfig         ,       4, NOT_APP,    NOT_APP,    PERM_RT,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "BLEDebugConfig. (Reading restarts logging into the BLE log)")
 * ENTRY(0x00803808,        6, FanThreshold        ,       4,       0,         50,    PERM_RW,        1, 0, SFP::Unused, UNUSED, F_NoOp, "Fan threshold temp")
 * ENTRY(0x0080380C,        7, TankTemp            ,       4,       0,         60,    PERM_RW,        1, 0, SFP::Unused, UNUSED, F_NoOp, "Tank water temp threshold.")
 * ENTRY(0x00803810,        8, HeaterUp1Flow       ,       4,     0.5,        8.0,    PERM_RW,       10, 0, SFP::Unused, UNUSED, F_NoOp, "HeaterUp Phase 1 Flow Rate")
 * ENTRY(0x00803814,        9, HeaterUp2Flow       ,       4,     0.5,        8.0,    PERM_RW,       10, 0, SFP::Unused, UNUSED, F_NoOp, "HeaterUp Phase 2 Flow Rate")
 * ENTRY(0x00803818,       10, WaterHeaterIdleTemp ,       4,     0.5,      100.0,    PERM_RW,       10, 0, SFP::Unused, UNUSED, F_NoOp, "Water Heater Idle Temperature")
 * ENTRY(0x0080381C,       11, GHCInfo             ,       4, NOT_APP, 0xFFFFFFFF,  PERM_READ,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "GHC Info Bitmask, 0x1 = GHC LED Controller Present, 0x2 = GHC Touch Controller_Present, 0x4 GHC Active, 0x80000000 = Factory Mode")
 * ENTRY(0x00803820,       12, PrefGHCMCI          ,       4, NOT_APP,    NOT_APP,  PERM_NONE,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "TODO")
 * ENTRY(0x00803824,       13, MaxShotPres         ,       4,       0, 0xFFFFFFFF,  PERM_NONE,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "TODO")
 * ENTRY(0x00803828,       14, TargetSteamFlow     ,       4,     0.4,        4.0,    PERM_RW,      100, 0, SFP::Unused, UNUSED, F_NoOp, "Target steam flow rate")
 * ENTRY(0x0080382C,       15, SteamStartSecs      ,       4,     0.1,        4.0,    PERM_RW,      100, 0, SFP::Unused, UNUSED, F_NoOp, "Seconds of high steam flow * 100. Valid range 0.0 - 4.0. 0 may result in an overheated heater. Be careful.")
 * ENTRY(0x00803830,       16, SerialN             ,       4,       0, 0xFFFFFFFF,  PERM_READ,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "Current serial number")
 * ENTRY(0x00803834,       17, HeaterV             ,       4,       0,       1230,    PERM_RW,  NOT_APP, 0, SFP::Unused, UNUSED, F_NoOp, "Nominal Heater Voltage (0, 120V or 230V). +1000 if it's a set value.")
 * ENTRY(0x00803838,       18, HeaterUp2Timeout    ,       4,     0.5,       30.0,    PERM_RW,       10, 0, SFP::Unused, UNUSED, F_NoOp, "HeaterUp Phase 2 Timeout")
 * ENTRY(0x0080383C,       22, CalFlowEst          ,       4,   0.125,        2.0,    PERM_RW,     1000, 0, SFP::Unused, UNUSED, F_NoOp, "Flow Estimation Calibration")
 * ENTRY(0x00803840,       23, FlushFlowRate       ,       4,       1,        8.0,    PERM_RW,       10, 0, SFP::Unused, UNUSED, F_NoOp, "Flush Flow Rate")
 * ENTRY(0x00803844,       24, FlushTemp           ,       4,       1,       99.0,    PERM_RW,       10, 0, SFP::Unused, UNUSED, F_NoOp, "Flush Temp")
 * ENTRY(0x00803848,       25, FlushTimeout        ,       4,       1,      250.0,    PERM_RW,       10, 0, SFP::Unused, UNUSED, F_NoOp, "Flush Timeout")
 * ENTRY(0x0080384C,       26, HotWaterFlowRate    ,       4,       0,        8.0,    PERM_RW,       10, 0, SFP::Unused, UNUSED, F_NoOp, "Hot Water Flow Rate")
 * ENTRY(0x00803850,       27, SteamPurgeMode      ,       4,       0,          1,   PERM_RWD,  NOT_APP, 1, SIP::SteamPurgeMode,   STEAMPURGEMODE, F_NoOp, "Steam Purge Mode")
 * ENTRY(0x00803854,       28, AllowUSBCharging    ,       4,       0,          1,    PERM_RW,  NOT_APP, 1, SIP::AllowUSBCharging, UNUSED, F_NoOp, "Allow USB charging")
 * ENTRY(0x00803858,       29, AppFeatureFlags     ,       4,       0, 0x7FFFFFFF,    PERM_RW,  NOT_APP, 1, SIP::AppFeatureFlags,  UNUSED, F_NoOp, "App Feature Flags")
 * ENTRY(0x0080385C,       30, RefillKitPresent    ,       4,       0, 0x7FFFFFFF,    PERM_RW,  NOT_APP, 1, SIP::RefillKitPresent, UNUSED, F_NoOp, "Refill Kit Present")
 * ENTRY(0x00803860,       31, UserPresent         ,       4,       0,          1,    PERM_RW,  NOT_APP, 1, SIP::UserPresent, UNUSED, F_UserPresent, "Is User Present")
 */
public enum MMRRegions {

    ExternalFlash(0x0000, 0x0000, 0x000FFFFF),       //!< ExternalFlash
    HWConfig(0x0000),            //!< HWConfig
    Model(0x0004),            //!< Model
    DebugLen(0x2800),            //!< DebugLen
    DebugBuffer(0x2804, 0x1000), //!< DebugBuffer
    DebugConfig(0x3804),         //!< DebugConfig
    FanThreshold(0x3808, ValueDeviders.DEV_1),        //!< FanThreshold
    TankTemp(0x380C, ValueDeviders.DEV_1),            //!< TankTemp
    HeaterUp1Flow(0x3810, ValueDeviders.DEV_10),       //!< HeaterUp1Flow
    HeaterUp2Flow(0x3814, ValueDeviders.DEV_10),       //!< HeaterUp2Flow
    WaterHeaterIdleTemp(0x3818, ValueDeviders.DEV_10),//!< WaterHeaterIdleTemp
    GHCInfo(0x381C),            //!< GHCInfo
    PrefGHCMCI(0x3820),         //!< PrefGHCMCI
    MaxShotPres(0x3824),        //!< MaxShotPres
    TargetSteamFlow(0x3828, ValueDeviders.DEV_100),    //!< TargetSteamFlow
    SteamStartSecs(0x382C, ValueDeviders.DEV_100),     //!< SteamStartSecs
    SerialN(0x3830),            //!< SerialN
    HeaterV(0x3834),            //!< HeaterV
    HeaterUp2Timeout(0x3838, ValueDeviders.DEV_10),   //!< HeaterUp2Timeout
    CPUBoardModel(0x0008),      //!< CPUBoardModel
    v13Model(0x000C),           //!< v13Model
    CPUFirmwareBuild(0x0010),   //!< CPUFirmwareBuild
    CalFlowEst(0x383C, ValueDeviders.DEV_10),         //!< CalFlowEst
    FlushFlowRate(0x3840, ValueDeviders.DEV_10), //!<FlushFlowRate
    FlushTemp(0x3844, ValueDeviders.DEV_10), //!<FlushFlowRate
    FlushTimeout(0x3848, ValueDeviders.DEV_10), //!<FlushFlowRate
    HotWaterFlowRate(0x384C, ValueDeviders.DEV_10), //!<FlushFlowRate
    SteamPurgeMode(0x3850), //!<SteamPurgeMode
    AllowUSBCharging(0x3854), //!<AllowUSBCharging

    /**
     * App feature flags.
     *
     * These are used by the app to report what features it supports, so that the machine can decline to do
     * things that would crash old versions of the app.
     *
     * 0x0000_0001 : App supports "UserNotPresent" substate.
     *
     * The top bit of the flags is not available for use as parts of the system see it as signed.
     */
    AppFeatureFlags(0x858), //!<AppFeatureFlags
    RefillKitPresent(0x85C), //!<RefillKitPresent
    UserPresent(0x860), //!<UserPresent
    ;
    int addr_hi;
    int addr_lo;
    ValueDeviders divider = null;
    private int len;

    MMRRegions(int addr_hi, int addr_lo, int len) {
        this.addr_hi = addr_hi;
        this.addr_lo = addr_lo;
        this.len = len;
    }

    MMRRegions(int addr_lo, int len) {
        this.addr_hi = 0x0800;
        this.addr_lo = addr_lo;
        this.len = len;
    }

    MMRRegions(int addr_lo) {
        this.addr_hi = 0x0800;
        this.addr_lo = 4;
        this.len = len;
    }

    MMRRegions(int addr_lo, ValueDeviders divider) {
        this.addr_hi = 0x0800;
        this.addr_lo = 4;
        this.len = len;
        divider = divider;
    }

    public int getLen() {
        return len;
    }

    enum ValueDeviders {
        DEV_1,
        DEV_10,
        DEV_100,
        DEV_1000,
    }
}
