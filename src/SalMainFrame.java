import javax.swing.*;
public class SalMainFrame  {
    public SalMainFrame() {
        JFrame frame = new JFrame("Snakes and Ladders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLocation(500, 200);

        // create and add panel
        SalGraphicsPanel panel = new SalGraphicsPanel();
        //frame.add(panel);

        // display the frame
        frame.setVisible(true);
    }
}