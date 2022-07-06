package gentlecoffee.doctor.coffee.decent.binary;

import javolution.io.Struct;

import static gentlecoffee.doctor.coffee.decent.binary.MachineSubStates.*;

public class StateInfo extends Struct {
    Enum8<MachineStates> state;
    Enum8<MachineSubStates> subState;

    public StateInfo() {
        this.state = new Enum8<MachineStates>(MachineStates.values());
        this.subState = new Enum8<MachineSubStates>(MachineSubStates.values());
    }

    public StateInfo(MachineStates state, MachineSubStates subState) {
        this.state = new Enum8<MachineStates>(MachineStates.values());
        this.subState = new Enum8<MachineSubStates>(MachineSubStates.values());
        this.state.set(state);
        this.subState.set(subState);
    }
}
