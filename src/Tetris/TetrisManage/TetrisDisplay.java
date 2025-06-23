package Tetris.TetrisManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TetrisDisplay {
    private final TetrisPanel gamePanel = new TetrisPanel();
    private final JFrame TetrisFrame = new JFrame();

    public TetrisDisplay() {
        // Tetris Frame setup
        TetrisFrame.setTitle("Tetris");
        TetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TetrisFrame.setResizable(false);
        TetrisFrame.setLocationRelativeTo(null);
        TetrisFrame.setLayout(new BorderLayout());

        // Center: the game panel
        TetrisFrame.add(gamePanel, BorderLayout.CENTER);

        // South: exit button
        JPanel controlPanel = new JPanel();
        JButton exitBtn = new JButton("Quit");
        exitBtn.addActionListener((ActionEvent e) -> {
            // stop the game loop
            gamePanel.stopGame();
            // terminate JVM
            System.exit(0);
        });
        controlPanel.add(exitBtn);
        TetrisFrame.add(controlPanel, BorderLayout.SOUTH);

        // finalize
        TetrisFrame.pack();
        TetrisFrame.setVisible(true);

        gamePanel.LaunchGame();
    }
}
