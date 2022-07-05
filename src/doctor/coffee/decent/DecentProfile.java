package doctor.coffee.decent;

import com.google.gson.GsonBuilder;
import doctor.coffee.ChangeCommand;
import doctor.coffee.Profile;
import doctor.coffee.ProfileStep;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DecentProfile extends Profile {
    private List<DecentProfileStep> steps;
    public String tank_temperature;
    public String target_volume_count_start;

    public List<ProfileStep> getSteps() {
        return steps.stream().map(decentProfileStep -> (ProfileStep) decentProfileStep).toList();
    }

    public void setSteps(List<ProfileStep> steps) {
        this.steps = new ArrayList<DecentProfileStep>(steps.stream().map(step ->  (DecentProfileStep)step).toList());
    }

    @Override
    protected void processChangeCommand(ChangeCommand command) {

    }

    @Override
    public void copyParametersFromProfile(Profile newProfile) {
        if (!(newProfile instanceof DecentProfile)) {
           return;
        }
        DecentProfile decentProfile = (DecentProfile) newProfile;
        this.steps = decentProfile.steps;
        this.tank_temperature = decentProfile.tank_temperature;
        this.target_volume_count_start = decentProfile.target_volume_count_start;
    }


    public class ExitCondition {
        public String type;
        public String condition;
        public String value;
    }

    public class DecentProfileStep extends ProfileStep {
        public String temperature;
        public String sensor;
        public String pump;
        public String transition;
        public String pressure;
        public String flow;
        public String seconds;
        public String volume;
    }

    public static Profile fromString(String input) {
        //TODO allow name changes in Profile
        DecentProfile root = new Gson().fromJson(input, DecentProfile.class);

        return root;
    }
}



