import javax.swing.*;
public class SalMainFrame  {
    public SalMainFrame() {
        JFrame frame = new JFrame("Snakes and Ladders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(665, 739);
        frame.setLocation(500, 150);

        // create and add panel
        SalGraphicsPanel panel = new SalGraphicsPanel();
        frame.add(panel);

        // display the frame
        frame.setVisible(true);
    }
}