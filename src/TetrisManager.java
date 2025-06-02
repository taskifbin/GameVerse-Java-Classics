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

        // * First Fill box then border

        // Main Game Frame
        g2.setColor(Color.BLACK);
        g2.fillRect(left_x, top_y, WIDTH, HEIGHT);

        // Main Game Frame Border
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(8f));
        g2.drawRect(left_x-4, top_y-4, WIDTH+8, HEIGHT+9);

        // Draw Next Block Frame

        int x = right_x + 100;
        int y = bottom_y - 200;

        // Next BLock
        g2.setColor(Color.BLACK);
        g2.fillRect(x,y,200,200);

        // Next Block Border
        g2.setColor(Color.GREEN);
        g2.drawRect(x-4, y-4, 200+8, 200+8);

        // Next Block "NEXT"
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x+60, y+60);


    }
}
