package FlappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final Timer timer;
    private final ArrayList<Rectangle> pipes = new ArrayList<>();
    private final Random rand = new Random();

    private int birdY = 300, birdVelocity = 0;
    private final int birdX = 150;
    private int score = 0, highScore;
    private boolean gameOver = false, started = false;

    private final int gravity = 1;
    private final int jumpStrength = 12;

    private final JFrame parentFrame;

    public GamePanel(int previousHighScore, JFrame frame) {
        this.highScore = previousHighScore;
        this.parentFrame = frame;

        setBackground(new Color(140, 210, 255));
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(20, this);
        timer.start();

        addPipe(true);
        addPipe(true);
    }

    private void addPipe(boolean isStart) {
        int space = 180;
        int width = 100;
        int height = 40 + rand.nextInt(250);
        int x = isStart ? 800 + pipes.size() * 300 : pipes.get(pipes.size() - 1).x + 300;

        pipes.add(new Rectangle(x, 0, width, height));
        pipes.add(new Rectangle(x, height + space, width, 600 - height - space));
    }

    private void resetGame() {
        birdY = 300;
        birdVelocity = 0;
        score = 0;
        gameOver = false;
        started = true;
        pipes.clear();
        addPipe(true);
        addPipe(true);
        requestFocus();
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw ground
        g.setColor(new Color(250, 220, 130));
        g.fillRect(0, 550, 800, 50);

        // Bird
        g.setColor(Color.RED);
        g.fillOval(birdX, birdY, 30, 30);

        // Pipes
        g.setColor(new Color(0, 180, 0));
        for (Rectangle pipe : pipes) {
            g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
        }

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 36));
        g.drawString("Score: " + score, 30, 50);

        if (!started) {
            g.setFont(new Font("Segoe UI", Font.BOLD, 24));
            g.drawString("Press UP to Start", 280, 280);
        }

        if (gameOver) {
            g.setFont(new Font("Segoe UI", Font.BOLD, 30));
            g.drawString("Game Over!", 310, 250);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (started && !gameOver) {
            birdVelocity += gravity;
            birdY += birdVelocity;

            ArrayList<Rectangle> toRemove = new ArrayList<>();
            for (Rectangle pipe : pipes) {
                pipe.x -= 5;
                if (pipe.x + pipe.width < 0)
                    toRemove.add(pipe);
            }
            pipes.removeAll(toRemove);

            if (pipes.size() < 6)
                addPipe(false);

            Rectangle birdRect = new Rectangle(birdX, birdY, 30, 30);

            for (Rectangle pipe : pipes) {
                if (pipe.intersects(birdRect)) {
                    gameOver = true;
                    timer.stop();
                    if (score > highScore)
                        highScore = score;
                    MainMenu.setHighScore(highScore);
                    showGameOverPrompt();
                    return;
                }
            }

            if (birdY > 520 || birdY < 0) {
                gameOver = true;
                timer.stop();
                if (score > highScore)
                    highScore = score;
                MainMenu.setHighScore(highScore);
                showGameOverPrompt();
                return;
            }

            for (Rectangle pipe : pipes) {
                if (pipe.y == 0 && pipe.x + pipe.width == birdX) {
                    score++;
                }
            }
        }

        repaint();
    }

    private void showGameOverPrompt() {
        int result = JOptionPane.showConfirmDialog(this, "Game Over!\nScore: " + score + "\nContinue Playing?",
                "Play Again?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            parentFrame.dispose();
            new MainMenu();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (!started)
                started = true;
            if (!gameOver)
                birdVelocity = -jumpStrength;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
