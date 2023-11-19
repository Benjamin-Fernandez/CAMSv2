package CAMSv2;
import java.util.*;
public abstract class BaseController<U, V> {
    protected U user;
    protected V view;
    protected Scanner sc;

    public BaseController(U user, V view) {
        this.user = user;
        this.view = view;
        sc = new Scanner(System.in);
    }

    public abstract void startProgram();

}