import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameBoard extends JPanel implements ActionListener,GameConstants {

    GameBoard(){}
    private GameController gameController;

    //    static final int screenWidth = 800;
//    static final int screenHeight = 600;
//    static final int unit = 20;
//    static final int gameUnit = (screenWidth * screenHeight) / (unit * unit);
//    static final int Delay = 80;
//    final int x[] = new int[gameUnit];
//    final int y[] = new int[gameUnit];
//
//    int bodypart = 3;
//    int appleX;
//    int appleY;
//    int applEaten;
//    char direction = 'R';
//    boolean runing = false;
//    Timer timer;
    Random random;

    GameBoard(GameController gameController) {
        random = new Random();
        this.gameController = gameController;
        this.setPreferredSize(new Dimension(ScreeenWidth, ScreenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(gameController));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Game logic here
        repaint();  // Redraw the panel
    }
    private class MyKeyAdapter extends KeyAdapter {
    private GameController Controller;

    public MyKeyAdapter(GameController Controller) {
        this.Controller = Controller;
    }
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }
}
