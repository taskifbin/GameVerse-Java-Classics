import java.awt.*;

public class TetrisManager {

    // Main Play Area
    final int WIDTH = 360;
    final int HEIGHT = 720;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    public TetrisManager() {

        // Main Play Area in the Frame
        left_x = (TetrisPanel.WIDTH/2) - (WIDTH/2); // that's in the middle
        right_x = left_x + WIDTH;
        top_y = 80;
        bottom_y = top_y + HEIGHT;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {

    }
}
