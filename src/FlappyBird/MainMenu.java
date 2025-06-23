package FlappyBird;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame {
    private static int highScore = 0;

    public MainMenu() {
        setTitle("Flappy Bird - FlappyBird.Main Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Flappy Bird", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton newGameBtn = new JButton("New Game");
        JButton continueBtn = new JButton("Continue");
        JButton highScoreBtn = new JButton("High Score");

        newGameBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        continueBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        highScoreBtn.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        newGameBtn.addActionListener(e -> {
            highScore = 0;
            new GameFrame(highScore);
            dispose();
        });

        continueBtn.addActionListener(e -> {
            new GameFrame(highScore);
            dispose();
        });

        highScoreBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "High Score: " + highScore));

        buttonPanel.add(newGameBtn);
        buttonPanel.add(continueBtn);
        buttonPanel.add(highScoreBtn);

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void setHighScore(int score) {
        highScore = Math.max(highScore, score);
    }

    public static int getHighScore() {
        return highScore;
    }
}
