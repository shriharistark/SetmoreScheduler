package Components.Service;

import Components.Component;
import Components.Staff.Staff;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServiceImpl implements Service {

    private String ID;
    private String name;
    private LocalTime durationInMins;
    private String description;
    private List<Staff> providers;

    private ServiceImpl(ServiceBuilder serviceBuilder){

        this.ID = UUID.randomUUID().toString().substring(0,10);
        if(providers == null && serviceBuilder.providers == null){
            this.providers = new ArrayList<>();
        }
        this.name = serviceBuilder.name;
        this.description = serviceBuilder.description;
        this.durationInMins = serviceBuilder.durationInMins;
    }

    public static class ServiceBuilder{

        //mandatory
        private String name;
        private LocalTime durationInMins;

        //optional
        private String description;
        private List<Staff> providers;


        public ServiceBuilder(String name, LocalTime durationInMins) {
            this.name = name;
            this.durationInMins = durationInMins;
        }

        public ServiceBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public ServiceBuilder setProviders(List<Staff> staffs){
            this.providers = staffs;
            return this;
        }

        public ServiceImpl build(){
            return new ServiceImpl(this);
        }

    }

    @Override
    public void addProviders(List staffs) {
        this.getProviders().addAll(staffs);
    }

    @Override
    public List<Staff> getProviders() {
        return this.providers;
    }

    @Override
    public boolean addProviders(Staff staff) {
        if(this.providers != null){
            this.providers.add(staff);
            return true;
        }

        return false;
    }

    @Override
    public Component<Service> read(String ID) {
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

    //getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getDuration() {
        return durationInMins;
    }

    public void setDuration(LocalTime duration) {
        this.durationInMins = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ServiceImpl{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", durationInMins=" + durationInMins +
                ", description='" + description + '\'' +
                '}';
    }
}
