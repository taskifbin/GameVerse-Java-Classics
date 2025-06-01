import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

public class GameController implements GameConstants{

    private final int[] snakeX = new int[gameUnit];
    private final int[] snakeY = new int[gameUnit];

    private Runnable repaintCallback;

    static final int Delay = 80;
    private int bodyParts = 3;
    private int applesEaten = 0;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;

    public GameController(){
        random = new Random();
        //initial position
        snakeX[0] = unit * 5;
        snakeY[0] = unit * 5;
        startGame();
    }

    public void startGame(){
        placeApple();
        running = true;
        timer = new Timer(Delay, e -> update());
        timer.start();

    }
    public void placeApple(){
        appleX = random.nextInt(ScreenWidth / unit) * unit;
        appleY = random.nextInt(ScreenHeight / unit) * unit;
    }
    public void update(){
        if(!running) {return;}
        System.out.println("Snake head: (" + snakeX[0] + ", " + snakeY[0] + ")");
        move();
        checkApple();
        checkCollision();

        if (repaintCallback != null) {
            repaintCallback.run();
        }

    }
    public void draw(Graphics g){
       if(running){
           //Draw apple
           g.setColor(Color.RED);
           g.fillOval(appleX, appleY, unit, unit);

           //Draw Snake
           for (int i = 0; i < bodyParts; i++){
               if(i == 0){
                   g.setColor(Color.GREEN);
                   g.fillRect(snakeX[i], snakeY[i], unit, unit);
               }
               else {
                   g.setColor(new Color(25, 80, 245));
                   g.fillRect(snakeX[i], snakeY[i], unit, unit);
               }
           }
       }
       else gameOver(g);
    }
    public void setRepaintCallback(Runnable repaintCallback) {
        this.repaintCallback = repaintCallback;
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Game Over", (ScreenWidth - metrics.stringWidth("Game Over")) / 2, ScreenHeight / 2);

    }

    public void move(){
        //Move BOdy
        for (int i = bodyParts; i >0; i--){
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        //directions
        switch (direction){
            case 'U' : snakeY[0] -= unit; break;
            case 'D' : snakeY[0] += unit; break;
            case 'R' : snakeX[0] += unit; break;
            case 'L' : snakeX[0] -= unit; break;
        }

    }
    public void checkApple(){
        if (snakeX[0] == appleX && snakeY[0] == appleY) {
            bodyParts++;
            applesEaten++;
            placeApple();
        }
    }
    public void checkCollision(){
        // Check head body collsion
        for (int i = bodyParts; i > 0; i--) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                running = false;
                timer.stop();
            }
        }

        // Check wall collisions
        if (snakeX[0] < 0 || snakeX[0] >= ScreenWidth || snakeY[0] < 0 || snakeY[0] >= ScreenHeight) {
            running = false;
            timer.stop();
        }
    }
    public void changeDirection(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') direction = 'L';
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') direction = 'R';
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') direction = 'U';
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') direction = 'D';
                break;

        }
        System.out.println("Direction: " + direction);
    }

}
