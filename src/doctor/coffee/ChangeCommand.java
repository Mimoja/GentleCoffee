package doctor.coffee;

public class ChangeCommand {
    private String parameter_name;
    private Object parameter_value;

    private Profile before;
    private Profile after;

    public ChangeCommand(String name, Object value) {
        parameter_name = name;
        parameter_value = value;
    }

    public String getName() {
        return parameter_name;
    }

    public Object getValue() {
        return parameter_value;
    }

    public Profile getBefore() {
        return before;
    }

    public void setBefore(Profile before) {
        this.before = before;
    }

    public Profile getAfter() {
        return after;
    }

    public void setAfter(Profile after) {
        this.after = after;
    }
}
