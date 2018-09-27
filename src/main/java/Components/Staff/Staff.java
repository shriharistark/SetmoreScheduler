package Components.Staff;

import Components.Component;
import Components.Service.Service;
import Components.Service.ServiceImpl;
import Components.Util.TimeWindow;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface Staff extends Component<Staff> {

    void updateWorkingHours(List<String> hours);
    void updateWorkingHours(int dayOfWeek, TimeWindow timeWindow);
    void addBreak(TimeWindow breakTime, int day);
    List<Service> getServices();
    boolean addService(List<Service> serviceList);
    boolean addService(Service service);
    List<LocalTime> getAvailableSlots(int dayOfWeek);
    boolean removeSlotsBetween(LocalTime from, LocalTime to, int dayOfWeek);

}
