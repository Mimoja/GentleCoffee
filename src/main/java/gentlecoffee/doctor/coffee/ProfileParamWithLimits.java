package gentlecoffee.doctor.coffee;

import static java.lang.Math.random;

// This needs a better name
public class ProfileParamWithLimits<T> implements Cloneable {
    private Integer _id;
    private final String name;
    private T value;
    private T min;
    private T max;
    private T increment;
    private String description;

    public ProfileParamWithLimits(String name, T value, T min, T max, T increment, String description) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.description = description;
        this._id = (int) (name.hashCode() * random());
    }

    public ProfileParamWithLimits(String name, T value, String description) {
        this(name, value, null, null, null, description);
    }
    @Override
    public Object clone()
    {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        return _id;
    }
}
