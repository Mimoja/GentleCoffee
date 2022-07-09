package gentlecoffee.doctor.coffee;

// This needs a better name
public class ProfileParamLimits<T> {
    private final String name;
    private T value;
    private T min;
    private T max;
    private T increment;
    private String description;

    public ProfileParamLimits(String name, T value, T min, T max, T increment, String description) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.description = description;
    }

    public ProfileParamLimits(String name, T value, String description) {
        this.name = name;
        this.value = value;
        this.min = null;
        this.max = null;
        this.increment = null;
        this.description = description;
    }

    public T getValue() {
        return value;
    }

    // setValue can be overwriten to ensure min / max / increment of other variables still match
    public void setValue(T value) {
        this.value = value;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    public T getIncrement() {
        return increment;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
