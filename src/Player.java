import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    private static final double MOVE_AMT = 0.3;
    private BufferedImage rightImage;
    private BufferedImage leftImage;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;
    private int score;
    private String name;

    public Player(String leftImgPath, String rightImgPath, String playerName, int xCoord, int yCoord) {
        this.name = playerName;
        this.facingRight = true;
        this.xCoord = xCoord; // starting position
        this.yCoord = yCoord;
        this.score = 0;
        try {
            leftImage = ImageIO.read(new File(leftImgPath));
            rightImage = ImageIO.read(new File(rightImgPath));
        } catch (IOException e) {
            System.out.println("Error loading player images: " + e.getMessage());
        }
    }

    public int getxCoord() {
        return (int) xCoord;
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void faceRight() {
        facingRight = true;
    }

    public void faceLeft() {
        facingRight = false;
    }

    public void moveRight() {
        if (xCoord + MOVE_AMT <= 768) { // assuming 920 is the right boundary
            xCoord += MOVE_AMT;
        }
    }

    public void moveLeft() {
        if (xCoord - MOVE_AMT >= 0) { // assuming 0 is the left boundary
            xCoord -= MOVE_AMT;
        }
    }

    public void moveUp() {
        if (yCoord - MOVE_AMT >= 0) { // assuming 0 is the top boundary
            yCoord -= MOVE_AMT;
        }
    }

    public void moveDown() {
        if (yCoord + MOVE_AMT <= 512) { // assuming 435 is the bottom boundary
            yCoord += MOVE_AMT;
        }
    }

    public void incrementScore() {
        score++;
    }

    public BufferedImage getPlayerImage() {
        if (facingRight) {
            return rightImage != null ? rightImage : leftImage; // fallback to left image if right image is null
        } else {
            return leftImage != null ? leftImage : rightImage; // fallback to right image if left image is null
        }
    }

    // Method for collision detection
    public Rectangle getBounds() {
        BufferedImage currentImage = getPlayerImage();
        if (currentImage != null) {
            return new Rectangle((int) xCoord, (int) yCoord, currentImage.getWidth(), currentImage.getHeight());
        } else {
            return new Rectangle((int) xCoord, (int) yCoord, 0, 0);
        }
    }
}