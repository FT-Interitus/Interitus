package de.ft.robocontrol.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.Programm;
import de.ft.robocontrol.ProgrammingSpace;
import de.ft.robocontrol.loading.AssetLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Wire {
    private VisibleObjects left_connection;
    private VisibleObjects right_connection;

    private boolean space_between_blocks = false;
    private boolean movebymouse = false;
    private boolean canplaceanewwirenode = false;

    private Vector3 tempvector = new Vector3();


    public Wire(VisibleObjects left_connection,Block right_connection) {
        this.left_connection = left_connection;
        this.right_connection = right_connection;
    }

    public Wire(VisibleObjects left_connection) {
        this.left_connection = left_connection;
    }






    public void draw() {


        if(!Gdx.input.isKeyPressed(Input.Keys.N)) {
             canplaceanewwirenode = true;
        }


        if(space_between_blocks) {

            if(movebymouse) {

                if (left_connection== null) { //Selbst zerstören wenn ein Block gelöscht wird mit der die Wire verbunden war
                    BlockVar.wires.remove(this);
                    BlockVar.visiblewires.remove(this);
                    BlockVar.movingwires = null;


                }

            if(Gdx.input.isKeyJustPressed(Input.Keys.N)&&canplaceanewwirenode) {


                    WireNode tempwirenode = new WireNode(BlockVar.movingwires,(int)ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x,(int)ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y,10,10);

                    BlockVar.wireNodes.add(tempwirenode);
                    BlockVar.visibleWireNodes.add(tempwirenode);

                    this.right_connection = tempwirenode;


                        BlockVar.movingwires.movebymouse = false;
                        BlockVar.movingwires.space_between_blocks = true;
                        BlockVar.movingwires.right_connection = tempwirenode;


                    BlockVar.movingwires = null;



                    tempwirenode.setWire_right(new Wire(tempwirenode));
                    //tempwirenode.getWire_right().space_between_blocks = false;
                    tempwirenode.getWire_right().movebymouse = true;
                    tempwirenode.getWire_right().space_between_blocks = true;
                   BlockVar.movingwires =tempwirenode.getWire_right();
                   BlockVar.visiblewires.add(tempwirenode.getWire_right());
                   //BlockVar.wires.add(tempwirenode.getWire_right());




            }





                boolean temp = false;
                if(!ProgrammingSpace.batch.isDrawing()) {
                    ProgrammingSpace.batch.begin();
                    temp = true;
                }

                Sprite sprite = new Sprite(AssetLoader.wire);



float a = left_connection.getX_exit() - ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x;
float b = left_connection.getY_exit() - ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y;

                sprite.setPosition(left_connection.getX_exit(),left_connection.getY_exit());


                double weite = Math.sqrt(a * a + b * b);

                //sprite.setOrigin(left_connection.getX_exit(),left_connection.getY_exit());

               // sprite.setOrigin(left_connection.getX_exit(),left_connection.getY_exit());


                if(ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-left_connection.getX_exit()>=0) {
                    sprite.setRotation((float) ((float) Math.atan((float) ((ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y - left_connection.getY_exit()) / (ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x - left_connection.getX_exit()))) * 180 / Math.PI));
                }else{
                    sprite.setRotation((float) ((float) Math.atan((float) ((ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y - left_connection.getY_exit()) / (ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x - left_connection.getX_exit()))) * 180 / Math.PI)+180);

                }
            //   ProgrammingSpace.batch.draw(AssetLoader.switch_background_white,873,575,5,5);



             //   System.out.println((float) ((float) Math.acos((float) ((ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-left_connection.getX_exit())/weite))*180/Math.PI));
              sprite.setSize((float) weite,5);
              sprite.setOrigin(0,0);
             //   sprite.setSize(50,50);
              //  sprite.setRotation(20);
               // sprite.setRotation();



               // System.out.println("X: "+left_connection.getX_exit()+" Y: "+left_connection.getY_exit()+" Mouse: X:" + ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x+" Y:"+ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y+" Origion X:"+sprite.getOriginX()+" Y:"+sprite.getOriginY()+"   Rotation "+sprite.getRotation());


                sprite.draw(ProgrammingSpace.batch);


                //ProgrammingSpace.batch.draw(sprite,left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y,(float) weite,10);
                //ProgrammingSpace.batch.draw(sprite,left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y,);


                if(temp) {
                    ProgrammingSpace.batch.end();
                }




            }else {


                if (left_connection== null || right_connection == null) { //Selbst zerstören wenn ein Block gelöscht wird mit der die Wire verbunden war
                    BlockVar.wires.remove(this);
                    BlockVar.visiblewires.remove(this);


                    if(right_connection!=null) {
                        right_connection.getblock().setWire_left(null);
                        right_connection = null;
                        System.out.println("Tets");
                    }

                    if(left_connection!=null) {
                        left_connection.getblock().setWire_right(null);
                        System.out.println("Tets");
                        left_connection = null;
                    }

                }






                try {
                    boolean temp = false;
                    if (!ProgrammingSpace.batch.isDrawing()) {
                        ProgrammingSpace.batch.begin();
                        temp = true;
                    }


                    Sprite sprite = new Sprite(AssetLoader.wire);

                    float a = left_connection.getX_exit() - right_connection.getX_entrance();
                    float b = left_connection.getY_exit() - right_connection.getY_entrance();

                    sprite.setPosition(left_connection.getX_exit(), left_connection.getY_exit());


                    double weite = Math.sqrt(a * a + b * b);

                    if (right_connection.getX_entrance() - left_connection.getX_exit() > 0) {
                        sprite.setRotation((float) ((float) Math.atan((float) ((right_connection.getY_entrance() - left_connection.getY_exit()) / (right_connection.getX_entrance() - left_connection.getX_exit()))) * 180 / Math.PI));

                    } else {
                        sprite.setRotation((float) ((float) Math.atan((float) ((right_connection.getY_entrance() - left_connection.getY_exit()) / (right_connection.getX_entrance() - left_connection.getX_exit()))) * 180 / Math.PI) + 180);

                    }


                    sprite.setSize((float) weite, 5);
                    sprite.setOrigin(0, 0);


                    sprite.draw(ProgrammingSpace.batch);

                    if (temp) {
                        ProgrammingSpace.batch.end();
                    }
                }catch (Exception e) {
                    //Falls der Block der Verbunden ist gerade gelöscht wird
                    try {
                        ProgrammingSpace.batch.end(); //Damit der Render Prozess weiterläuft
                    }catch (IllegalStateException I) {

                    }
                }
            }


        }


    }


    public boolean isSpace_between_blocks() {
        return space_between_blocks;
    }

    public void setLeft_connection(VisibleObjects left_connection) {
        this.left_connection = left_connection;
    }

    public void setRight_connection(VisibleObjects right_connection) {
        this.right_connection = right_connection;
    }

    public Block getLeft_connection() {
        return left_connection.getblock();
    }

    public Block getRight_connection() {
        return right_connection.getblock();
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

    public boolean isvisible() {

        if(movebymouse)  {
            return true;
        }

        try {
            if(left_connection.isVisible()) {
                return true;
            }

        }catch (Exception e) {

        }

        try {
            if (right_connection.isVisible()) {
                return true;
            }
        }catch (Exception e) {

        }

        return false;




    }






}
