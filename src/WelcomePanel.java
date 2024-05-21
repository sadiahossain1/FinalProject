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
    private BufferedImage door;


    public WelcomePanel(JFrame frame) {
        enclosingFrame = frame;
        try {
            door = ImageIO.read(new File("src/dungeondoor.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        textField = new JTextField(10);
        clearButton = new JButton("Clear");
        dungeonGame = new JButton("Dungeon Game");
        add(textField);  // textField doesn't need a listener since nothing needs to happen when we type in text
        add(clearButton);
        add(dungeonGame);
        clearButton.addActionListener(this);
        dungeonGame.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.BLACK);
        g.drawString("Please enter your name:", 150, 50);
        g.drawImage(door, 80, 150, null);
        textField.setLocation(210, 65);
        clearButton.setLocation(280, 100);
        dungeonGame.setLocation(70, 270);
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