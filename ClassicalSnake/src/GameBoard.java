import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements GameConstants {

    private GameController gameController;

    public GameBoard(GameController controller) {
        this.gameController = controller;

        setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);

        controller.setRepaintCallback(this::repaint);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameController.changeDirection(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameController.draw(g);
    }
}