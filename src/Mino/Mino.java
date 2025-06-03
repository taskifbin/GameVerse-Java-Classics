package Mino;

import java.awt.*;
import Main.*;


public class Mino {

    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    int AutoDropCounter = 0;

    public void create(Color c){
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }

    public void setXY(int x, int y){

    }

    public void updateXY(int direction){

    }

    public void update(){

        // Move the Mino using key

        // pressed up for rotate
        if(TetrisKeyHandle.upPressed){

        }

        if(TetrisKeyHandle.downPressed){
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;

            // When moved down, reset the autoDropCounter
            AutoDropCounter = 0;

            TetrisKeyHandle.downPressed = false;
        }
        if(TetrisKeyHandle.leftPressed){

            b[0].x -= Block.SIZE;
            b[1].x -= Block.SIZE;
            b[2].x -= Block.SIZE;
            b[3].x -= Block.SIZE;

            AutoDropCounter = 0;

            TetrisKeyHandle.leftPressed = false;
        }
        if(TetrisKeyHandle.rightPressed){

            b[0].x += Block.SIZE;
            b[1].x += Block.SIZE;
            b[2].x += Block.SIZE;
            b[3].x += Block.SIZE;

            AutoDropCounter = 0;
            TetrisKeyHandle.rightPressed = false;
        }

        // the counter increase in every frames
        AutoDropCounter++;
        if(AutoDropCounter == TetrisManager.DropInterval){

            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;
            AutoDropCounter = 0;

        }
    }

    public void draw(Graphics2D g2) {

        int margin = 2;

        g2.setColor(b[0].c);
        g2.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[1].x + margin, b[1].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[2].x + margin, b[2].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[3].x + margin, b[3].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));

    }


}




