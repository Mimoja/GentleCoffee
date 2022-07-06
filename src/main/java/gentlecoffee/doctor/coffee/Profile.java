package gentlecoffee.doctor.coffee;

import java.util.ArrayList;
import java.util.List;

public abstract class Profile<T extends ProfileStep> {

    public ProfileData data;
    private ArrayList<DataChangeCommand<ProfileData>> changeList;
    private Integer undoDepth;

    private List<T> steps;
    public List<T> getSteps() {
        return steps;
    }

    public void setSteps(List<T> steps) {
        this.steps = steps;
    }


    public Profile() {
        data = new ProfileData();
        changeList = new ArrayList<>();
        undoDepth = 0;
    }

    static Profile fromString() {
        return null;
    }

    public Profile fromProfile(Profile old) {
        Profile profile = null;
        try {
            profile = old.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        profile.data = old.data;
        profile.copyParametersFromProfile(old);
        return profile;
    }

    public void changeParameter(DataChangeCommand<ProfileData> command) {
        // No chance to redo from here
        if (undoDepth > 0) {
            int size = changeList.size();
            changeList.subList(size - undoDepth, size).clear();
            undoDepth = 0;
        }

        command.setBefore(data);
        this.data = command.execute(this.data);
        command.setAfter(data);

        changeList.add(command);
    }

    protected abstract void processChangeCommand(ChangeCommand command);

    public abstract void copyParametersFromProfile(Profile newProfile);

    public void undo() {
        if (changeList.isEmpty())
            return;

        int size = changeList.size();
        DataChangeCommand<ProfileData> changeCommand = changeList.get(size - 1 - undoDepth);
        this.data = changeCommand.getBefore();
        // copyParametersFromProfile(changeCommand.getBefore());
        undoDepth++;
    }

    public void redo() {
        if (undoDepth == 0) {
            return;
        }
        undoDepth--;
        int size = changeList.size();
        DataChangeCommand<ProfileData> changeCommand = changeList.get(size - 1 - undoDepth);
        this.data = changeCommand.getAfter();
        // copyParametersFromProfile(changeCommand.getAfter());
    }
}
