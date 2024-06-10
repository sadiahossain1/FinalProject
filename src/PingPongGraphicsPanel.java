import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PingPongGraphicsPanel extends JPanel implements ActionListener, KeyListener {

    private static final int PANEL_WIDTH = 450;
    private static final int PANEL_HEIGHT = 500;
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_SIZE = 20;
    private static final int PADDLE_SPEED = 5;
    private static final int BALL_SPEED = 2;

    private Timer timer;
    private int paddle1Y = PANEL_HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private int paddle2Y = PANEL_HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private int paddle1DY = 0;
    private int paddle2DY = 0;
    private int ballX = PANEL_WIDTH / 2 - BALL_SIZE / 2;
    private int ballY = PANEL_HEIGHT / 2 - BALL_SIZE / 2;
    private int ballDX = BALL_SPEED;
    private int ballDY = BALL_SPEED;
    private int score1 = 0;
    private int score2 = 0;
    private String name;

    public PingPongGraphicsPanel(String name) {
        this.name = name;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        timer = new Timer(5, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        // Draw paddle 1
        g.fillRect(PANEL_WIDTH - PADDLE_WIDTH - 30, paddle1Y, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw paddle 2
        g.fillRect(20, paddle2Y, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw ball
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        // Draw scores
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Player 1 Score: " + score1, 10, 30);
        g.drawString("Player 2 Score: " + score2, PANEL_WIDTH - 180, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movePaddles();
        moveBall();
        repaint();
    }

    private void movePaddles() {
        if (paddle1Y + paddle1DY >= 0 && paddle1Y + paddle1DY <= PANEL_HEIGHT - PADDLE_HEIGHT) {
            paddle1Y += paddle1DY;
        }
        if (paddle2Y + paddle2DY >= 0 && paddle2Y + paddle2DY <= PANEL_HEIGHT - PADDLE_HEIGHT) {
            paddle2Y += paddle2DY;
        }
    }

    private void moveBall() {
        if (ballX + ballDX < 0) {
            resetGame();
            score2++;
        }
        if (ballX + ballDX > PANEL_WIDTH - BALL_SIZE - PADDLE_WIDTH - 30 && ballY + ballDY >= paddle1Y && ballY + ballDY <= paddle1Y + PADDLE_HEIGHT) {
            ballDX = -BALL_SPEED;
            score1++;
        }
        if (ballX + ballDX > PANEL_WIDTH - BALL_SIZE) {
            resetGame();
            score1++;
        }
        if (ballX + ballDX > PADDLE_WIDTH && ballX + ballDX < PADDLE_WIDTH + BALL_SIZE + 30 && ballY + ballDY >= paddle2Y && ballY + ballDY <= paddle2Y + PADDLE_HEIGHT) {
            ballDX = -BALL_SPEED;
        }
        if (ballY + ballDY < 0 || ballY + ballDY > PANEL_HEIGHT - BALL_SIZE) {
            ballDY = -ballDY;
        }

        ballX += ballDX;
        ballY += ballDY;
    }

    private void resetGame() {
        ballX = PANEL_WIDTH / 2 - BALL_SIZE / 2;
        ballY = PANEL_HEIGHT / 2 - BALL_SIZE / 2;
        ballDX = BALL_SPEED;
        ballDY = BALL_SPEED;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            paddle1DY = -PADDLE_SPEED;
        }
        if (key == KeyEvent.VK_S) {
            paddle1DY = PADDLE_SPEED;
        }
        if (key == KeyEvent.VK_UP) {
            paddle2DY = -PADDLE_SPEED;
        }
        if (key == KeyEvent.VK_DOWN) {
            paddle2DY = PADDLE_SPEED;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            paddle1DY = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            paddle2DY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
