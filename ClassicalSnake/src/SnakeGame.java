import javax.swing.*;

public class SnakeGame {
    SnakeGame(){
        GameBoard board=new GameBoard();
        JFrame frame=new JFrame();
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }
}
