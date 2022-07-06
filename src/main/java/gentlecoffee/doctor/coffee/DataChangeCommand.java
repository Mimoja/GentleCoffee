package gentlecoffee.doctor.coffee;

import java.lang.Record;

public class DataChangeCommand<T extends Record & ProfileDataInterface<T>> {
    private T new_data;

    private T before;
    private T after;

    public DataChangeCommand(T new_data) {
        this.new_data = new_data;
    }

    public T getNewData() {
        return this.new_data;
    }


    public T execute(T base) {
        return base.merge(this.new_data);
    }

    public T getBefore() {
        return before;
    }

    public void setBefore(T before) {
        this.before = before;
    }

    public T getAfter() {
        return after;
    }

    public void setAfter(T after) {
        this.after = after;
    }
}
