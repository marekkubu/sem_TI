import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Trida, kde nastavujeme parametry vytahu
 * @author Marek Kubů, Josef Kalivoda
 */
public class Lift {
    /**
     * Udava, zda je vytah v pohybu
     */
    boolean acitve = false;
    /**
     * Udava aktualni patro, kde se vytah nachazi
     */
    int curent_floor=0;
    /**
     * Udava zda je nejaka osoba ve vytahu
     */
    boolean sensor_weight=true;
    /**
     * Udava zda je neco mezi dvermi
     */
    boolean sensor_door=true;
    /**
     * Zjistuje zda jsou dvere otevrene
     */
    boolean door_open = false;
    /**
     * Udava patro, do ktereho se vytah ma presunout, nebo uz se presouva
     */
    int next_floor=0;
    /**
     * Zde jsou ulozeny pokyny pro vytah
     */
    public static Deque<Integer> stack = new ArrayDeque<Integer>();
}
