import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class GraphicsPanel extends JPanel {
    private BufferedImage dungeonBackground;
    private BufferedImage diamond;

    public GraphicsPanel() {
        try {
            dungeonBackground = ImageIO.read(new File("src/dungeon.png"));
            diamond = ImageIO.read(new File("src/diamond.webp"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        g.drawImage(dungeonBackground, 0, 0, null);
        int random = (int) (Math.random() * 1);
        int x = (int) (Math.random() * 769);
        int y = (int) (Math.random() * 512);
        if (random == 1) {
            g.drawImage(diamond, 100, 100, null);
        }
    }
}
