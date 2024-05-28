import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        player = new Player("src/ghostleft.png", "src/ghostright.png", name, 50, 250);

        this.setFocusable(true); // Makes this panel active for keylistener events
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false);

        // Initialize the timer to trigger action every 2 to 7 seconds
        timer = new Timer(2000 + random.nextInt(5000), this);
        timer.start();
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

        // Player movement handling
        if (pressedKeys[65]) { // A
            player.faceLeft();
            player.moveLeft();
        }
        if (pressedKeys[68]) { // D
            player.faceRight();
            player.moveRight();
        }
        if (pressedKeys[87]) { // W
            player.moveUp();
        }
        if (pressedKeys[83]) { // S
            player.moveDown();
        }

        // Check for collision with diamond
        if (diamondX != -100 && diamondY != -100 && player.getBounds().intersects(new Rectangle(diamondX, diamondY, diamond.getWidth(), diamond.getHeight()))) {
            player.incrementScore();
            diamondX = -100; // Move diamond off-screen after collection
            diamondY = -100;
        }

        // Repaint the panel to reflect player movement
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Randomize the diamond position
        diamondX = random.nextInt(getWidth() - diamond.getWidth());
        diamondY = random.nextInt(getHeight() - diamond.getHeight());
        repaint(); // Repaint the panel to show the diamond
    }

    @Override
    public void keyTyped(KeyEvent e) {
    } // Unimplemented

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key >= 0 && key < pressedKeys.length) {
            pressedKeys[key] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key >= 0 && key < pressedKeys.length) {
            pressedKeys[key] = false;
        }
    }
}