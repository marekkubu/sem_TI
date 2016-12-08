import java.util.*;

public class Main {

    public static Lift lift = new Lift();
    public static Scanner sc = new Scanner(System.in);
    public static long timeDoors = 2000;
    public static long timeMove = 3000;
    public static UserInterface userInterface = new UserInterface();



    public static void main(String[] args) {
        Main watcher = new Main();
        userInterface.initFrame();
        while (true){
            userInterface.background.repaint();
            while(!lift.stack.isEmpty()){
                if (lift.stack.getLast()==lift.curent_floor) {
                    //System.out.println("curent floor "+lift.curent_floor);
                    opendoors();
                    lift.stack.removeLast();
                    System.out.println(lift.stack);
                    // move(lift.next_floor);
                }
                else {
                    int x =lift.stack.getLast();
                    lift.stack.removeLast();
                    System.out.println("kontrola " +x);
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
                    timer(timeMove);
                    if (nextFloor < lift.curent_floor) {
                       lift.setCurent_floor(lift.curent_floor-1);
                        userInterface.background.yPosition+=userInterface.background.nextPosition;
                        userInterface.background.repaint();
                    }
                    else {
                        lift.setCurent_floor(lift.curent_floor+1);
                        userInterface.background.yPosition-=userInterface.background.nextPosition;
                        userInterface.background.repaint();
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
            System.out.println(" Zadane hodnoty sou chybne");
        }

    }

    private static boolean control() {
        if (lift.sensor_door == true && lift.sensor_error==true && lift.sensor_weight==true ) {
            return true;
        }
        else{
            return false;
        }
    }

    private static void problem() {
        System.out.println("problem");
    }

    private static void opendoors() {
        if (control() == true) {
            System.out.println(" open doors");
            timer(timeDoors);
            lift.door_open=true;
            userInterface.background.repaint();
            closeDoors();
        }
        else{
            problem();
        }
    }

    private static void closeDoors() {
        if (control() == true) {
            System.out.println(" close doors ");
            timer(timeDoors);
            lift.door_open=false;
            userInterface.background.repaint();
        }
        else{
            problem();
        }
    }

    public static void timer(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  /*  @Override
    public void update(Observable o, Object arg) {
        if (lift.stack.isEmpty()==false && lift.stack.getLast()==lift.curent_floor) {
            //System.out.println("curent floor "+lift.curent_floor);
            opendoors();
            lift.stack.removeLast();
            System.out.println(lift.stack);
           // move(lift.next_floor);
        }
        else if (lift.stack.isEmpty()==false) {
            int x =lift.stack.getLast();
            lift.stack.removeLast();
            System.out.println("kontrola " +x);
            move(x);
        }
        //System.out.println("Zmena: "+lift.stack+" arg: "+arg);
    }*/
}
