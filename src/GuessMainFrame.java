import javax.swing.*;

public class GuessMainFrame {
    public GuessMainFrame(String name) {
        JFrame frame = new JFrame("Guess the Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocation(500, 200);

        // Create and add panel
        GuessGraphicsPanel panel = new GuessGraphicsPanel(name); // Pass the frame as an argument
        frame.add(panel);

        // Display the frame
        frame.setVisible(true);
    }
}

