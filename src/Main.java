import javax.swing.*;
import java.util.*;

public class Main implements Observer {

    public static Lift lift = new Lift();
    public static Scanner sc = new Scanner(System.in);
    public static long timeDoors = 2000;
    public static long timeMove = 3000;
    public static UserInterface userInterface = new UserInterface();



    public static void main(String[] args) {
        Main watcher = new Main();
        userInterface.addObserver(watcher);
        userInterface.initFrame();
        while (true){
            userInterface.background.repaint();

            while(!lift.stack.isEmpty()){
                if (lift.stack.getLast()==lift.curent_floor) {
                    opendoors();
                    lift.stack.removeLast();
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



    private static void doAction(int cur) {
        if (lift.curent_floor== cur) {
            opendoors();
            if (lift.door_open == false) {
                System.out.println("Zadejte patro:");
                int floor = sc.nextInt();
                move(floor);
            }
        }
        else {
            move(cur);
        }
    }

    private static void move(int nextFloor) {
        lift.next_floor=nextFloor;
        System.out.println(nextFloor + " Zadane patro");
        if (nextFloor <= 3) {
            if (nextFloor == lift.curent_floor) {
                opendoors();


            }
            else {
                lift.acitve=true;
                System.out.println(" nextFloor: "+ nextFloor);

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
                    System.out.println("curent floor " + lift.curent_floor);
                }
                lift.acitve=false;
                lift.next_floor=-1;
                opendoors();
                System.out.println("Aktualni patro vytahu: " + lift.curent_floor);
            }
        }

        else{
            System.out.println(" Zadane hodnoty jsou chybne");
        }

    }

    private static void enabledFloor(int curent_floor) {
        if(curent_floor == 0){
            userInterface.button0.setEnabled(true);
            userInterface.buttonOut0.setEnabled(true);
        }else if (curent_floor == 1){
            userInterface.button1.setEnabled(true);
            userInterface.buttonOut1.setEnabled(true);
        }else if (curent_floor == 2){
            userInterface.button2.setEnabled(true);
            userInterface.buttonOut2.setEnabled(true);
        }else {
            userInterface.button3.setEnabled(true);
            userInterface.buttonOut3.setEnabled(true);
        }
    }

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

        if (lift.sensor_door == true && lift.sensor_error==true && lift.sensor_weight==true ) {
            return true;
        }
        else{
            return false;
        }
    }

    private static void problem() {
        System.out.println("Problem");
    }

    private static void opendoors() {
        System.out.println(" open doors");
        timer(timeDoors);
        lift.door_open=true;
        userInterface.background.repaint();
        closeDoors();
        enabledFloor(lift.curent_floor);
    }
    public static void msgbox(String text, String header){
        JOptionPane.showMessageDialog(null, text, header, JOptionPane.WARNING_MESSAGE);

    }
    private static void closeDoors() {
            System.out.println(" close doors ");
            int pocet=0;
            while (pocet<50){
                control();

                if (lift.sensor_door == true) {
                    timer(timeDoors/50);
                    pocet++;
                }
                else {
                    msgbox("I cannot close doors.", "Sensor doors");
                    userInterface.radioButtonDoors.setSelected(false);
                    pocet=0;
                }
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

    public static void timer(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("waiting...");
        problem();
       /* try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
