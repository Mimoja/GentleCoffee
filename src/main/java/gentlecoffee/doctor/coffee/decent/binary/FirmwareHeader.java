package gentlecoffee.doctor.coffee.decent.binary;

import javolution.io.Struct;

public final class FirmwareHeader extends Struct {
    public Unsigned32 CheckSum;    // The checksum of the rest of the encrypted image. Includes "CheckSums" + "Data" fields, not "Header"
    public Unsigned32 BoardMarker; // 0xDE100001
    public Unsigned32 Version;     // The version of this image
    public Unsigned32 ByteCount;   // Number of bytes in image body, ignoring padding.
    public Unsigned32 CPUBytes;    // The first CPUBytes of the image are for the CPU. Remainder is for BLE.
    public Unsigned32 Unused;      // Blank spot for future extension. Always zero for now
    public Unsigned32 DCSum;       // Checksum of decrypted image
    public Unsigned8[] IV;       // Initialization vector for the firmware. 32 bytes
    public Unsigned32 HSum;        // Checksum of this header.

    public FirmwareHeader() {
        CheckSum = new Unsigned32();
        BoardMarker = new Unsigned32();
        Version = new Unsigned32();
        ByteCount = new Unsigned32();
        CPUBytes = new Unsigned32();
        Unused = new Unsigned32();
        DCSum = new Unsigned32();
        IV = array(new Unsigned8[32]);
        HSum = new Unsigned32();
    }

    public FirmwareHeader(long checkSum, long boardMarker, long version, long byteCount, long CPUBytes, long unused, long DCSum, short IV[], long HSum) {
        this.CheckSum = new Unsigned32();
        this.BoardMarker = new Unsigned32();
        this.Version = new Unsigned32();
        this.ByteCount = new Unsigned32();
        this.CPUBytes = new Unsigned32();
        this.Unused = new Unsigned32();
        this.DCSum = new Unsigned32();
        this.IV = array(new Unsigned8[32]);
        this.HSum = new Unsigned32();

        this.CheckSum.set(checkSum);
        this.BoardMarker.set(boardMarker);
        this.Version.set(version);
        this.ByteCount.set(byteCount);
        this.CPUBytes.set(CPUBytes);
        this.Unused.set(0);
        this.DCSum.set(DCSum);
        for(int i = 0;i < 32; i++) {
            this.IV[i].set(IV[i]);
        }

        this.HSum.set(HSum);
    }
    @Override
    public boolean isPacked() {
        return true;
    }
}