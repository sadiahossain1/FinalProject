import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessGraphicsPanel extends JPanel {
    private int secretNumber;
    private int attemptsLeft;
    private JTextField guessField;
    private JLabel resultLabel;
    private String name;

    public GuessGraphicsPanel(String name) {
        this.name = name;
        // Generate a random number between 1 and 100
        Random random = new Random();
        secretNumber = random.nextInt(50) + 1;
        attemptsLeft = 10; // Number of attempts allowed

        // Guess input field
        JLabel guessLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10); // Set preferred size instead of preferred dimension
        guessField.setPreferredSize(new Dimension(100, 30));

        // Button to submit guess
        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessListener());

        // Result label
        resultLabel = new JLabel("You have " + attemptsLeft + " attempts left.");

        // Add components to the panel
        add(guessLabel);
        add(guessField);
        add(guessButton);
        add(resultLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier New", Font.BOLD, 16));
        g.drawString("I am a number between 1 and 50.", 30, 100);
        g.drawString(" What am I?", 100, 120);
    }

    public class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (attemptsLeft <= 0) {
                JOptionPane.showMessageDialog(null, "Sorry, you ran out of attempts. The secret number was: " + secretNumber);
                System.exit(0);
            }

            int guess;
            try {
                guess = Integer.parseInt(guessField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                return;
            }

            if (guess < secretNumber) {
                JOptionPane.showMessageDialog(null, "Too low! Try again.");
            } else if (guess > secretNumber) {
                JOptionPane.showMessageDialog(null, "Too high! Try again.");
            } else {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number: " + secretNumber);
                System.exit(0);
            }

            attemptsLeft--;
            resultLabel.setText("You have " + attemptsLeft + " attempts left.");

            if (attemptsLeft == 0) {
                JOptionPane.showMessageDialog(null, "Sorry, you ran out of attempts. The secret number was: " + secretNumber);
                System.exit(0);
            }

            guessField.setText("");
        }
    }
}
