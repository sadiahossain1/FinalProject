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
    private int count = 1;

    public SalGraphicsPanel() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int r = grid.length - 1; r >= 0; r--) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(c * width, r * height, width, height);
                }
                if (grid[r][c] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillRect(c * width, r * height, width, height);
                }
                g.setColor(Color.BLACK);
                g.setFont(new Font("Courier New", Font.BOLD, 18));
                g.drawString(count - 100 + "", c*width+10, r*height+20);
                count++;
            }
        }
        g.setColor(Color.BLUE);
        g.fillOval(10, 650, 45, 45);
        // Draw player 2
        g.setColor(Color.PINK);
        g.fillOval(20, 630, 45, 45);
    }
}