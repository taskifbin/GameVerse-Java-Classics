package Mino;

import java.awt.*;
import Main.*;


public class Mino {

    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    int AutoDropCounter = 0;
    public int direction = 1; // Each Mino have 4 Direction for Rotation
    boolean leftCollision, rightCollision, downCollision;
    public boolean active = true;

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

        checkRoatationCollision();

        // if collision is occur then we can not rotate mino
        if(leftCollision == false && rightCollision == false && downCollision == false) {


            this.direction = direction;

            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }

    }

    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}

    public void checkMovementCollision(){

        leftCollision = false;
        rightCollision = false;
        downCollision = false;

        // always check block collision
        checkBlockCollision();


        // Check Frame Collision


        for(int i = 0; i < b.length; i++){

            // left wall
            if(b[i].x == TetrisManager.left_x){
                leftCollision = true;
            }

            if(b[i].x + Block.SIZE == TetrisManager.right_x){
                rightCollision = true;
            }

            if(b[i].y + Block.SIZE == TetrisManager.bottom_y){
                downCollision = true;
            }
        }
    }

    public void checkRoatationCollision(){
        leftCollision = false;
        rightCollision = false;
        downCollision = false;

        // always check Block collision
        checkBlockCollision();


        for(int i = 0; i < b.length; i++){

            if(tempB[i].x < TetrisManager.left_x){
                leftCollision = true;
            }

            if(tempB[i].x + Block.SIZE > TetrisManager.right_x){
                rightCollision = true;
            }

            if(tempB[i].y + Block.SIZE > TetrisManager.bottom_y){
                downCollision = true;
            }
        }
    }

    public void checkBlockCollision(){

        for(int i=0;i<TetrisManager.staticsBlocks.size();i++){

            int curr_x = TetrisManager.staticsBlocks.get(i).x;
            int curr_y = TetrisManager.staticsBlocks.get(i).y;

            for(int j=0;j<b.length;j++){
                // down
                if(b[j].y + Block.SIZE == curr_y && b[j].x == curr_x){
                    downCollision = true;
                }
                // left
                if(b[j].x - Block.SIZE == curr_x && b[j].y == curr_y){
                    leftCollision = true;
                }

                // right
                if(b[j].x + Block.SIZE == curr_x && b[j].y == curr_y){
                    rightCollision = true;
                }
            }

        }
    }

    public void update(){

        // Move the Mino using key

        // pressed up for rotate
        if(TetrisKeyHandle.upPressed){
            switch(direction){
                case 1:getDirection2();break;
                case 2:getDirection3();break;
                case 3:getDirection4();break;
                case 4:getDirection1();break;
            }
            TetrisKeyHandle.upPressed = false;
        }

        checkMovementCollision();

        if(TetrisKeyHandle.downPressed){

            if(downCollision == false) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;

                // When moved down, reset the autoDropCounter
                AutoDropCounter = 0;
            }

            TetrisKeyHandle.downPressed = false;
        }
        if(TetrisKeyHandle.leftPressed){

            if(leftCollision == false) {

                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;

            }


            TetrisKeyHandle.leftPressed = false;
        }
        if(TetrisKeyHandle.rightPressed){

            if(rightCollision == false) {


                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;
            }

            AutoDropCounter = 0;
            TetrisKeyHandle.rightPressed = false;
        }

        if(downCollision){
            active = false;
        }
        else {
            // the counter increase in every frames
            AutoDropCounter++;
            if (AutoDropCounter == TetrisManager.DropInterval) {

                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                AutoDropCounter = 0;

            }
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




