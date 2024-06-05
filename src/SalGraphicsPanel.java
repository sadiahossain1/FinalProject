import javax.swing.*;
import java.awt.*;

public class  SalGraphicsPanel extends JPanel {
    private final int[][] grid = {
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0}
    };
    private int width = 65;
    private int height = 70;
    private int count = 100;

    public SalGraphicsPanel() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    g.setColor(Color.BLUE);
                    g.fillRect(c * width, r * height, width, height);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Courier New", Font.BOLD, 18));
                    g.drawString(count + "", c * width, r * height);
                    count--;
                }
                if (grid[r][c] == 1) {
                    g.setColor(Color.black);
                    g.fillRect(c * width, r * height, width, height);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Courier New", Font.BOLD, 18));
                    g.drawString(count + "", c*width, r*height);
                    count--;
                }
            }
        }
    }
}
