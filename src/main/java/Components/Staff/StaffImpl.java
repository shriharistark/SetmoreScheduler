package Components.Staff;

import Components.Component;
import Components.Service.Service;
import Components.Util.TimeWindow;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffImpl implements Staff {

    private String ID;
    private String name;
    private String description;
    private List<Service> services;
    private List<TimeWindow> workingHours;
    private List<List<LocalTime>> availableSlots;   //contains slots for seven days of the week

    private StaffImpl(StaffBuilder builder) {

        this.ID = UUID.randomUUID().toString().substring(0,10);
        this.name = builder.name;
        this.services = builder.services;
        this.description = builder.description;

        //implicit values
        if(services == null)
            this.services = new ArrayList<>();

        if(availableSlots == null)
            this.availableSlots = new ArrayList<>();

        if(workingHours == null)
            this.workingHours = new WorkingHoursBuilder().build();

        for(int i = 0 ; i < 7 ;i++){
            this.availableSlots.add(this._generateSlots(i));
        }
    }

    public static class StaffBuilder{

        //mandatory
        private String name;

        //optional
        private String description;
        private List<Service> services;


        public StaffBuilder(String name, List<Service> services) {
            this.name = name;
            this.services = services;
        }

        public StaffBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public StaffBuilder setServices(List<Service> serviceList){
            this.services.addAll(serviceList);
            return this;
        }

        public StaffImpl build(){
            return new StaffImpl(this);
        }

    }

    public static class WorkingHoursBuilder{

        List<TimeWindow> workWeek;
        LocalTime dateTimeGen;
        TimeWindow timeWindow;

        public WorkingHoursBuilder(){

            workWeek = new ArrayList<>();
            //mon - 0 | tue - 1 | wed - 2 | thurs - 3 | fri - 4 | sat - 5 | sun - 6
            //set default hours for staff
            TimeWindow defaultNineToFive = new TimeWindow(LocalTime.of(9,0),LocalTime.of(17,0));
            for(int i = 0 ; i < 7 ; i++){
                workWeek.add(i, defaultNineToFive);
            }
        }

        WorkingHoursBuilder buildFromString(List<String> hoursForTheWeekInString){

            int i = 0;
            for(String hour : hoursForTheWeekInString){
                int from = Integer.valueOf(hour.split(":")[0]);
                int to = Integer.valueOf(hour.split(":")[1]);

                if(to > from && (to >= 0 && to <= 24) && (from >= 0)){
                    this.workWeek.add(i, new TimeWindow(LocalTime.of(from,0),LocalTime.of(to,0)));
                }

                i++;
            }

            return this;
        }

        List<TimeWindow> build(){
            return this.workWeek;
        }

    }

    @Override
    public Component<Staff> read(String ID) {
        return null;
    }

    @Override
    public boolean update(Component newComponent) {
        return false;
    }

    @Override
    public boolean delete(String ID) {
        return false;
    }

    /*
    Hours should be -> ["11:16, 14:18, 11:14, "]
    week starts from monday [mon, tue, wed .. sun]
     */
    @Override
    public void updateWorkingHours(List<String> hours) {
        this.workingHours = new WorkingHoursBuilder().buildFromString(hours).build();
    }

    @Override
    public void updateWorkingHours(int dayOfWeek, TimeWindow timeWindow) {
        this.workingHours.set(dayOfWeek,timeWindow);
    }

    public TimeWindow getWorkingHoursFor(int dayOfTheWeek) {
        if(dayOfTheWeek >= 0 && dayOfTheWeek <= 6){
            return workingHours.get(dayOfTheWeek);
        }
        System.out.println("Is not a valid work day");
        return null;
    }

    @Override
    public List<Service> getServices() {
        return this.services;
    }

    @Override
    public boolean addService(List<Service> serviceList) {
        if(serviceList != null){
            this.services.addAll(serviceList);
            return true;
        }

        return false;
    }

    @Override
    public boolean addService(Service service) {
        if(this.services != null){
            this.services.add(service);
            return true;
        }

        return false;
    }

    @Override
    public void addBreak(TimeWindow breakTime, int day) {

        //todo
        LocalTime dateTime;
        LocalTime time = breakTime.getFrom();

    }

    //generates default set of slots using the working hours and breaks
    private List<LocalTime> _generateSlots(int dayOfTheWeek) {

        List<LocalTime> slots = new ArrayList<>();
        TimeWindow workingHoursForTheDay = getWorkingHoursFor(dayOfTheWeek);

        LocalTime iter;
        LocalTime start = workingHoursForTheDay.getFrom();
        LocalTime end = workingHoursForTheDay.getTo();

        for(iter = start ; iter.isBefore(end) ; ){
            //do something
            slots.add(iter);
            iter = iter.plusMinutes(15);
        }

        return slots;
    }

    @Override
    public List<LocalTime> getAvailableSlots(int dayOfWeek) {
        return this.availableSlots.get(dayOfWeek);
    }


    @Override
    public boolean removeSlotsBetween(LocalTime from, LocalTime to, int dayOfWeek) {

        List<LocalTime> slots = getAvailableSlots(dayOfWeek);
        int fromSlotIndex = -1;
        for(int i = 0 ; i < slots.size() ; i++){
            if(slots.get(i).compareTo(from) == 0){
                fromSlotIndex = i;
            }
        }
        System.out.println("Slots: "+slots);

        int nearestSlotIndex = _findNearestSlotIndex(to, dayOfWeek);
        System.out.println("from slot: "+fromSlotIndex+" to slot: "+nearestSlotIndex);

        if(nearestSlotIndex >= 0 && fromSlotIndex >= 0) {
            for(int j = fromSlotIndex ; j <= nearestSlotIndex ; j++){
                System.out.println("removed: "+slots.remove(fromSlotIndex));
            }
            this.availableSlots.set(dayOfWeek,slots);
            return true;
        }
        return false;
    }

    private int _findNearestSlotIndex(LocalTime mySlotTime, int dayOfWeek){
        List<LocalTime> dayOfWeeksSlot = getAvailableSlots(dayOfWeek);
        for(int i = 0 ; i < dayOfWeeksSlot.size() ; i++){
            if(dayOfWeeksSlot.get(i).isAfter(mySlotTime)){
                return i;
            }
        }

        return -1;
    }

    public void printStaffAvailabilities(){

        for(List<LocalTime> day : availableSlots){
            for(LocalTime time : day){
                System.out.print(time+" ");
            }
            System.out.println("\n");
        }
    }

    @Override
    public String toString() {
        return "StaffImpl{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", services=" + services +
                ", workingHours=" + workingHours +
                '}';
    }
}
