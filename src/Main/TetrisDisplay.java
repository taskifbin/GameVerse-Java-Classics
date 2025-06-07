package Main;

import javax.swing.*;

public class TetrisDisplay {
    TetrisPanel gamePanel = new TetrisPanel();
    JFrame TetrisFrame = new JFrame();

    public TetrisDisplay() {

        // Tetris Frame
        TetrisFrame.setTitle("Tetris");
        TetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TetrisFrame.setResizable(false);
        TetrisFrame.setLocationRelativeTo(null);
        TetrisFrame.setVisible(true);


        // adding item to Tetris Frame
        TetrisFrame.add(gamePanel);
        TetrisFrame.pack();

        gamePanel.LaunchGame();

    }
}