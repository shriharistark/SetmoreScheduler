package Components.Appointment;

import Components.Component;
import Components.Service.Service;
import Components.Staff.Staff;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Appointment extends Component<Appointment> {

    Appointment create(Service service, Staff staff, LocalTime time, LocalDate date, Appointment appointmentDetails);
    Appointment reschedule(Appointment appointmentdata, LocalTime time, LocalDate date);

}
