import javax.swing.*;
public class PongMainFrame  {
    public PongMainFrame(String name) {
        JFrame frame = new JFrame("Simple Pong Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLocation(500, 200);

        // create and add panel
        PongGraphicsPanel panel = new PongGraphicsPanel(name);
        frame.add(panel);

        // display the frame
        frame.setVisible(true);
    }
}
