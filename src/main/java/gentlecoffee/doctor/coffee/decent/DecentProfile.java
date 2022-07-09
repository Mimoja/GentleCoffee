package gentlecoffee.doctor.coffee.decent;

import gentlecoffee.doctor.coffee.*;

public class DecentProfile extends Profile<ProfileStep>  {
    public ProfileName<Float> TANK_TEMP;
    public ProfileName<Integer> PREINFUSION_STEPS;

    public DecentProfile(String title, String author, String notes, ProfileType type, float targetBrewWeight, float inputWeight, Float tank_temperature, Integer target_volume_count_start) {
        super(title, author, notes, type, targetBrewWeight, inputWeight);
        this.TANK_TEMP = registerParam(new ProfileParamWithLimits<>(
                "Tank Preheat Temperature",
                tank_temperature, "The temperature to which to preheat the tank")
        );

        this.PREINFUSION_STEPS = registerParam(new ProfileParamWithLimits<>(
                "Preinfusion Steps",
                target_volume_count_start, "The number of preinfusion steps which are not counted towards to the total weight goal")
        );


    }
}



