import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomePanel extends JPanel implements ActionListener {

    private JTextField textField;
    private JButton clearButton;
    private JFrame enclosingFrame;
    private JButton dungeonGame;
    private JButton salGame;
    private BufferedImage door;
    private BufferedImage snakesAndLadders;


    public WelcomePanel(JFrame frame) {
        enclosingFrame = frame;
        try {
            door = ImageIO.read(new File("src/dungeondoor.png"));
            snakesAndLadders = ImageIO.read(new File("src/snakesandladders.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        textField = new JTextField(10);
        clearButton = new JButton("Clear");
        dungeonGame = new JButton("Dungeon Game");
        salGame = new JButton("Snakes and Ladders");
        add(textField);  // textField doesn't need a listener since nothing needs to happen when we type in text
        add(clearButton);
        add(dungeonGame);
        add(salGame);
        clearButton.addActionListener(this);
        dungeonGame.addActionListener(this);
        salGame.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.BLACK);
        g.drawString("Please enter your name:", 150, 50);
        g.drawImage(door, 70, 150, null);
        g.drawImage(snakesAndLadders, 220, 150, null);
        textField.setLocation(210, 65);
        clearButton.setLocation(235, 100);
        dungeonGame.setLocation(50, 270);
        salGame.setLocation(200, 270);
    }

    // ACTIONLISTENER INTERFACE METHODS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button == dungeonGame) {
                String playerName = textField.getText();
                DungeonMainFrame f = new DungeonMainFrame(playerName);
                enclosingFrame.setVisible(false);
            } else {
                textField.setText("");
            }
        }
    }
}