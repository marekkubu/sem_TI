import javax.swing.*;
import java.util.*;

/**
 * Hlavní třída slouzici k behu vytahu
 * @author Marek Kubů, Josef Kalivoda
 */
public class Main implements Observer {
    /**
     * Inicializace vytahu
     */
    public static Lift lift = new Lift();
    /**
     * Nastaveni doby zavirani a otvirani dveri
     */
    public static long timeDoors = 2000;
    /**
     * Nastaveni doby presunuti vytahu z jednoho patra do druheho
     */
    public static long timeMove = 3000;
    /**
     * Inicializace uzivatelskeho rozhrani
     */
    public static UserInterface userInterface = new UserInterface();


    /**
     * Hlavni metoda aplikace
     * @param args nepouzit
     */
    public static void main(String[] args) {
        Main watcher = new Main();
        userInterface.addObserver(watcher);
        userInterface.initFrame();
        while (true){
            userInterface.background.repaint();

            while(!lift.stack.isEmpty()){
                if (lift.stack.getLast()==lift.curent_floor ) {
                    opendoors();
                    //int x = lift.stack.getLast();
                    // lift.stack.removeLast();
                    System.out.println(lift.stack);
                }
                else {
                    System.out.println(lift.stack);
                    int x =lift.stack.getLast();
                    lift.stack.removeLast();

                    move(x);
                }
            }

        }
    }

    /**
     * Metoda slouzici k presunuti vytahu do zadaneho patra
     * @param nextFloor patro, do ktereho se ma vytah presunout
     */
    private static void move(int nextFloor) {
        lift.next_floor=nextFloor;
        System.out.println("Zadane patro: "+nextFloor);
        if (nextFloor <= 3) {
            if (nextFloor == lift.curent_floor) {
                opendoors();


            }
            else {
                lift.acitve=true;
                //System.out.println(" nextFloor: "+ nextFloor);

                while (nextFloor!=lift.curent_floor){


                    if (nextFloor < lift.curent_floor) {
                        if (!lift.stack.isEmpty()&& lift.curent_floor == lift.stack.getLast()) {

                            lift.stack.removeLast();


                            opendoors();

                        }
                        int end = userInterface.background.yPosition+userInterface.background.nextPosition;

                        while(userInterface.background.yPosition!=end){
                            timer(timeMove/userInterface.background.nextPosition);
                            userInterface.background.yPosition=userInterface.background.yPosition+1;
                            userInterface.background.repaint();
                        }
                        lift.curent_floor--;
                        userInterface.background.repaint();
                    }
                    else {
                        if (!lift.stack.isEmpty()&& lift.curent_floor == lift.stack.getLast()) {
                            lift.stack.removeLast();
                            opendoors();
                        }
                        int end = userInterface.background.yPosition-userInterface.background.nextPosition;
                        while(userInterface.background.yPosition!=end){
                            timer(timeMove/userInterface.background.nextPosition);
                            userInterface.background.yPosition=userInterface.background.yPosition-1;
                            userInterface.background.repaint();
                        }
                        lift.curent_floor++;
                    }
                    System.out.println("Aktualni patro vytahu: " + lift.curent_floor);
                }
                lift.acitve=false;
                lift.next_floor=-1;
                opendoors();
                System.out.println("Aktualni patro vytahu: " + lift.curent_floor);
            }
        }

        else{
            System.out.println("Zadane hodnoty jsou chybne");
        }

    }

    /**
     * Metoda slouzici ke kontrole senzoru a nastavuje vytahu udaje
     * @return true pokud jsou vsechny senzory ok nebo false pokud tomu tak neni
     */
    private static boolean control() {

        if (userInterface.radioButtonDoors.isSelected() == true) {
            lift.sensor_door =false;
        }
        else{
            lift.sensor_door =true;
        }

        if (userInterface.radioButtonWeight.isSelected() == true) {
            lift.sensor_weight =false;
        }
        else{
            lift.sensor_weight =true;
        }

        if (lift.sensor_door == true && lift.sensor_weight==true ) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Metoda slouzici k otvirani dveri vytahu
     */
    private static void opendoors() {
        System.out.println(" open doors");
        timer(timeDoors);
        lift.door_open=true;
        userInterface.background.repaint();
        closeDoors();
        UserInterface.enabledFloor(lift.curent_floor);
        lift.stack.remove(lift.curent_floor);
    }

    /**
     * Metoda slouzici k upozorneni uzivatele, chybove hlaseni
     * @param text
     * @param header
     */
    public static void msgbox(String text, String header){
        JOptionPane.showMessageDialog(null, text, header, JOptionPane.WARNING_MESSAGE);

    }

    /**
     * Metoda slouzici k zavirani dveri
     */
    private static void closeDoors() {
        System.out.println(" close doors ");
        int pocet=0;
        while (pocet<50){
            control();
            while (lift.sensor_door == false){
                control();
                userInterface.doorsLabel.setVisible(true);
            }
            userInterface.doorsLabel.setVisible(false);
            timer(timeDoors/50);
            pocet++;
        }
        lift.door_open=false;
        if (lift.sensor_weight == false){
            userInterface.emptyLabel.setVisible(true);
            userInterface.button0.setVisible(false);
            userInterface.button1.setVisible(false);
            userInterface.button2.setVisible(false);
            userInterface.button3.setVisible(false);
        }
        else{
            userInterface.emptyLabel.setVisible(false);
            userInterface.radioButtonWeight.setSelected(false);
            userInterface.button0.setVisible(true);
            userInterface.button1.setVisible(true);
            userInterface.button2.setVisible(true);
            userInterface.button3.setVisible(true);

        }

        userInterface.background.repaint();

    }

    /**
     * Metoda slouzici k casovani programu a jednotlivych ukonu
     * @param time
     */
    public static void timer(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}