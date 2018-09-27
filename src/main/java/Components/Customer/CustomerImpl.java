package Components.Customer;

import Components.Component;

import java.util.Map;

public class CustomerImpl implements Component<CustomerImpl>{

    private String name;
    private String email;

    //optional
    private Long number;
    private String notes;
    private Map<String, String> additionalFields;

    //getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getNumber() {
        return number;
    }

    public String getNotes() {
        return notes;
    }

    public Map<String, String> getAdditionalFields() {
        return additionalFields;
    }

    private CustomerImpl(CustomerBuilder builder){

        this.name = builder.name;
        this.email = builder.email;

        //notes
        this.additionalFields = builder.additionalFields;
        this.number = builder.number;
        this.notes = builder.notes;
    }

    //Builder class for Customer
    public static class CustomerBuilder{

        //mandatory
        private String name;
        private String email;

        //optional
        private Long number;
        private String notes;
        private Map<String, String> additionalFields;

        public CustomerBuilder(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public CustomerBuilder setNumber(Long number){
            this.setNumber(number);
            return this;
        }

        public CustomerBuilder setNotes(String notes){
            this.setNotes(notes);
            return this;
        }

        public CustomerBuilder setAdditionalFields(Map<String, String> fields){
            this.setAdditionalFields(fields);
            return this;
        }

        public CustomerImpl build(){
            return new CustomerImpl(this);
        }

    }

    @Override
    public Component read(String ID) {
        return null;
    }

    @Override
    public boolean update(Component<? extends Component> newComponent) {

        return false;
    }

    @Override
    public boolean delete(String ID) {
        return false;
    }
}
