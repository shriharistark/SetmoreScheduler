package Components.Appointment;

import Components.Component;
import Components.Service.Service;
import Components.Staff.Staff;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.time.LocalTime;

//either use the solid method create to create an Appointment or use the appt builder
public class AppointmentImpl implements Appointment{


    private AppointmentImpl(AppointmentBuilder builder){


    }

    //user builder for appointment
    //attempt dynamic slot generation
    public static class AppointmentBuilder{


    }


    @Override
    public Appointment create(Service service, Staff staff, LocalTime time, LocalDate date, Appointment appointmentDetails) {
        return null;
    }

    @Override
    public Appointment reschedule(Appointment appointmentdata, LocalTime time, LocalDate date) {
        return null;
    }

    @Override
    public Component<Appointment> read(String ID) {
        System.out.println("appointment read");
        return null;
    }

    @Override
    public boolean update(Component<? extends Component> newComponent) {
        return false;
    }

    //aka cancellation
    @Override
    public boolean delete(String ID) {
        return false;
    }
}
