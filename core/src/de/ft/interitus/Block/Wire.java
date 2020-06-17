package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.RechtsKlick;
import de.ft.interitus.Var;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.rightclick.RightClickButtonSelectEvent;
import de.ft.interitus.events.rightclick.RightClickCloseEvent;
import de.ft.interitus.events.rightclick.RightClickEventListener;
import de.ft.interitus.events.rightclick.RightClickOpenEvent;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.CheckKollision;

public abstract class Wire {
    private final Vector3 tempvector = new Vector3();
    private final Vector3 tempvector1 = new Vector3();
    private final float dicke = 3.5f;
    private VisibleObjects left_connection;
    private VisibleObjects right_connection;
    private boolean space_between_blocks = false;
    private boolean movebymouse = false;
    private boolean canplaceanewwirenode = false;
    final Wire INSTANCE = this;
    RightClickEventListener rightClickEventListener= new RightClickEventListener() {
        @Override
        public void openrightclickwindow(RightClickOpenEvent e) {

        }

        @Override
        public void closerightclickwindow(RightClickCloseEvent e) {

        }

        @Override
        public void buttonclickedinwindow(RightClickButtonSelectEvent e) {
            if (e.getButton().getText().contains("Löschen")) {
                if(Var.openprojects.get(Var.openprojectindex).mousehoveredwire==INSTANCE) {
                    try {
                        INSTANCE.left_connection.getblock().setRight(-1);
                        INSTANCE.right_connection.getblock().setLeft(-1);
                        INSTANCE.left_connection.setWire_right(null);
                        INSTANCE.right_connection.setWire_left(null);
                        INSTANCE.left_connection = null;
                        EventVar.rightClickEventManager.removeListener(INSTANCE.rightClickEventListener);
                    }catch (Exception ignored){}
                }
            }
        }
    };

    public Wire(final VisibleObjects left_connection, final Block right_connection) {
        this.left_connection = left_connection;
        this.right_connection = right_connection;
        EventVar.rightClickEventManager.addListener(rightClickEventListener);

    }

    public Wire(final VisibleObjects left_connection) {
        this.left_connection = left_connection;
        
        EventVar.rightClickEventManager.addListener(rightClickEventListener);
    }


    public void draw() {


        if (!Gdx.input.isKeyPressed(Input.Keys.N)) {
            canplaceanewwirenode = true;
        }


        if (space_between_blocks) {

            if (movebymouse) {


                if (Gdx.input.isButtonJustPressed(0)) {
                    int counter = 0;
                    for (int i = 0; i < Var.openprojects.get(Var.openprojectindex).visibleblocks.size(); i++) {
                        //TODO hier auch nach nodes testen eventuell will man die nur verschieben
                        if (CheckKollision.object(Var.openprojects.get(Var.openprojectindex).visibleblocks.get(i).getX_entrance(), Var.openprojects.get(Var.openprojectindex).visibleblocks.get(i).getY_entrance(), Var.openprojects.get(Var.openprojectindex).visibleblocks.get(i).getH_entrance(), Var.openprojects.get(Var.openprojectindex).visibleblocks.get(i).getW_entrance(), (int) ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y, 1, 1)) {
                            counter++;
                        }


                    }

                    if (counter == 0) {

                        Var.openprojects.get(Var.openprojectindex).wire_beginn.getBlockupdate().isconnectorclicked = false;
                        Var.openprojects.get(Var.openprojectindex).showleftdocker = false;

                        try {
                            Var.openprojects.get(Var.openprojectindex).wire_beginn.getBlockupdate().tempwire.getLeft_connection().setWire_right(null);
                            Var.openprojects.get(Var.openprojectindex).visiblewires.remove(Var.openprojects.get(Var.openprojectindex).wire_beginn.getBlockupdate().tempwire);
                            Var.openprojects.get(Var.openprojectindex).wires.remove(Var.openprojects.get(Var.openprojectindex).wire_beginn.getBlockupdate().tempwire);

                            try {

                                Var.openprojects.get(Var.openprojectindex).wire_beginn.getBlockupdate().tempwire.getRight_connectionObject().getwirenode().setWire_left(null);
                                Var.openprojects.get(Var.openprojectindex).wire_beginn.getBlockupdate().tempwire.setRight_connection(null);

                            } catch (NullPointerException e) {
                                //Falls hier keine Wire ist
                            }
                            Var.openprojects.get(Var.openprojectindex).wire_beginn.getBlockupdate().tempwire = null;
                        } catch (Exception ignored) {

                        }

                        Var.openprojects.get(Var.openprojectindex).movingwires = null;

                    }

                }


                if (left_connection == null) { //Selbst zerstören wenn ein Block gelöscht wird mit der die Wire verbunden war
                    Var.openprojects.get(Var.openprojectindex).wires.remove(this);
                    Var.openprojects.get(Var.openprojectindex).visiblewires.remove(this);
                    Var.openprojects.get(Var.openprojectindex).movingwires = null;
                    Var.openprojects.get(Var.openprojectindex).showleftdocker = false;


                    try {
                        getRight_connectionObject().getwirenode().setWire_left(null);
                    } catch (NullPointerException e) {
                        //Falls da nie was war
                    }


                    try {

                        getLeft_connectionObject().getwirenode().setWire_right(null);
                    } catch (NullPointerException ignored) {

                    }

                    return; //Damit die Funktion interupted wird

                }



                if (Gdx.input.isKeyJustPressed(Input.Keys.N) && canplaceanewwirenode&&Var.openprojects.get(Var.openprojectindex).movingwires!=null) {



                    WireNode tempwirenode =  Var.openprojects.get(Var.openprojectindex).projectType.getWireNodeGenerator().generate(Var.openprojects.get(Var.openprojectindex).movingwires, (int) ProgrammingSpace.viewport.unproject(tempvector.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(tempvector1.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y, WireNode.public_w, WireNode.public_h);

                    Var.openprojects.get(Var.openprojectindex).wireNodes.add(tempwirenode);
                    Var.openprojects.get(Var.openprojectindex).visibleWireNodes.add(tempwirenode);

                    this.right_connection = tempwirenode;

        try {
            Var.openprojects.get(Var.openprojectindex).movingwires.movebymouse = false;
            Var.openprojects.get(Var.openprojectindex).movingwires.space_between_blocks = true;
            Var.openprojects.get(Var.openprojectindex).movingwires.right_connection = tempwirenode;
        }catch (NullPointerException e ) {
            DisplayErrors.customErrorstring = "Keine WireNodes in der Luft plazieren!";
            DisplayErrors.error = e;
        }

                    Var.openprojects.get(Var.openprojectindex).movingwires = null;


                    tempwirenode.setWire_right( Var.openprojects.get(Var.openprojectindex).projectType.getWireGenerator().generate(tempwirenode));
                    //tempwirenode.getWire_right().space_between_blocks = false;
                    tempwirenode.getWire_right().movebymouse = true;
                    tempwirenode.getWire_right().space_between_blocks = true;
                    Var.openprojects.get(Var.openprojectindex).movingwires = tempwirenode.getWire_right();
                    Var.openprojects.get(Var.openprojectindex).visiblewires.add(tempwirenode.getWire_right());
                    //Var.openprojects.get(Var.openprojectindex).wires.add(tempwirenode.getWire_right());


                }


                boolean temp = false;
                if (!ProgrammingSpace.batch.isDrawing()) {
                    ProgrammingSpace.batch.begin();
                    temp = true;
                }

                Sprite sprite = new Sprite(AssetLoader.wire);


                float a = left_connection.getX_exit() - ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x;
                float b = left_connection.getY_exit() - ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y;

                sprite.setPosition(left_connection.getX_exit(), left_connection.getY_exit());


                double weite = Math.sqrt(a * a + b * b);

                //sprite.setOrigin(left_connection.getX_exit(),left_connection.getY_exit());

                // sprite.setOrigin(left_connection.getX_exit(),left_connection.getY_exit());


                if (ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x - left_connection.getX_exit() >= 0) {
                    sprite.setRotation((float) ((float) Math.atan((ProgrammingSpace.viewport.unproject(tempvector.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - left_connection.getY_exit()) / (ProgrammingSpace.viewport.unproject(tempvector1.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - left_connection.getX_exit())) * 180 / Math.PI));
                } else {
                    sprite.setRotation((float) ((float) Math.atan((ProgrammingSpace.viewport.unproject(tempvector.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - left_connection.getY_exit()) / (ProgrammingSpace.viewport.unproject(tempvector1.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - left_connection.getX_exit())) * 180 / Math.PI) + 180);

                }

                //   ProgrammingSpace.batch.draw(AssetLoader.switch_background_white,873,575,5,5);


                //   System.out.println((float) ((float) Math.acos((float) ((ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x-left_connection.getX_exit())/weite))*180/Math.PI));
                sprite.setSize((float) weite, dicke);
                sprite.setOrigin(0, 0);
                //   sprite.setSize(50,50);
                //  sprite.setRotation(20);
                // sprite.setRotation();


                // System.out.println("X: "+left_connection.getX_exit()+" Y: "+left_connection.getY_exit()+" Mouse: X:" + ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).x+" Y:"+ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0)).y+" Origion X:"+sprite.getOriginX()+" Y:"+sprite.getOriginY()+"   Rotation "+sprite.getRotation());


                sprite.draw(ProgrammingSpace.batch);


                //ProgrammingSpace.batch.draw(sprite,left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y,(float) weite,10);
                //ProgrammingSpace.batch.draw(sprite,left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y,);


                if (temp) {
                    ProgrammingSpace.batch.end();
                }


            } else {


                if (left_connection == null || right_connection == null) { //Selbst zerstören wenn ein Block gelöscht wird mit der die Wire verbunden war
                    Var.openprojects.get(Var.openprojectindex).wires.remove(this);
                    Var.openprojects.get(Var.openprojectindex).visiblewires.remove(this);


                    if (right_connection != null) {
                        right_connection.setWire_left(null);
                        right_connection = null;
                    }

                    if (left_connection != null) {
                        left_connection.setWire_right(null);

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

                    if (right_connection.getX_entrance() - left_connection.getX_exit() >= 0) {
                        sprite.setRotation((float) ((float) Math.atan((right_connection.getY_entrance() - left_connection.getY_exit()) / (right_connection.getX_entrance() - left_connection.getX_exit())) * 180 / Math.PI));

                    } else {
                        sprite.setRotation((float) ((float) Math.atan((right_connection.getY_entrance() - left_connection.getY_exit()) / (right_connection.getX_entrance() - left_connection.getX_exit())) * 180 / Math.PI) + 180);

                    }


                    sprite.setSize((float) weite, dicke);
                    sprite.setOrigin(0, 0);


                    sprite.draw(ProgrammingSpace.batch);
                    if (CheckKollision.objectwithrotation(sprite.getX(), sprite.getY(), sprite.getHeight(), sprite.getWidth(), sprite.getRotation(), ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x, ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y, 1, 1, 0) && Var.openprojects.get(Var.openprojectindex).mousehoveredwire != this) {
                        Var.openprojects.get(Var.openprojectindex).mousehoveredwire = this;

                    }

                    if (!RechtsKlick.popupmanager.isPopupopen()&&Var.openprojects.get(Var.openprojectindex).mousehoveredwire == this && !CheckKollision.objectwithrotation(sprite.getX(), sprite.getY(), sprite.getHeight(), sprite.getWidth(), sprite.getRotation(), ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x, ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y, 1, 1, 0)) {
                        System.out.println("Popup disable"); //TODO here is an error
                        Var.openprojects.get(Var.openprojectindex).mousehoveredwire = null;
                    }


                 /*


                    if(clickedonwire&&Gdx.input.isButtonPressed(0)&&!CheckKollision.objectwithrotation(sprite.getX(),sprite.getY(),sprite.getHeight(),sprite.getWidth(),sprite.getRotation(),ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x,ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y,1,1,0)) {
                        WireNode node = new WireNode(this,(int)ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x,(int)ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y,10,10);
                        Wire newwire = new Wire(node,this.getRight_connection());
                        this.getRight_connection().setWire_left(newwire);
                        this.setRight_connection(node);
                      //  newwire.setRight_connection(this.getRight_connectionObject());
                        newwire.setMovebymouse(false);
                        newwire.setSpace_between_blocks(true);

                        node.setWire_right(newwire);
                        node.setWire_left(this);

                        Var.openprojects.get(Var.openprojectindex).wires.add(newwire);
                        Var.openprojects.get(Var.openprojectindex).visiblewires.add(newwire);

                        Var.openprojects.get(Var.openprojectindex).wireNodes.add(node);
                        Var.openprojects.get(Var.openprojectindex).visibleWireNodes.add(node);


                    }

                     */

                    if (temp) {
                        ProgrammingSpace.batch.end();
                    }
                } catch (Exception e) {
                    //Falls der Block der Verbunden ist gerade gelöscht wird
                    try {
                        ProgrammingSpace.batch.end(); //Damit der Render Prozess weiterläuft
                    } catch (IllegalStateException ignored) {

                    }
                }
            }


        }


    }


    public boolean isSpace_between_blocks() {
        return space_between_blocks;
    }

    public void setSpace_between_blocks(boolean space_between_blocks) {
        this.space_between_blocks = space_between_blocks;
    }

    public Block getLeft_connection() {
        return left_connection.getblock();
    }

    public void setLeft_connection(VisibleObjects left_connection) {
        this.left_connection = left_connection;
    }

    public Block getRight_connection() {
        return right_connection.getblock();
    }

    public void setRight_connection(VisibleObjects right_connection) {
        this.right_connection = right_connection;
    }

    public VisibleObjects getLeft_connectionObject() {
        return left_connection;
    }

    public VisibleObjects getRight_connectionObject() {
        return right_connection;
    }

    public boolean isMovebymouse() {
        return movebymouse;
    }

    public void setMovebymouse(boolean movebymouse) {
        this.movebymouse = movebymouse;
    }

    public boolean isvisible() {

        if (movebymouse) {
            return true;
        }

        try {
            if (left_connection.isVisible()) {
                return true;
            }

        } catch (Exception e) {

        }

        try {
            if (right_connection.isVisible()) {
                return true;
            }
        } catch (Exception e) {

        }

        return false;


    }


}
