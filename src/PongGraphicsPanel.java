import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGraphicsPanel extends JPanel implements ActionListener, KeyListener {

    private static final int PANEL_WIDTH = 450;
    private static final int PANEL_HEIGHT = 500;
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_SIZE = 20;
    private static final int PADDLE_SPEED = 5;
    private static final int BALL_SPEED = 2;

    private Timer timer;
    private int paddleY = PANEL_HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private int paddleDY = 0;
    private int ballX = PANEL_WIDTH / 2 - BALL_SIZE / 2;
    private int ballY = PANEL_HEIGHT / 2 - BALL_SIZE / 2;
    private int ballDX = BALL_SPEED;
    private int ballDY = BALL_SPEED;
    private int score = 0;
    private String name;

    public PongGraphicsPanel(String name) {
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

        // Draw paddle
        g.fillRect(PANEL_WIDTH - PADDLE_WIDTH - 30, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw ball
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        // Draw score
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(name + "'s Score: " + score, 10, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movePaddle();
        moveBall();
        repaint();
    }

    private void movePaddle() {
        if (paddleY + paddleDY >= 0 && paddleY + paddleDY <= PANEL_HEIGHT - PADDLE_HEIGHT) {
            paddleY += paddleDY;
        }
    }

    private void moveBall() {
        if (ballX + ballDX < 0) {
            ballDX = BALL_SPEED;
        }
        if (ballX + ballDX > PANEL_WIDTH - BALL_SIZE - PADDLE_WIDTH - 30 && ballY + ballDY >= paddleY && ballY + ballDY <= paddleY + PADDLE_HEIGHT) {
            ballDX = -BALL_SPEED;
            score++;
        }
        if (ballX + ballDX > PANEL_WIDTH - BALL_SIZE) {
            resetGame();
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
        score = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            paddleDY =- PADDLE_SPEED;
        }
        if (key == KeyEvent.VK_DOWN) {
            paddleDY = PADDLE_SPEED;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            paddleDY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
