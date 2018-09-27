import Components.Service.Service;
import Components.Service.ServiceImpl;
import Components.Staff.Staff;
import Components.Staff.StaffImpl;
import Components.Util.TimeWindow;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        List<Service> services = new ArrayList<>();
        List<Staff> staffs = new ArrayList<>();


        List<String> hours = new ArrayList<>();
        for(int i = 0 ; i <= 6 ; i++){
            hours.add(i,"10:16");
        }

        ServiceImpl haircut = new ServiceImpl.ServiceBuilder("haircut",LocalTime.of(1,15))
                .setDescription("Spikes")
                .build();

        ServiceImpl massage = new ServiceImpl.ServiceBuilder("massage", LocalTime.of(2,30)).build();
        services.add(haircut);
        services.add(massage);

        StaffImpl staff1 = new StaffImpl.StaffBuilder("Glenn",services).build();

        staff1.updateWorkingHours(hours);
        staffs.add(staff1);

        haircut.addProviders(staffs);

        System.out.println(haircut);
        System.out.println(staff1);
        System.out.println(haircut.getProviders());
        System.out.println(staff1.getServices());
        System.out.println(staff1.getAvailableSlots(3));
        System.out.println(staff1.getWorkingHoursFor(2));

        int day = 3;
//        staff1.removeSlotsBetween(staff1.getAvailableSlots(3).get(4),LocalTime.of(12,10),3);
        System.out.println(staff1.removeSlotsBetween(staff1
                        .getAvailableSlots(3) //wednesday
                        .get(2) //picked 9.30
                        ,LocalTime.of(10,20),3));

        System.out.println(staff1.getAvailableSlots(3));

        staff1.printStaffAvailabilities();
        TimeWindow tuesdayHours = staff1.getWorkingHoursFor(1);
        tuesdayHours.setFrom(LocalTime.of(11,15));
        tuesdayHours.setFrom(LocalTime.of(12,15));

        staff1.updateWorkingHours(1,tuesdayHours);
        staff1.printStaffAvailabilities();

    }
}
