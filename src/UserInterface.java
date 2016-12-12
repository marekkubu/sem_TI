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
    static JButton button0,button1,button2,button3,buttonOut0,buttonOut1,buttonOut2,buttonOut3;
    static JLabel emptyLabel;

    public void initFrame() {
        frame = new JFrame("Lift");
        background=new Background(Images.background,Images.open_doors, Images.close_doors);
        frame.setResizable(false);


        button0=new JButton(" Floor 0 ");
        button1=new JButton(" Floor 1 ");
        button2=new JButton(" Floor 2 ");
        button3=new JButton(" Floor 3 ");

        buttonOut0=new JButton("");
        buttonOut1=new JButton("");
        buttonOut2=new JButton("");
        buttonOut3=new JButton("");

        radioButtonWeight = new JRadioButton("Sensor weight" );
        radioButtonDoors = new JRadioButton("Sensor doors" );
        //radioButtonError = new JRadioButton("Sensor error" );

        emptyLabel = new JLabel("LIFT IS EMPTY");

        radioButtonWeight.setSelected(true);
        button0.setVisible(false);
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);


        button0.addActionListener(e -> {
            Main.lift.stack.push(0);
            button0.setEnabled(false);
        });

        button1.addActionListener(e -> {
            Main.lift.stack.push(1);
            button1.setEnabled(false);
        });

        button2.addActionListener(e -> {
            Main.lift.stack.push(2);
            button2.setEnabled(false);
        });

        button3.addActionListener(e -> {
            Main.lift.stack.push(3);
            button3.setEnabled(false);
        });

        buttonOut0.addActionListener(e -> {
            Main.lift.stack.push(0);
            buttonOut0.setEnabled(false);
        });

        buttonOut1.addActionListener(e -> {
            Main.lift.stack.push(1);
            buttonOut1.setEnabled(false);
        });

        buttonOut2.addActionListener(e -> {
            Main.lift.stack.push(2);
            buttonOut2.setEnabled(false);
        });

        buttonOut3.addActionListener(e -> {
            Main.lift.stack.push(3);
            buttonOut3.setEnabled(false);
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
        background.add(buttonOut0);
        background.add(buttonOut1);
        background.add(buttonOut2);
        background.add(buttonOut3);
        background.add(emptyLabel);
        background.setLayout(null);

        button0.setBounds(15,10,80,25);
        button1.setBounds(105,10,80,25);
        button2.setBounds(195,10,80,25);
        button3.setBounds(285,10,80,25);
        radioButtonDoors.setBounds(240,75,110,25);
        radioButtonWeight.setBounds(240,100,110,25);
        buttonOut0.setBounds(165,390,25,25);
        buttonOut1.setBounds(165,295,25,25);
        buttonOut2.setBounds(165,195,25,25);
        buttonOut3.setBounds(165,100,25,25);
        emptyLabel.setBounds(250,20,200,25);

        frame.add(background);
        frame.setSize(382,495);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
