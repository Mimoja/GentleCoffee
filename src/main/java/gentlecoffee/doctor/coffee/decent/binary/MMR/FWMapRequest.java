package gentlecoffee.doctor.coffee.decent.binary.MMR;

import gentlecoffee.doctor.coffee.decent.binary.decentTypes.Unsigned24P0;
import javolution.io.Struct;

// Request that a firmware image be put in the memory mapped region for later reading
public class FWMapRequest extends Struct {
        Unsigned16 WindowIncrement; // Every time the MMR is read, add this byte offset to the MMR base address
        // If this field is non-zero, erase the firmware slot in question. (1 or 2)
        // Stays non-zero until firmware is erased
        Unsigned8  FWToErase;
        Unsigned8  FWToMap;          // Either 1 or 2

        // How to use FirstError:
        // Enable Notify for this characteristic
        // Write 0xFFFFFF to FirstError, FWToMap should be set to the required image. FWErase should not be set.
        // A notify will later arrive with the first address that needs repairing.
        // Write 0xFFFFFE in order to update FirstError to the next block that needs repairing.
        // 0xFFFFFF resets the search address to 0, 0xFFFFFE does not.
        // If there are no remaining errors, the notified FirstError value will be 0xFFFFFD
        Unsigned24P0 FirstError;

        public FWMapRequest(){
                WindowIncrement = new Unsigned16();
                FWToErase = new Unsigned8();
                FWToMap = new Unsigned8();
                FirstError = inner(new Unsigned24P0());
        }

        public enum FirstErrorState {
                Start(0xFFFFFF),  // Start the steam quickly and at higher pressure
                Next(0xFFFFFE),  // Start the steam slowly and at lower pressure (ie. No Bit set)
                Done(0xFFFFFD);   // Run the steam at higher pressure
                private long numVal;

                FirstErrorState(long numVal) {
                        this.numVal = (byte) numVal;
                }

                public long val() {
                        return numVal;
                }
        }

        public FWMapRequest(boolean erase, byte fwIndex){
                WindowIncrement = new Unsigned16();
                FWToErase = new Unsigned8();
                FWToMap = new Unsigned8();
                FirstError = inner(new Unsigned24P0());

                FWToErase.set((short) (erase ? 1:0));
                FWToMap.set(fwIndex);
        }

        public static FWMapRequest Repair(byte fwIndex, FirstErrorState state) {
                FWMapRequest repair = new FWMapRequest(false, fwIndex);
                repair.FirstError.setRaw(state.val());
                return repair;
        }

        @Override
        public boolean isPacked() {
                return true;
        }
}
