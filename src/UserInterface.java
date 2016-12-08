
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Created by Marek on 8. 12. 2016.
 */
public class UserInterface extends Observable {

    private static JFrame frame;
    public static Background background;
    private static Background open_doors;
    public static boolean exitPressed =false;

    public void initFrame() {
        frame = new JFrame("Lift");
        background=new Background(Images.background,Images.open_doors, Images.close_doors);
        //open_doors=new Background(Images.open_doors);
        frame.setResizable(false);


        JButton button0=new JButton(" 0 ");
        JButton button1=new JButton(" 1 ");
        JButton button2=new JButton(" 2 ");
        JButton button3=new JButton(" 3 ");
        JButton buttonExit=new JButton(" exit ");

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
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                exitPressed = true;

            }
        });
       // background.setLayout(null);
        background.add(button0);
        background.add(button1);
        background.add(button2);
        background.add(button3);
        background.add(buttonExit);

        frame.add(background);
        frame.setSize(382,495);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
