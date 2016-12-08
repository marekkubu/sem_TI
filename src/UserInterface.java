import javax.swing.*;
import java.util.Observable;

/**
 * Created by Marek on 8. 12. 2016.
 */
public class UserInterface extends Observable {

    private static JFrame frame;
    public static Background background;
    private static Background open_doors;
    public static boolean exitPressed =false;
    //static JRadioButton radioButtonError;
    static JRadioButton radioButtonDoors;
    static JRadioButton radioButtonWeight;

    public void initFrame() {
        frame = new JFrame("Lift");
        background=new Background(Images.background,Images.open_doors, Images.close_doors);
        frame.setResizable(false);


        JButton button0=new JButton(" Floor 0 ");
        JButton button1=new JButton(" Floor 1 ");
        JButton button2=new JButton(" Floor 2 ");
        JButton button3=new JButton(" Floor 3 ");

        radioButtonWeight = new JRadioButton("Sensor weight" );
        radioButtonDoors = new JRadioButton("Sensor doors" );
        //radioButtonError = new JRadioButton("Sensor error" );



        button0.addActionListener(e -> {
            Main.lift.stack.push(0);
        });
        button1.addActionListener(e -> {
            Main.lift.stack.push(1);
        });
        button2.addActionListener(e -> {
            Main.lift.stack.push(2);
        });
        button3.addActionListener(e -> {
            Main.lift.stack.push(3);
        });

        /*radioButtonError.addActionListener(e -> {
            setChanged();
            notifyObservers();
        });*/


        background.add(button0);
        background.add(button1);
        background.add(button2);
        background.add(button3);
        background.add(radioButtonWeight);
        //background.add(radioButtonError);
        background.add(radioButtonDoors);

        frame.add(background);
        frame.setSize(382,495);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
