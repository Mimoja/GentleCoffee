package gentlecoffee.doctor.coffee;

import java.util.HashMap;
import java.util.List;

public class ChangeCommand<T> {
    private List<ProfileParamWithLimits> before;
    private List<ProfileParamWithLimits> after;
    private ProfileParamWithLimits parameter_value;
    public ChangeCommand(ProfileParamWithLimits parameter_value) {
        this.parameter_value = parameter_value;
    }

    public ProfileParamWithLimits getParameter() {
        return parameter_value;
    }
    public List<ProfileParamWithLimits> getBefore() {
        return before;
    }

    public void setBefore(List<ProfileParamWithLimits> before) {
        this.before = before;
    }

    public List<ProfileParamWithLimits> getAfter() {
        return after;
    }

    public void setAfter(List<ProfileParamWithLimits> after) {
        this.after = after;
    }

}
