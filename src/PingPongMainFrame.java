import javax.swing.*;
public class PingPongMainFrame  {
    public PingPongMainFrame(String name) {
        JFrame frame = new JFrame("Simple Pong Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLocation(500, 200);

        // create and add panel
        PingPongGraphicsPanel panel = new PingPongGraphicsPanel(name);
        frame.add(panel);

        // display the frame
        frame.setVisible(true);
    }
}