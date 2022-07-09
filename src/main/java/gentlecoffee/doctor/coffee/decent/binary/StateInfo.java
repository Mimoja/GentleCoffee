package gentlecoffee.doctor.coffee.decent.binary;

import javolution.io.Struct;

public class StateInfo extends Struct {
    Enum8<MachineStates> state;
    Unsigned8 subState;

    public StateInfo() {
        this.state = new Enum8<MachineStates>(MachineStates.values());
        this.subState = new Unsigned8();
    }

    public StateInfo(MachineStates state, MachineSubStates subState) {
        this();

        this.state.set(state);
        this.subState.set((short) subState.val());
    }

    public MachineStates getState(){
        return state.get();
    }

    public MachineSubStates getSubState() {
        short substate = subState.get();
        if(substate > MachineSubStates.Error_NaN.ordinal())
            substate -= MachineSubStates.Error_NaN.val();

        return MachineSubStates.values()[substate];
    }

    @Override
    public boolean isPacked() {
        return true;
    }
}
