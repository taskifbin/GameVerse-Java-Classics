package FlappyBird;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(int currentHighScore) {
        setTitle("Flappy Bird");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel(currentHighScore, this));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
