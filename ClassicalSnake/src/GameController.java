import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;

public class GameController implements GameConstants{

    private final int[] snakeX = new int[gameUnit];
    private final int[] snakeY = new int[gameUnit];

    private int bodyParts = 6;
    private int applesEaten = 0;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;

    public void startGame(){
        newApple();

    }
    public void newApple(){

    }
    public void paintComponent(Graphics g){}
    public void draw(Graphics g){}
    public void move(){}
    public void checkApp(){}
    public void checkCollision(){}

}
