package Components.Service;

import Components.Component;
import Components.Staff.Staff;

import java.util.List;

public interface Service<E> extends Component {

    List<Staff> getProviders();
    void addProviders(List<Staff> staffs);
    boolean addProviders(Staff staff);
}
