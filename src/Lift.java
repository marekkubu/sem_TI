import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Marek on 6. 12. 2016.
 */
public class Lift {
    boolean acitve = false;
    int curent_floor=0;
    boolean sensor_weight=true;
    boolean sensor_door=true;
    boolean sensor_error =true;
    boolean door_open = false;
    int next_floor=0;
    public static Deque<Integer> stack = new ArrayDeque<Integer>();
}
