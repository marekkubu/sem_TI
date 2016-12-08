import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Observable;

/**
 * Created by Marek on 6. 12. 2016.
 */
public class Lift extends Observable {
    boolean acitve = false;
    int curent_floor=0;
    boolean sensor_weight=true;
    boolean sensor_door=true;
    boolean sensor_error =true;
    boolean door_open = false;
    int next_floor=0;
    public static Deque<Integer> stack = new ArrayDeque<Integer>();


    public void setCurent_floor(int curent_floor) {
        this.curent_floor = curent_floor;
        setChanged();
        notifyObservers(curent_floor);
    }

    public void setPush(int floor) {
        Lift.stack.push(floor);

        setChanged();
        notifyObservers(floor);
    }





}
