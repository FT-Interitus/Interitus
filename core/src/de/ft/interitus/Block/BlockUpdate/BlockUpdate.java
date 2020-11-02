/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.UI.ExtendedBlocksApplicationListener;
import de.ft.interitus.UI.window.CreateWindow;
import de.ft.interitus.UI.window.Window;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.programmablauf.For.For;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;


public abstract class BlockUpdate  {
    public Block block;
    private boolean openwindow = false;


    private void block_moveingengine() {


        if (block.isMarked()) {

            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                block.setExtendedBlocks(new ArrayList<>());
                block.getExtendedBlocks().add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(0, 10, 10, 10, 10, new For(ProjectManager.getActProjectVar().projectType, null),  ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator(), true));
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




}
