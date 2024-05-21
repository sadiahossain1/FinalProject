
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class DungeonGraphicsPanel extends JPanel implements KeyListener, ActionListener {

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
        player = new Player("src/ghostleft.png", "src/dungeondoor.png", name);
        pressedKeys = new boolean[128];
        this.setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        this.requestFocusInWindow();
        this.addKeyListener(this);

        // Initialize the timer to trigger action every 1 to 5 seconds
        timer = new Timer(1000 + random.nextInt(2000), new ActionListener() {
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
        if (diamond != null) {
            g.drawImage(diamond, diamondX, diamondY, this);
        }
        g.drawImage(player.getPlayerImage(), player.getxCoord(), player.getyCoord(), this);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, 22));
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
    public void keyPressed(KeyEvent e) {
        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        pressedKeys[key] = true;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }

    // ----- MouseListener interface methods -----
//    public void mouseClicked(MouseEvent e) { }  // unimplemented; if you move your mouse while clicking,
//    // this method isn't called, so mouseReleased is best
//
//    public void mousePressed(MouseEvent e) { } // unimplemented
//
//    public void mouseEntered(MouseEvent e) { } // unimplemented
//
//    public void mouseExited(MouseEvent e) { } // unimplemented
    public void actionPerformed(ActionEvent e) {
    }

}