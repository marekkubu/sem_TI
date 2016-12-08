import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marek on 6. 12. 2016.
 */
class Images{
    public static BufferedImage background = loadImage("img/bgr.png");
    public static BufferedImage open_doors = loadImage("img/open_doors.png");
    public static BufferedImage close_doors = loadImage("img/close_doors.png");


    public static BufferedImage loadImage(String name)
    {   BufferedImage img = null;
        try {
            img = ImageIO.read(new File(name));
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
        return img;
    }

}

public class Background extends JPanel {
    int yPosition = 354;
    int nextPosition = 98;

    /**
     * Obrazek pozadi
     */
    private BufferedImage img = null;
    private BufferedImage imgOpen = null;
    private BufferedImage imgDoors = null;
    private BufferedImage imgClose = null;

    /**
     * Vytvori pozadi se zadanym obrazkem
     *
     * @param img obrazek
     */
    public Background(BufferedImage img,BufferedImage imgOpen, BufferedImage imgClose) {
        this.img = img;
        this.imgOpen = imgOpen;
        this.imgClose = imgClose;
    }

    /**
     * Vykresli Obrazek
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img,0,0,null);

        if (Main.lift.door_open == false) {
            this.imgDoors=imgClose;
        }
        else if (Main.lift.door_open == true) {
            this.imgDoors=imgOpen;
        }
        g2d.drawImage(imgDoors,5,yPosition,null);
    }

}