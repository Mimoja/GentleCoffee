package gentlecoffee.doctor.coffee;

import java.util.HashMap;
import java.util.List;

public class ChangeCommand<T> {
    private List<ProfileParamWithLimits<T>> before;
    private List<ProfileParamWithLimits<T>> after;
    private ProfileParamWithLimits<T> parameter_value;
    public ChangeCommand(ProfileParamWithLimits<T> parameter_value) {
        this.parameter_value = parameter_value;
    }

    public ProfileParamWithLimits<T> getParameter() {
        return parameter_value;
    }
    public List<ProfileParamWithLimits<T>> getBefore() {
        return before;
    }

    public void setBefore(List<ProfileParamWithLimits<T>> before) {
        this.before = before;
    }

    public List<ProfileParamWithLimits<T>> getAfter() {
        return after;
    }

    public void setAfter(List<ProfileParamWithLimits<T>> after) {
        this.after = after;
    }

}
