/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Program;
import de.ft.interitus.UI.ExtendedBlocksApplicationListener;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.window.CreateWindow;
import de.ft.interitus.UI.window.Window;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.For.For;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;
import de.ft.interitus.utils.Unproject;

import java.util.Timer;
import java.util.TimerTask;


public abstract class BlockUpdate extends Thread {
    public Block block; //Der zugehörige Block den die Klasse updated
    public boolean isrunning = true; //Läuft der Thread gerade?
    public Timer time; //das  ist der Timer in dem alle Update Vorgänge laufen
    public boolean isconnectorclicked = false;//Ist der connector des zuständigen Blocks ausgelöst
    public boolean geschoben = false;
    public boolean toggle; // Ist der Block von der mouse gehovert?
    Vector3 temp3;//Temp vectoren für berechnungs zwischen schritte
    Vector3 temp4;//Temp vectoren für berechnungs zwischen schritte

    private boolean IsMousealreadypressed = false;


    private boolean openwindow = false;

    public BlockUpdate(Block block) {
        this.block = block; //Der Block wird zugewiesen

        temp3 = new Vector3(0, 0, 0);//Temp Vectoren init
        temp4 = new Vector3(0, 0, 0);//Temp Vectoren init
    }


    @Override
    public void run() {


        time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


                if (UIVar.isdialogeopend || ProjectManager.getActProjectVar() == null) {
                    return;
                }

                try {

                    //TODO delete Block while Hovering Block Bar
/*
                    if ( block.isMoving()&&CheckMouse.isMouseover(UIVar.BlockBarX, UIVar.BlockBarY, UIVar.BlockBarW, UIVar.BlockBarH, false) && block.getBlocktype().canbedeleted()) {


                        if (willbedelete) {
                            Gdx.graphics.setCursor(Gdx.graphics.newCursor(AssetLoader.backcursor, 0, 0));
                            ProjectManager.getActProjectVar().removeblock = true;
                        } else {
                            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                            ProjectManager.getActProjectVar().removeblock = false;
                        }
                        ProjectManager.getActProjectVar().removeblock = false;
                        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
                        block.delete(false);
                        this.cancel();
                    }

 */

                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        DisplayErrors.customErrorstring = "Fehler in einem " + block.getBlocktype().getName() + " Block mit der ID " + block.getIndex();
                    } catch (Exception v) {
                        DisplayErrors.customErrorstring = "Fehler in einem Block";
                    }
                    DisplayErrors.error = e;
                }


            }
        }, 0, 20);

    }

    private void datawire_managment() {
        /////////////////////////////Datawire erzeigen
        if (block.getBlocktype() != null && block.getBlocktype().getBlockParameter() != null) {
            for (int i = 0; i < block.getBlocktype().getBlockParameter().size(); i++) {
                if (block.getBlocktype().getBlockParameter().get(i).getParameterType().isOutput()) {


                    if (CheckCollision.checkpointwithobject(block.getBlocktype().getBlockParameter().get(i).getX(), block.getBlocktype().getBlockParameter().get(i).getY(), UIVar.parameter_width, UIVar.parameter_height, Unproject.unproject()) && Gdx.input.isButtonJustPressed(0) && ProjectManager.getActProjectVar().moveingdatawire == null) {
                        block.getBlocktype().getBlockParameter().get(i).getDataWires().add(new DataWire(block.getBlocktype().getBlockParameter().get(i)));
                        ProjectManager.getActProjectVar().moving_block = null; //TODO fix Block
                        ProjectManager.getActProjectVar().moveingdatawire = block.getBlocktype().getBlockParameter().get(i).getDataWires().getLastObject();

                    }


                }
            }
        }


    }







    private void block_moveingengine() {


        if (block.isMarked()) {

            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                block.setExtendedBlocks(new ArrayList<>());
                block.getExtendedBlocks().add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0, 10, 10, 10, 10, new For(ProjectManager.getActProjectVar().projectType, null), ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator(), true));
                Program.logger.config("Created Subblock");
            }


            if (Gdx.input.isKeyPressed(Input.Keys.D) && !openwindow) {
                openwindow = true;
                if (block.getExtendedBlocks() != null) {

                    Window window = CreateWindow.addWindow(block.getBlocktype().getName(), new ExtendedBlocksApplicationListener());
                    window.create();

                    Program.logger.config("Open Window");


                }

            }

        }


        if (openwindow && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            openwindow = false;
        }


    }


    public boolean isIsconnectorclicked() {
        return isconnectorclicked;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
