import javax.swing.*;
public class MainFrame  {
    public MainFrame() {
        JFrame frame = new JFrame("Intro to Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(768, 512);
        frame.setLocation(500, 200);

        // create and add panel
        GraphicsPanel panel = new GraphicsPanel();
        frame.add(panel);

        // display the frame
        frame.setVisible(true);
    }
}