package gentlecoffee.doctor.coffee;

import java.util.*;

public abstract class Profile<T extends ProfileStep> {
    public Integer TITLE;
    public Integer AUTHOR;
    public Integer NOTES;
    public Integer TYPE;
    public Integer OUTPUT_WEIGHT;
    public Integer INPUT_WEIGHT;
    public ProfileList profileVars = new ProfileList();
    private ArrayList<ChangeCommand> changeList;
    private Integer undoDepth;

    public Profile(String title, String author, String notes, ProfileType type, float targetBrewWeight, float inputWeight) {
        changeList = new ArrayList<>();
        undoDepth = 0;

        this.TITLE = registerParam(new ProfileParamWithLimits<>(
                "Title",
                title, "The title of the profile")
        );

        this.AUTHOR = registerParam(new ProfileParamWithLimits<>(
                "Author",
                author, "The author of the profile")
        );

        this.NOTES = registerParam(new ProfileParamWithLimits<>(
                "Notes",
                notes, "The description or tasting notes of the profile")
        );

        this.TYPE = registerParam(new ProfileParamWithLimits<>(
                "Beverage Type",
                type, "The type of the profile (e.g. Espresso, Pourover, Tea)")
        );

        this.AUTHOR = registerParam(new ProfileParamWithLimits<>(
                "Author",
                author, "The author of the profile")
        );

        this.OUTPUT_WEIGHT = registerParam(new ProfileParamWithLimits<>(
                "Output Weight",
                targetBrewWeight, 0.0f, null, 0.1f, "The output of the brew in gramm")
        );

        this.INPUT_WEIGHT = registerParam(new ProfileParamWithLimits<>(
                "Input Weight",
                inputWeight, 0.0f, 100.0f, 0.1f, "The input of the brew in gramm")
        );
    }

    public List<ProfileParamWithLimits> getVars() {
        return (List<ProfileParamWithLimits>) profileVars.clone();
    }

    protected int registerParam(ProfileParamWithLimits param) {
        profileVars.add(param);
        return param.hashCode();
    }

    public void changeParameter(ChangeCommand command) {
        // No chance to redo from here
        if (undoDepth > 0) {
            int size = changeList.size();
            changeList.subList(size - undoDepth, size).clear();
            undoDepth = 0;
        }
        ProfileParamWithLimits parameter = command.getParameter();

        System.out.println("Changing param " + parameter.getName());

        command.setBefore((List<ProfileParamWithLimits>) profileVars.clone());

        for (int i = 0; i < profileVars.size(); i++) {
            ProfileParamWithLimits var = profileVars.get(i);
            if (var.hashCode() == parameter.hashCode()) {
                var.setValue(parameter.getValue());
            }
        }

        command.setAfter((List<ProfileParamWithLimits>) profileVars.clone());

        changeList.add(command);
    }

    public ProfileParamWithLimits getParameterFromHashCode(Integer hashCode) {
        for (int i = 0; i < profileVars.size(); i++) {
            ProfileParamWithLimits var = profileVars.get(i);
            if (var.hashCode() == hashCode) {
                return (ProfileParamWithLimits) var.clone();
            }
        }
        return null;
    }

    public void undo() {
        if (changeList.isEmpty())
            return;

        int size = changeList.size();
        ChangeCommand changeCommand = changeList.get(size - 1 - undoDepth);
        profileVars = (ProfileList) ((ProfileList) changeCommand.getBefore()).clone();
        undoDepth++;
    }

    public void redo() {
        if (undoDepth == 0) {
            return;
        }
        undoDepth--;
        int size = changeList.size();
        ChangeCommand changeCommand = changeList.get(size - 1 - undoDepth);
        profileVars = (ProfileList) ((ProfileList) changeCommand.getAfter()).clone();
    }

    class ProfileList extends LinkedList<ProfileParamWithLimits> {
        public ProfileList clone() {
            ProfileList l = new ProfileList();

            for (ProfileParamWithLimits item : this) {
                l.add((ProfileParamWithLimits) item.clone());
            }
            return l;
        }
    }
}
