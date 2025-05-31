import javax.swing.*;
import java.awt.*;

public class TetrisDisplay extends JFrame {
    MyFrame frame = new MyFrame();
    JPanel GamePanel = new JPanel();
    JPanel ScorePanel = new JPanel();
    JPanel MenuPanel = new JPanel();
    JPanel ButtonPanel = new JPanel();
    public TetrisDisplay() {
        GamePanel.setBackground(Color.BLACK);
        GamePanel.setBounds(250,80,500,750);
        GamePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        ScorePanel.setBackground(Color.LIGHT_GRAY);
        ScorePanel.setBounds(750,80,250,750);

        MenuPanel.setBackground(Color.LIGHT_GRAY);
        MenuPanel.setBounds(0,80,250,750);



        frame.add(GamePanel);
        frame.add(ScorePanel);
        frame.add(MenuPanel);
    }
}
