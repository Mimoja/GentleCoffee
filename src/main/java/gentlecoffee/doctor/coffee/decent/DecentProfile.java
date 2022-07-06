package gentlecoffee.doctor.coffee.decent;

import com.google.gson.Gson;
import gentlecoffee.doctor.coffee.ChangeCommand;
import gentlecoffee.doctor.coffee.Profile;
import gentlecoffee.doctor.coffee.ProfileStep;

public class DecentProfile extends Profile<DecentProfile.DecentProfileStep> {
    public String tank_temperature;
    public String target_volume_count_start;

    public static Profile fromString(String input) {
        //TODO allow name changes in Profile
        DecentProfile root = new Gson().fromJson(input, DecentProfile.class);

        return root;
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
}



