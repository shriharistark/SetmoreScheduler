package Components.Util;
import java.time.LocalTime;

public class TimeWindow {

    LocalTime from;
    LocalTime to;

    public TimeWindow(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "TimeWindow{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
