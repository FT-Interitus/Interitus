/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import de.ft.interitus.DisplayErrors;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.popup.PopupMenue;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.block.BlockKillMovingWiresEvent;
import de.ft.interitus.events.rightclick.RightClickEventListener;
import de.ft.interitus.events.rightclick.RightClickOpenRequestEvent;
import de.ft.interitus.events.rightclick.RightClickPerformActionEvent;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.UI.UIElements.check.CheckKollision;
import de.ft.interitus.utils.Input;
import de.ft.interitus.utils.Vector3;

import java.awt.event.KeyEvent;

public abstract class Wire {
    final Wire INSTANCE = this;
    private final Vector3 tempvector = new Vector3();
    private final Vector3 tempvector1 = new Vector3();
    private final float dicke = 3.5f;
    //TODO L private final Sprite sprite = new Sprite();
    private VisibleObjects left_connection;
    private VisibleObjects right_connection;
    RightClickEventListener rightClickEventListener = new RightClickEventListener() {

        @Override
        public PopupMenue openrequest(RightClickOpenRequestEvent e, float Pos_X, float Pos_Y) {
            return null;
        }

        @Override
        public void performAction(RightClickPerformActionEvent e, PopupMenue popupMenue, int Buttonindex) {

        }
    };
    private boolean space_between_blocks = false;
    private boolean movebymouse = false;
    private boolean canplaceanewwirenode = false;

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


        if (!Input.isKeyPressed(KeyEvent.VK_N)) {
            canplaceanewwirenode = true;
        }


        if (space_between_blocks) {

            if (movebymouse) {

                ProjectManager.getActProjectVar().changes = true;

                if (Input.isButtonJustPressed(0)) {
                    int counter = 0;
                    for (int i = 0; i < ProjectManager.getActProjectVar().visibleblocks.size(); i++) {

                        if (CheckKollision.object(ProjectManager.getActProjectVar().visibleblocks.get(i).getX(), ProjectManager.getActProjectVar().visibleblocks.get(i).getY(), ProjectManager.getActProjectVar().visibleblocks.get(i).getW(), ProjectManager.getActProjectVar().visibleblocks.get(i).getH(), (int) ProgrammingSpace.blockCamera.unproject_x(Input.getX()), (int) ProgrammingSpace.blockCamera.unproject_y(Input.getY()), 1, 1)) {
                            counter++;
                        }


                    }

                    for (int i = 0; i < ProjectManager.getActProjectVar().visibleWireNodes.size(); i++) {
                        if (CheckKollision.object(ProjectManager.getActProjectVar().visibleWireNodes.get(i).getX(), ProjectManager.getActProjectVar().visibleWireNodes.get(i).getY(), WireNode.public_w, WireNode.public_h, (int) ProgrammingSpace.blockCamera.unproject_x(Input.getX()), (int) ProgrammingSpace.blockCamera.unproject_y(Input.getY()), 1, 1)) {
                            counter++;
                        }
                    }

                    if (counter == 0) {

                        EventVar.blockEventManager.killmovingwires(new BlockKillMovingWiresEvent(this));

                    }

                }


                if (left_connection == null) { //Selbst zerstören wenn ein Block gelöscht wird mit der die Wire verbunden war
                    ProjectManager.getActProjectVar().wires.remove(this);
                    ProjectManager.getActProjectVar().visiblewires.remove(this);
                    ProjectManager.getActProjectVar().movingwires = null;
                    ProjectManager.getActProjectVar().showleftdocker = false;


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


                if (Input.isKeyPressed(KeyEvent.VK_N) && canplaceanewwirenode && ProjectManager.getActProjectVar().movingwires != null) {


                    WireNode tempwirenode = ProjectManager.getActProjectVar().projectType.getWireNodeGenerator().generate(ProjectManager.getActProjectVar().movingwires, (int) ProgrammingSpace.blockCamera.unproject_x(Input.getX()), (int) ProgrammingSpace.blockCamera.unproject_y(Input.getY()), WireNode.public_w, WireNode.public_h);

                    ProjectManager.getActProjectVar().wireNodes.add(tempwirenode);
                    ProjectManager.getActProjectVar().visibleWireNodes.add(tempwirenode);

                    this.right_connection = tempwirenode;

                    try {
                        ProjectManager.getActProjectVar().movingwires.movebymouse = false;
                        ProjectManager.getActProjectVar().movingwires.space_between_blocks = true;
                        ProjectManager.getActProjectVar().movingwires.right_connection = tempwirenode;
                    } catch (NullPointerException e) {
                        DisplayErrors.customErrorstring = "Keine WireNodes in der Luft plazieren!";
                        DisplayErrors.error = e;
                    }

                    ProjectManager.getActProjectVar().movingwires = null;


                    tempwirenode.setWire_right(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(tempwirenode));
                    //tempwirenode.getWire_right().space_between_blocks = false;
                    tempwirenode.getWire_right().movebymouse = true;
                    tempwirenode.getWire_right().space_between_blocks = true;
                    ProjectManager.getActProjectVar().movingwires = tempwirenode.getWire_right();
                    ProjectManager.getActProjectVar().visiblewires.add(tempwirenode.getWire_right());
                    //ProjectManager.getactProjectVar().wires.add(tempwirenode.getWire_right());


                }


                boolean temp = false;
                //TODO L if (!ProgrammingSpace.batch.isDrawing()) {
                //TODO L  ProgrammingSpace.batch.begin();
                //TODO L   temp = true;
                //TODO L }

                //TODO L sprite.setTexture(AssetLoader.wire);


                float a = left_connection.getX_exit() - ProgrammingSpace.blockCamera.unproject_x(Input.getX());
                float b = left_connection.getY_exit() - ProgrammingSpace.blockCamera.unproject_y(Input.getY());

                //TODO L sprite.setPosition(left_connection.getX_exit(), left_connection.getY_exit());


                double weite = Math.sqrt(a * a + b * b);

                //sprite.setOrigin(left_connection.getX_exit(),left_connection.getY_exit());

                // sprite.setOrigin(left_connection.getX_exit(),left_connection.getY_exit());


                if (ProgrammingSpace.blockCamera.unproject_x(Input.getX()) - left_connection.getX_exit() >= 0) {
                    //TODO L sprite.setRotation((float) ((float) Math.atan((ProgrammingSpace.viewport.unproject(tempvector.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - left_connection.getY_exit()) / (ProgrammingSpace.viewport.unproject(tempvector1.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - left_connection.getX_exit())) * 180 / Math.PI));
                } else {
                    //TODO L sprite.setRotation((float) ((float) Math.atan((ProgrammingSpace.viewport.unproject(tempvector.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y - left_connection.getY_exit()) / (ProgrammingSpace.viewport.unproject(tempvector1.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x - left_connection.getX_exit())) * 180 / Math.PI) + 180);

                }

                //   ProgrammingSpace.batch.draw(AssetLoader.switch_background_white,873,575,5,5);


                //TODO L sprite.setSize((float) weite, dicke);
                //TODO L sprite.setOrigin(0, 0);
                //   sprite.setSize(50,50);
                //  sprite.setRotation(20);
                // sprite.setRotation();


                //TODO L sprite.draw(ProgrammingSpace.batch);


                //ProgrammingSpace.batch.draw(sprite,left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y,(float) weite,10);
                //ProgrammingSpace.batch.draw(sprite,left_connection.getwireconnector_right().x,left_connection.getwireconnector_right().y,);


                //TODO L  if (temp) {
                    //TODO L  ProgrammingSpace.batch.end();
                //TODO L  }


            } else {


                if (left_connection == null || right_connection == null) { //Selbst zerstören wenn ein Block gelöscht wird mit der die Wire verbunden war
                    ProjectManager.getActProjectVar().wires.remove(this);
                    ProjectManager.getActProjectVar().visiblewires.remove(this);


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
                    //TODO L   if (!ProgrammingSpace.batch.isDrawing()) {
                    //TODO L    ProgrammingSpace.batch.begin();
                    //TODO L      temp = true;
                    //TODO L  }


                    //TODO L   sprite.setTexture(AssetLoader.wire);


                    float a = left_connection.getX_exit() - right_connection.getX_entrance();
                    float b = left_connection.getY_exit() - right_connection.getY_entrance();

                    //TODO L  sprite.setPosition(left_connection.getX_exit(), left_connection.getY_exit());


                    double weite = Math.sqrt(a * a + b * b);

                    if (right_connection.getX_entrance() - left_connection.getX_exit() >= 0) {
                        //TODO L     sprite.setRotation((float) ((float) Math.atan((right_connection.getY_entrance() - left_connection.getY_exit()) / (right_connection.getX_entrance() - left_connection.getX_exit())) * 180 / Math.PI));

                    } else {
                        //TODO L    sprite.setRotation((float) ((float) Math.atan((right_connection.getY_entrance() - left_connection.getY_exit()) / (right_connection.getX_entrance() - left_connection.getX_exit())) * 180 / Math.PI) + 180);

                    }


                    //TODO L  sprite.setSize((float) weite, dicke);
                    //TODO L  sprite.setOrigin(0, 0);


                    //TODO L sprite.draw(ProgrammingSpace.batch);
                    //TODO L if (CheckKollision.objectwithrotation(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), sprite.getRotation(), ProgrammingSpace.viewport.unproject(tempvector1.set(Gdx.input.getX(), Gdx.input.getY(), 0)).x, ProgrammingSpace.viewport.unproject(tempvector.set(Gdx.input.getX(), Gdx.input.getY(), 0)).y, 1, 1, 0) && ProjectManager.getActProjectVar().mousehoveredwire != this) {
                    //TODO L       ProjectManager.getActProjectVar().mousehoveredwire = this;

                    //TODO L    }




                    //TODO L  if (temp) {
                    //TODO L       ProgrammingSpace.batch.end();
                    //TODO L   }
                } catch (Exception e) {
                    //Falls der Block der Verbunden ist gerade gelöscht wird
                    try {
                        //TODO L     ProgrammingSpace.batch.end(); //Damit der Render Prozess weiterläuft
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
