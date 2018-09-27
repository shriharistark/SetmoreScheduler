package Components;

public interface Component<E> {

    //CRUD on components
//    create is exclusive to each component. cannot be generalised
    Component<E> read(String ID);
    boolean update(Component<? extends Component> newComponent);
    boolean delete(String ID);
}
