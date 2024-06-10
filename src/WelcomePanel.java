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
    private JButton simplePong;
    private JButton pongTwo;
    private BufferedImage door;
    private BufferedImage pingPong;


    public WelcomePanel(JFrame frame) {
        enclosingFrame = frame;
        try {
            door = ImageIO.read(new File("src/dungeondoor.png"));
            pingPong = ImageIO.read(new File ("src/pingpong.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        textField = new JTextField(10);
        clearButton = new JButton("Clear");
        dungeonGame = new JButton("Dungeon Game");
        simplePong = new JButton("Simple Pong");
        pongTwo = new JButton("Ping Pong");
        add(textField);  // textField doesn't need a listener since nothing needs to happen when we type in text
        add(clearButton);
        add(dungeonGame);
        add(simplePong);
        add(pongTwo);
        clearButton.addActionListener(this);
        dungeonGame.addActionListener(this);
        simplePong.addActionListener(this);
        pongTwo.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.BLACK);
        g.drawString("Please enter your name:", 130, 50);
        g.drawImage(door, 130, 150, null);
        g.drawImage(pingPong, 330, 150, null);
        textField.setLocation(210, 65);
        clearButton.setLocation(235, 100);
        dungeonGame.setLocation(100, 270);
        simplePong.setLocation(320, 270);
        pongTwo.setLocation(400, 270);
    }

    // ACTIONLISTENER INTERFACE METHODS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button == dungeonGame) {
                String playerName = textField.getText();
                DungeonMainFrame f = new DungeonMainFrame(playerName);
                enclosingFrame.setVisible(false);
            } if (button == simplePong){
                String playerName = textField.getText();
                PongMainFrame p = new PongMainFrame(playerName);
                enclosingFrame.setVisible(false);
            } if (button == pongTwo) {
                String playerName = textField.getText();
                PingPongMainFrame p = new PingPongMainFrame(playerName);
                enclosingFrame.setVisible(false);
            }
            else {
                textField.setText("");
            }
        }
    }
}