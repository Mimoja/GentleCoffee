package doctor.coffee;

import java.util.ArrayList;
import java.util.List;

public abstract class Profile {

    public String title;
    public String author;
    public String notes;
    public ProfileType type;
    public Float targetBrewWeight;
    public Float inputWeight;
    private ArrayList<ChangeCommand> changeList;
    private Integer undoDepth;

    public Profile() {
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

        profile.copyGenericParametersFromProfile(old);
        profile.copyParametersFromProfile(old);
        return profile;
    }

    public abstract List<ProfileStep> getSteps();

    public abstract void setSteps(List<ProfileStep> steps);

    public void changeParameter(ChangeCommand command) {
        // No chance to redo from here
        if (undoDepth > 0) {
            int size = changeList.size();
            changeList.subList(size - undoDepth, size).clear();
            undoDepth = 0;
        }

        System.out.println("Changing param " + command.getName());

        command.setBefore(fromProfile(this));

        switch (command.getName()) {
            case "title":
                this.title = (String) command.getValue();
                break;
            case "author":
                this.author = (String) command.getValue();
                break;
            case "notes":
                this.notes = (String) command.getValue();
                break;
            case "type":
                this.type = (ProfileType) command.getValue();
                break;
            case "targetBrewWeight":
                this.targetBrewWeight = (Float) command.getValue();
                break;
            case "inputWeight":
                this.inputWeight = (Float) command.getValue();
                break;
            default:
                processChangeCommand(command);
                break;
        }
        command.setAfter(fromProfile(this));

        changeList.add(command);
    }

    protected abstract void processChangeCommand(ChangeCommand command);

    public abstract void copyParametersFromProfile(Profile newProfile);

    public void copyGenericParametersFromProfile(Profile newProfile) {
        this.title = newProfile.title;
        this.author = newProfile.author;
        this.notes = newProfile.notes;
        this.type = newProfile.type;
        this.targetBrewWeight = newProfile.targetBrewWeight;
        this.inputWeight = newProfile.inputWeight;
    }

    public void undo() {
        if (changeList.isEmpty())
            return;

        int size = changeList.size();
        ChangeCommand changeCommand = changeList.get(size - 1 - undoDepth);
        copyGenericParametersFromProfile(changeCommand.getBefore());
        copyParametersFromProfile(changeCommand.getBefore());
        undoDepth++;
    }

    public void redo() {
        if (undoDepth == 0) {
            return;
        }
        undoDepth--;
        int size = changeList.size();
        ChangeCommand changeCommand = changeList.get(size - 1 - undoDepth);
        copyGenericParametersFromProfile(changeCommand.getAfter());
        copyParametersFromProfile(changeCommand.getAfter());
    }
}
