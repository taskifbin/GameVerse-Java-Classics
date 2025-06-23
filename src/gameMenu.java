import javax.swing.*;
import java.awt.event.*;

public class gameMenu extends JFrame implements ActionListener {

    // Buttons
    JButton snakeBtn, tetrisBtn, flappyBirdBtn, logoutBtn;

    // Background Image
    JLabel backgroundLabel;

    public gameMenu() {
        setTitle("Game Menu");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background image using setContentPane()
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/background.jpg")); // use absolute classpath
        JLabel background = new JLabel(backgroundImage);
        setContentPane(background); // This sets the background correctly
        background.setLayout(null); // Must set layout here for absolute positioning

        // Snake Button
        ImageIcon snakeIcon = new ImageIcon(getClass().getResource("/images/snake_icon.png"));
        snakeBtn = new JButton(snakeIcon);
        snakeBtn.setBounds(100,100,250,250); // Adjust size as needed
        snakeBtn.setContentAreaFilled(false);
        snakeBtn.setBorderPainted(false);
        snakeBtn.addActionListener(this);
        background.add(snakeBtn); // Add to background panel

        // Tetris Button
        ImageIcon tetrisIcon = new ImageIcon(getClass().getResource("/images/tetris_icon.png"));
        tetrisBtn = new JButton(tetrisIcon);
        tetrisBtn.setBounds(360, 100, 250, 250);
        tetrisBtn.setContentAreaFilled(false);
        tetrisBtn.setBorderPainted(false);
        tetrisBtn.addActionListener(this);
        background.add(tetrisBtn);

        // Flappy Bird Button
        ImageIcon flappyBirdIcon = new ImageIcon(getClass().getResource("/images/flappy_bird_icon.png"));
        flappyBirdBtn = new JButton(flappyBirdIcon);
        flappyBirdBtn.setBounds(620, 100, 250, 250);
        flappyBirdBtn.setContentAreaFilled(false);
        flappyBirdBtn.setBorderPainted(false);
        flappyBirdBtn.addActionListener(this);
        background.add(flappyBirdBtn);

        // Logout Button
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(100, 240, 200, 30);
        logoutBtn.addActionListener(this);
        background.add(logoutBtn);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == snakeBtn) {
            // Launch Snake game
            try {
                // Assuming SnakeGame has a static method to start the game
                ClassicalSnake.Main.main(new String[]{});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error launching Snake game: " + ex.getMessage());
            }
        } else if (e.getSource() == tetrisBtn) {
            // Launch Tetris game
            try {
                // Assuming TetrisGame has a static method to start the game
                Tetris.TetrisGame.main(new String[]{});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error launching Tetris game: " + ex.getMessage());
            }
        } else if (e.getSource() == flappyBirdBtn) {
            // Launch Flappy Bird game
            try {
                // Assuming FlappyBirdGame has a static method to start the game
               FlappyBird.FlappyBird.main(new String[]{});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error launching Flappy Bird game: " + ex.getMessage());
            }
        } else if (e.getSource() == logoutBtn) {
            // Logout and return to Login screen
            dispose();
            new login(); // Assuming the Login class is in the login package
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new gameMenu());
    }
}