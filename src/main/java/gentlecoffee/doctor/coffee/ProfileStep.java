package gentlecoffee.doctor.coffee;

import java.util.ArrayList;

public abstract class ProfileStep< T extends ProfileStep.ProfileStepExitCondition> {
    private String name;
    private String description;
    private ArrayList<T> exitConditions;

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

    public ArrayList<T> getExitConditions() {
        return exitConditions;
    }

    public void setExitConditions(ArrayList<T> exitConditions) {
        this.exitConditions = exitConditions;
    }

    public abstract class ProfileStepExitCondition {
        public abstract String getName();

        public abstract String getDescription();
    }
}
