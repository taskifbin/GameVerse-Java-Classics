package Main;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class TetrisPanel extends JPanel implements Runnable {
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 1000;
    final int FPS = 60;

    Thread TetrisThread;
    TetrisManager tetrisManager;

    public TetrisPanel() {

        // panel
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);

        // Implement KeyListener
        this.addKeyListener(new TetrisKeyHandle());
        this.setFocusable(true);

        // Tetris Manager
        tetrisManager = new TetrisManager();

    }
    public void LaunchGame(){
        TetrisThread = new Thread(this);
        TetrisThread.start(); // it automatic called run method
    }

    @Override
    public void run() {

        // Game Loop ---> 1. Update() 2.repaint() 60 times per second
        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(TetrisThread !=null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update(){

        if(TetrisKeyHandle.pausePressed == false) {
            tetrisManager.update();
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tetrisManager.draw(g2);
    }
}
