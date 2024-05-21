import javax.swing.*;
public class DungeonMainFrame  {
    public DungeonMainFrame(String name) {
        JFrame frame = new JFrame("Dungeon Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(768, 512);
        frame.setLocation(500, 200);

        // create and add panel
        DungeonGraphicsPanel panel = new DungeonGraphicsPanel(name);
        frame.add(panel);

        // display the frame
        frame.setVisible(true);
    }
}
