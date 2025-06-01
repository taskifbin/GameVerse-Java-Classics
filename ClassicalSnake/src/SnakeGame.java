import javax.swing.*;

public class SnakeGame {
    SnakeGame(){
        GameBoard board=new GameBoard(new GameController());
        JFrame frame=new JFrame("Classical Snake Game");
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }
}
