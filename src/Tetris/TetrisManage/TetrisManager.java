package Tetris.TetrisManage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import Tetris.Mino.*;

public class TetrisManager {



    // Main.Main Play Area
    final int WIDTH = 360;
    final int HEIGHT = 720;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;


    // Tetris.Mino
    Mino CurrentMino;
    final int MINO_START_X;
    final int MINO_START_Y;

    // Tetris Drop
    public static int DropInterval = 30; // mino drops in every 45 frames

    // Next Tetris.Mino
    Mino NextMino;
    final int NEXT_MINO_X;
    final int NEXT_MINO_Y;
    public static ArrayList<Block> staticsBlocks = new ArrayList<Block>();

    // Effect
    boolean EffectCounterOn;
    int EffectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();

    // Game over
    boolean GameOver;


    //Score Box
    private int currentScore = 0;
    private int highScore = 0;
    private String currentUser = "player1"; // Replace with real username later
    int level = 1;
    int lines;
    public int score;


    public TetrisManager() {

        // Main.Main Play Area in the Frame
        left_x = (TetrisPanel.WIDTH/2) - (WIDTH/2); // that's in the middle
        right_x = left_x + WIDTH;
        top_y = 80;
        bottom_y = top_y + HEIGHT;

        // Tetris.Mino Start Position
        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        // Set the Starting Tetris.Mino
        CurrentMino = RandomMino();
        CurrentMino.setXY(MINO_START_X, MINO_START_Y);

        // Next Tetris.Mino
        NEXT_MINO_X = right_x + 175;
        NEXT_MINO_Y = top_y + 620;

        NextMino = RandomMino();
        NextMino.setXY(NEXT_MINO_X, NEXT_MINO_Y);


    }

    private Mino RandomMino() {

        // Get a random Tetris.Mino
        Mino mino = null;
        int rand = new Random().nextInt(7);

        switch (rand) {
            case 0:
                mino = new Mino_L1();
                break;
            case 1:
                mino = new Mino_L2();
                break;
            case 2:
                mino = new Mino_Square();
                break;
            case 3:
                mino = new Mino_I();
                break;
            case 4:
                mino = new Mino_Z1();
                break;
            case 5:
                mino = new Mino_Z2();
                break;
            case 6:
                mino = new Mino_T();
                break;
        }
        return mino;
    }

    public void update() {

        // Check if current mino is active
        if(CurrentMino.active == false){

            // if the mino is not active, put it into the Array Blocks
            staticsBlocks.add(CurrentMino.b[0]);
            staticsBlocks.add(CurrentMino.b[1]);
            staticsBlocks.add(CurrentMino.b[2]);
            staticsBlocks.add(CurrentMino.b[3]);

            // check if game over or not
            if(CurrentMino.b[0].x == MINO_START_X && CurrentMino.b[0].y == MINO_START_Y){
                // that means mino did not change it's possible from starting position
                GameOver = true;
                 // Save score to DB

            }

            if (GameOver) {

                currentScore = 0;
                // Reset score
                // Show "Game Over" message, etc.
            }

            CurrentMino.deactivating = false;

            // replace the currentMino with the NEXT Tetris.Mino
            CurrentMino = NextMino;
            CurrentMino.setXY(MINO_START_X, MINO_START_Y);
            NextMino = RandomMino();
            NextMino.setXY(NEXT_MINO_X, NEXT_MINO_Y);

            // when a mino becomes deactive, check if line's can be deleted
            CheckDelete();
        }


        CurrentMino.update();

    }

    private void CheckDelete(){

        int x = left_x;
        int y = top_y;
        int BlockCount = 0;
        int lineCount = 0;

        while(x < right_x && y < bottom_y){

            for(int i=0;i<staticsBlocks.size();i++){
                // if in (x,y) there is block then it count increase
                if(staticsBlocks.get(i).x == x && staticsBlocks.get(i).y == y){
                    BlockCount++;
                }
            }

            x += Block.SIZE;

            if(x == right_x){

                // if block count is 12 then this line is filled
                if(BlockCount == 12){

                    EffectCounterOn = true;
                    effectY.add(y);

                    for(int i=staticsBlocks.size()-1;i>=0;i--){

                        // remove all the block in y lines
                        // we need to delete reversely otherwise the index will change

                        if(staticsBlocks.get(i).y == y){
                            staticsBlocks.remove(i);
                        }

                    }

                    lineCount++;
                    lines++;

                    // Drop speed
                    // if the line score hit's a certain number, increase the drop speed
                    // 1 is the fastest

                    if(lines % 5 == 0 && DropInterval > 1){

                        level++;
                        if(DropInterval > 10){
                            DropInterval -= 5;
                        }
                        else{
                            DropInterval--;
                        }
                    }

                    // a line has been deleted so need to slide down all other blocks that are above it
                    for(int i=0;i<staticsBlocks.size();i++){
                        // if a block is abovs the current y, move it down by the block size
                        if(staticsBlocks.get(i).y < y){
                            staticsBlocks.get(i).y += Block.SIZE;
                        }
                    }

                }

                BlockCount = 0;
                x = left_x;
                y += Block.SIZE;
            }


        }

        // Add Score
        if(lineCount > 0){
            int singleLineScore = 10 * level;
            currentScore += singleLineScore * lineCount;
            score = currentScore;
            if (currentScore > highScore) {
                highScore = currentScore;
            }
        }

    }

    public void draw(Graphics2D g2) {

        // * First Fill box then border

        // Main.Main Game Frame
        g2.setColor(Color.BLACK);
        g2.fillRect(left_x, top_y, WIDTH, HEIGHT);

        // Main.Main Game Frame Border
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(8f));
        g2.drawRect(left_x-4, top_y-4, WIDTH+8, HEIGHT+9);

        // Draw Next Block Frame

        int x = right_x + 105;
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
        g2.drawString("NEXT", x+60, y+50);

        // Draw Score
        g2.setColor(Color.BLACK);
        g2.fillRect(x,top_y+10,300,320);
        g2.setColor(Color.GREEN);
        g2.drawRect(x, top_y+10,300,320);

        g2.setColor(Color.RED);
        x += 40;
        y = top_y + 90;
        g2.drawString("LEVEL: " + level, x, y);
        y += 70;
        g2.drawString("LINES: " + lines, x, y);
        y += 70;
        g2.drawString("SCORE: " + score, x, y);
        y += 70;
        g2.drawString("HIGH SCORE:"+ highScore, x, y);

        // Draw the CurrentMino
        if(CurrentMino != null){
            CurrentMino.draw(g2);
        }

        // Draw the Next Tetris.Mino
        NextMino.draw(g2);

        // Draw static array blocks
        for(int i=0;i<staticsBlocks.size();i++){
            staticsBlocks.get(i).draw(g2);
        }

        // Draw Effect
        if(EffectCounterOn){
            EffectCounter++;

            g2.setColor(Color.RED);
            for(int i=0;i< effectY.size();i++){
                g2.fillRect(left_x, effectY.get(i), WIDTH, Block.SIZE);
            }

            if(EffectCounter == 10){
                EffectCounter = 0;
                EffectCounterOn = false;
                effectY.clear();
            }
        }

        // Draw Pause and Game over
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setColor(Color.YELLOW);
        if(GameOver){
            x = left_x + 100;
            y = top_y + 400;
            g2.drawString("Game Over", x, y);
        }
        else if(TetrisKeyHandle.pausePressed){
            x = left_x + 120;
            y = top_y + HEIGHT/2;
            g2.drawString("PAUSED", x, y);
        }

    }
}
