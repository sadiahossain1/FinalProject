//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Random;
//
//public class DungeonGraphicsPanel extends JPanel {
//
//    private BufferedImage dungeonBackground;
//    private BufferedImage diamond;
//    private Timer timer;
//    private boolean[] pressedKeys;
//    private int diamondX = -100; // Initial off-screen position
//    private int diamondY = -100; // Initial off-screen position
//    private Random random;
//    private Player player;
//
//    public DungeonGraphicsPanel(String name) {
//        random = new Random();
//        try {
//            dungeonBackground = ImageIO.read(new File("src/dungeon.png"));
//            diamond = ImageIO.read(new File("src/diamond.png"));
//        } catch (IOException e) {
//            System.out.println("Error loading images: " + e.getMessage());
//        }
//        player = new Player("src/tweetyleft.png", "src/tweetyright.png", name);
//
//        // Initialize the timer to trigger action every 2 to 5 seconds
//        timer = new Timer(2000 + random.nextInt(3000), new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Randomize the diamond position
//                diamondX = random.nextInt(getWidth() - diamond.getWidth());
//                diamondY = random.nextInt(getHeight() - diamond.getHeight());
//                repaint(); // Repaint the panel to show the diamond
//            }
//        });
//        timer.start(); // Start the timer
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(dungeonBackground, 0, 0, getWidth(), getHeight(), this);
//        g.drawImage(player.getPlayerImage(), player.getxCoord(), player.getyCoord(), null);
//        if (diamond != null) {
//            g.drawImage(diamond, diamondX, diamondY, this);
//        }
//        g.setFont(new Font("Courier New", Font.BOLD, 24));
//        g.drawString(player.getName() + "'s Score: " + player.getScore(), 20, 40);
//
//        // player moves left (A)
//        if (pressedKeys[65]) {
//            player.faceLeft();
//            player.moveLeft();
//        }
//
//        // player moves right (D)
//        if (pressedKeys[68]) {
//            player.faceRight();
//            player.moveRight();
//        }
//
//        // player moves up (W)
//        if (pressedKeys[87]) {
//            player.moveUp();
//        }
//
//        // player moves down (S)
//        if (pressedKeys[83]) {
//            player.moveDown();
//        }
//    }
//
//}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class DungeonGraphicsPanel extends JPanel {

    private BufferedImage dungeonBackground;
    private BufferedImage diamond;
    private Timer timer;
    private boolean[] pressedKeys;
    private int diamondX = -100; // Initial off-screen position
    private int diamondY = -100; // Initial off-screen position
    private Random random;
    private Player player;

    public DungeonGraphicsPanel(String name) {
        random = new Random();
        pressedKeys = new boolean[256]; // Initialize the pressedKeys array
        try {
            dungeonBackground = ImageIO.read(new File("src/dungeon.png"));
            diamond = ImageIO.read(new File("src/diamond.png"));
        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
        }
        player = new Player("src/tweetyleft.png", "src/tweetyright.png", name);


        // Initialize the timer to trigger action every 2 to 5 seconds
        timer = new Timer(2000 + random.nextInt(3000), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Randomize the diamond position
                diamondX = random.nextInt(getWidth() - diamond.getWidth());
                diamondY = random.nextInt(getHeight() - diamond.getHeight());
                repaint(); // Repaint the panel to show the diamond
            }
        });
        timer.start(); // Start the timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(dungeonBackground, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(player.getPlayerImage(), player.getxCoord(), player.getyCoord(), null);
        if (diamond != null) {
            g.drawImage(diamond, diamondX, diamondY, this);
        }
        g.setFont(new Font("Courier New", Font.BOLD, 24));
        g.drawString(player.getName() + "'s Score: " + player.getScore(), 20, 40);

        // player moves left (A)
        if (pressedKeys[65]) {
            player.faceLeft();
            player.moveLeft();
        }

        // player moves right (D)
        if (pressedKeys[68]) {
            player.faceRight();
            player.moveRight();
        }

        // player moves up (W)
        if (pressedKeys[87]) {
            player.moveUp();
        }

        // player moves down (S)
        if (pressedKeys[83]) {
            player.moveDown();
        }
    }
    public void keyTyped(KeyEvent e) { } // unimplemented



    // ----- MouseListener interface methods -----
    public void mouseClicked(MouseEvent e) { }  // unimplemented; if you move your mouse while clicking,
    // this method isn't called, so mouseReleased is best

    public void mousePressed(MouseEvent e) { } // unimplemented

    public void mouseEntered(MouseEvent e) { } // unimplemented

    public void mouseExited(MouseEvent e) { } // unimplemented

}