package doctor.coffee;

import java.util.ArrayList;

public abstract class ProfileStep {
    private String name;
    private String description;
    private ArrayList<ProfileStepExitCondition> exitConditions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ProfileStepExitCondition> getExitConditions() {
        return exitConditions;
    }

    public void setExitConditions(ArrayList<ProfileStepExitCondition> exitConditions) {
        this.exitConditions = exitConditions;
    }

    public interface ProfileStepExitCondition {
        public String getName();

        public String getDescription();
    }
}
