package de.ft.robocontrol.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.ProgrammingSpace;
import de.ft.robocontrol.loading.AssetLoader;

public class Wire {
    private Block left_connection;
    private Block right_connection;

    private boolean space_between_blocks = false;
    private boolean movebymouse = false;


    public Wire(Block left_connection,Block right_connection) {
        this.left_connection = left_connection;
        this.right_connection = right_connection;
    }

    public Wire(Block left_connection) {
        this.left_connection = left_connection;
    }

    public void draw() {



        if(!space_between_blocks) {
            System.out.println("Test");
            if(movebymouse) {
                boolean temp = false;
                if(!ProgrammingSpace.batch.isDrawing()) {
                    ProgrammingSpace.batch.begin();
                    temp = true;
                }

                Sprite sprite = new Sprite(AssetLoader.wire);



float a = left_connection.getwireconnector_right().x - ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x;
float b = left_connection.getwireconnector_right().y - ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y;

                sprite.setPosition(left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y);

                double weite = Math.sqrt(a * a + b * b);

                System.out.println("test");
               sprite.setRotation((float) ((float) Math.acos((float) ((ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-left_connection.getwireconnector_right().x)/weite))*180/Math.PI));
                System.out.println((float) ((float) Math.acos((float) ((ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-left_connection.getwireconnector_right().x)/weite))*180/Math.PI));
               sprite.setSize((float) weite,5);
              //  sprite.setRotation(20);
               // sprite.setRotation();
                System.out.println("here");




                sprite.draw(ProgrammingSpace.batch);


                //ProgrammingSpace.batch.draw(sprite,left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y,(float) weite,10);
                //ProgrammingSpace.batch.draw(sprite,left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y,);


                if(temp) {
                    ProgrammingSpace.batch.end();
                }




            }else{

            }


        }


    }

    public boolean isSpace_between_blocks() {
        return space_between_blocks;
    }


    public Block getLeft_connection() {
        return left_connection;
    }

    public Block getRight_connection() {
        return right_connection;
    }

    public void setSpace_between_blocks(boolean space_between_blocks) {
        this.space_between_blocks = space_between_blocks;
    }

    public boolean isMovebymouse() {
        return movebymouse;
    }

    public void setMovebymouse(boolean movebymouse) {
        this.movebymouse = movebymouse;
    }
}
