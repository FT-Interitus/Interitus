/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Block.Generators.BlockToSaveGenerator;
import de.ft.interitus.Program;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.UIElements.BlockDropDownMenue.BlockDropDownMenue;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.Viewport;
import de.ft.interitus.UI.WindowAPI;
import de.ft.interitus.UI.popup.PopupMenue;
import de.ft.interitus.WindowManager;
import de.ft.interitus.events.EventManager;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.block.BlockCreateEvent;
import de.ft.interitus.events.block.BlockDeleteEvent;
import de.ft.interitus.events.block.BlockNeighborSetEvent;
import de.ft.interitus.events.rightclick.RightClickEventListener;
import de.ft.interitus.events.rightclick.RightClickOpenRequestEvent;
import de.ft.interitus.events.rightclick.RightClickPerformActionEvent;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;
import de.ft.interitus.utils.Unproject;

import java.util.ConcurrentModificationException;
import java.util.Objects;



public abstract class Block {
    private final Vector2 movementDiff=new Vector2();
    private final Vector2 wireConnector_right = new Vector2(0, 0);
    private final Vector2 pos = new Vector2(0, 0); //Block pos
    private final Vector2 wireConnector_left = new Vector2(0, 0);
    private final RightClickEventListener rightClickEventListener;
    private final BlockToSaveGenerator blocktoSaveGenerator;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private final BlockDropDownMenue BlockModeSelection = new BlockDropDownMenue(0, 0, 0, 0, this);
    private final int h;
    public Block left = null;
    public Block right = null;

    private Wire wire_left;
    private Wire wire_right;
    private PlatformSpecificBlock blockType;
    private ArrayList<Block> extendedBlocks = null;


    public Block(int x, int y, int w, int h, PlatformSpecificBlock platformSpecificBlock, BlockToSaveGenerator blocktoSaveGenerator, boolean isSubBlock) {
        this.blockType = platformSpecificBlock;
        //  EventVar.blockEventManager.createBlock(new BlockCreateEvent(this, this));
        EventManager.fireEvent(this, new BlockCreateEvent(this));
        this.pos.x = x;
        this.pos.y = y;

        this.h = h;


        wireConnector_right.set(x + w, y + h / 3f);


        this.blocktoSaveGenerator = blocktoSaveGenerator;


        if (!isSubBlock)
            if (this.isVisible())
                Objects.requireNonNull(ProjectManager.getActProjectVar()).visible_blocks.add(this);


        rightClickEventListener = new RightClickEventListener() {

            @Override
            public PopupMenue openRequest(RightClickOpenRequestEvent e, float Pos_X, float Pos_Y) {


                CheckCollision.object(getX(), getY(), getW(), getW(), Unproject.unproject(Pos_X, Pos_Y).x, Unproject.unproject(Pos_X, Pos_Y).y, 1, 1);
                return null;
            }

            @Override
            public void performAction(RightClickPerformActionEvent e, PopupMenue popupMenue, int ButtonIndex) {


            }
        };
        EventVar.rightClickEventManager.addListener(rightClickEventListener);


    }


    /***
     *
     * @return if the Block is visible
     */


    public boolean isVisible() {


        return Viewport.extendedfrustum.boundsInFrustum(this.getX(), this.getY(), 0, this.getW(), this.getH(), 0); //Is Block Visible


    }


    /***
     *
     * @return the duplication is visible if a block offers the neighbours place
     */

    public int getRightDuplicatePosition() {
        return (int) (this.pos.x + blockType.getWidth());
    }


    /***
     *
     * @return is the Block moved by the user
     * @see de.ft.interitus.events.block.BlockEventListener
     */
    public boolean isMoving() {
        assert ProjectManager.getActProjectVar() != null;
        return ProjectManager.getActProjectVar().moving_block == this;
    }


    /***
     *
     * @return is the Block marked with the mouse
     */

    public boolean isMarked() {
        assert ProjectManager.getActProjectVar() != null;
        assert ProjectManager.getActProjectVar() != null;
        return ProjectManager.getActProjectVar().marked_blocks.contains(this);
    }


    public Block getLeft() {
        return left;
    }

    /***
     *
     * @param left set The block neighbour left
     *              And it set the right neighbour of the block to this
     *
     */

    public void setLeft(Block left) {


        if (left == null) {
            if (this.left != null) {
                if (this.left.getRight() != null) {
                    this.left.right = null;
                }
            }
        }

        if (this.left != left) {
            this.left = left;
        }
        if (left != null) {
            if (left.getRight() != this) {
                left.setRight(this);
            }
        }


        EventVar.blockEventManager.setNeighbor(new BlockNeighborSetEvent(this), this, left, false);

    }

    /***
     *
     * See Block.setLeft
     */

    public Block getRight() {
        return right;
    }

    public void setRight(Block right) {

        if (right == null) {
            if (this.right != null) {
                if (this.right.getLeft() != null) {
                    this.right.left = null;
                }
            }
        }

        if (this.right != right) {
            this.right = right;
        }
        if (right != null) {
            if (right.getLeft() != this) {
                right.setLeft(this);
            }
        }

        EventVar.blockEventManager.setNeighbor(new BlockNeighborSetEvent(this), this, right, true);

    }

    /**
     * @return Set and get the Block Positions
     */
    public int getX() {
        return (int) this.pos.x;
    }

    public void setX(int x) {
        this.pos.x = x;
    }

    public int getY() {
        return (int) this.pos.y;
    }

    public void setY(int y) {
        this.pos.y = y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return blockType.getWidth();
    }

    public void setPosition(int x, int y) {
        this.pos.x = x;
        this.pos.y = y;
    }


    /**
     * Getter and Setter for the Index of the Block
     * Must be the same as in the ProjectManager.getActProjectVar().blocks array
     *
     * @return the index from this Block
     */
    public int getIndex() {
        assert ProjectManager.getActProjectVar()!=null;
        return ProjectManager.getActProjectVar().blocks.indexOf(this);
    }


    /**
     * Deletes the Block a change the Index of the other Blocks in the Array depending on complete
     *
     * @param complete means that the whole Program is clearing for example if you open a new Project
     */

    public void delete(boolean complete) {


        if (blockType.getBlockParameter() != null) {
            for (Parameter parameter : blockType.getBlockParameter()) {
                if (parameter.getDataWires() == null) {
                    continue;
                }

                while (parameter.getDataWires().size() > 0) {
                    parameter.getDataWires().get(0).delete();
                }

            }
        }

        assert ProjectManager.getActProjectVar() != null;
        if (ProjectManager.getActProjectVar().Blockswitherrors.contains(this.getIndex())) {
            ProjectManager.getActProjectVar().Blockswitherrors.remove((Object) this.getIndex());
        }

        EventVar.rightClickEventManager.removeListener(this.rightClickEventListener);
        EventVar.blockEventManager.deleteBlock(new BlockDeleteEvent(this, this)); //Fire Delete Event
        ProjectManager.getActProjectVar().marked_blocks.remove(this);
        ProjectManager.getActProjectVar().moving_block = null;


        //Clear all neighbours and wires

        if (left != null) {
            left.setRight(null);
        }

        if (right != null) {
                right.setLeft(null);
        }

        if(wire_left!=null) {
            wire_left.delete();
        }
        if(wire_right!=null) {
            wire_right.delete();
        }

        left = null;
        right = null;


        if (!complete) {
            ProjectManager.getActProjectVar().visible_blocks.remove(this);
            try {
                ProjectManager.getActProjectVar().blocks.remove(this);
            } catch (Exception ignored) {

            }

        }

    }

    /***
     * Updates all Block Behaviors
     */
    public void update() {

        boolean hovered = CheckCollision.checkmousewithblock(this);
        if (hovered && WindowAPI.isButtonJustPressed(0)) this.onClick();

    }



    public void drawBlock(SpriteBatch batch){
        batch.draw(AssetLoader.block_middle, this.getX() + 6, this.getY(), this.getW() - 12, this.getH());
        batch.draw(AssetLoader.block_left, this.getX(), this.getY(), 6, this.getH());
        batch.draw(AssetLoader.block_right, this.getX() + this.getW() - 6, this.getY(), 6, this.getH());


        if (blockType.getBlockCategory() != null) {
            switch (blockType.getBlockCategory()) {

                case ActionBlocks -> {

                    batch.draw(AssetLoader.green_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13);
                    batch.draw(AssetLoader.green_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    batch.draw(AssetLoader.green_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);


                }


                case Programm_Sequence -> {
                    batch.draw(AssetLoader.orange_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13);
                    batch.draw(AssetLoader.orange_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    batch.draw(AssetLoader.orange_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);

                }

                case Sensors -> {
                    batch.draw(AssetLoader.yellow_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13);
                    batch.draw(AssetLoader.yellow_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    batch.draw(AssetLoader.yellow_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);

                }

                case Data_Operation -> {
                    batch.draw(AssetLoader.red_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13);
                    batch.draw(AssetLoader.red_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    batch.draw(AssetLoader.red_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);


                }


                case Specials -> {
                    batch.draw(AssetLoader.blue_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13);
                    batch.draw(AssetLoader.blue_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    batch.draw(AssetLoader.blue_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);

                }

                case OwnBlocks -> {
                    batch.draw(AssetLoader.turquoise_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13);
                    batch.draw(AssetLoader.turquoise_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
                    batch.draw(AssetLoader.turquoise_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);


                }


            }

        } else {
            batch.draw(AssetLoader.orange_bar_middle, this.getX() + 6, this.getY() - 1 + this.getH() - 13, this.getW() - 12, 13);
            batch.draw(AssetLoader.orange_bar_left, this.getX() + 1, this.getY() - 1 + this.getH() - 13, 6, 13);
            batch.draw(AssetLoader.orange_bar_right, this.getX() + this.getW() - 7, this.getY() - 1 + this.getH() - 13, 6, 13);

        }


        if (CheckCollision.checkmousewithblock(this)) {

            batch.draw(AssetLoader.mouse_over_mitte, this.getX() + 6, this.getY(), this.getW() - 12, this.getH());
            batch.draw(AssetLoader.mouseover_left, this.getX(), this.getY(), 6, this.getH());
            batch.draw(AssetLoader.mouse_over_right, this.getX() + this.getW() - 6, this.getY(), 6, this.getH());
        }

        if (this.isMarked()) {

            batch.draw(AssetLoader.marked_mitte, this.getX() + 6, this.getY(), this.getW() - 12, this.getH());
            batch.draw(AssetLoader.marked_left, this.getX(), this.getY(), 6, this.getH());
            batch.draw(AssetLoader.marked_right, this.getX() + this.getW() - 6, this.getY(), 6, this.getH());
        }
    }

    /**
     * Draw the Block and the connectors
     *
     * @param batch to draw the Block
     */

    public void draw(SpriteBatch batch) {

        /*
         Draw Wire with the Output Block if the Input Block is invisible
         */
        assert ProjectManager.getActProjectVar()!=null;
        if (this.getBlockType().getBlockParameter() != null) {
            for (Parameter parameter : this.getBlockType().getBlockParameter()) {
                for (DataWire dataWire : parameter.getDataWires()) {

                    if (dataWire.getParam_input().getBlock() == this) continue;
                    if (dataWire == ProjectManager.getActProjectVar().movingDataWire) continue;
                    if (!ProjectManager.getActProjectVar().visible_blocks.contains(dataWire.getParam_input().getBlock()))
                        dataWire.draw();
                }
            }
        }


        if(this.getLeft()!=null&&!ProjectManager.getActProjectVar().visible_blocks.contains(this.getLeft())) {
            if(this.getWire_left()!=ProjectManager.getActProjectVar().movingWire) {
                this.getWire_left().draw();
            }
        }



        batch.begin();


        if (ProjectManager.getActProjectVar().duplicate_block_right == this && this.getBlockType().canHasRightConnector()) {
            batch.setColor(1, 1, 1, 0.5f);
            batch.draw(AssetLoader.block_middle, this.getRightDuplicatePosition(), this.getY(), ProjectManager.getActProjectVar().moving_block.getW(), this.getH());
            batch.setColor(1, 1, 1, 1);
        }

        if (ProjectManager.getActProjectVar().duplicate_block_left == this && this.getBlockType().canHasLeftConnector() && ProjectManager.getActProjectVar().marked_blocks != null) {
            batch.setColor(1, 1, 1, 0.5f);
            batch.draw(AssetLoader.block_middle, this.getX() - ProjectManager.getActProjectVar().moving_block.getW(), this.getY(), ProjectManager.getActProjectVar().moving_block.getW(), this.getH());
            batch.setColor(1, 1, 1, 1);
        }


        try {
            if (ProjectManager.getActProjectVar().Blockswitherrors.contains(this.getIndex())) {
                batch.setColor(1, 0.8f, 0.8f, 1);
            } else {
                batch.setColor(1, 1, 1, 1);
            }


            float alpha = 1;

            if (!Settings.disableblockgrayout) {
                if (!ProjectManager.getActProjectVar().projectType.getProjectFunktions().isblockconnected(this)) {
                    batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 0.4f);
                    alpha = 0.4f;
                }
            }

            drawBlock(batch);



            if (this.getWire_right()==null && ProjectManager.getActProjectVar().showLeftDocker && this.getLeft() == null && this.getBlockType().canHasLeftConnector()) {
             batch.draw(AssetLoader.connector_offerd, getWireConnectorLeft().x, getWireConnectorLeft().y, 20, 20);
             }

            if (this.getRight() == null && this.getBlockType().canHasRightConnector()) {
                batch.draw(AssetLoader.connector, getWireConnectorRight().x, getWireConnectorRight().y, 20, 20);
            }


            /*
             * Show Parameters
             */

            if (this.getBlockType().getBlockParameter() != null && this.getBlockType().getBlockParameter().size() > 0) {
                float actualX = this.getX();

                actualX += 5;
                batch.draw(this.getBlockType().getDescriptionImage(), actualX, this.getY() + this.getH() - 30 - 17, 30, 30);

                actualX += 35;
                for (int i = 0; i < this.getBlockType().getBlockParameter().size(); i++) {
                    batch.draw(this.getBlockType().getBlockParameter().get(i).getParameterTexture(), actualX + 5, this.getY() + 33, 20, 20);

                    //DataWire Compatibility Color Check
                    if (ProjectManager.getActProjectVar().movingDataWire != null) {
                        if (CheckCollision.checkpointwithobject(this.getBlockType().getBlockParameter().get(i).getX(), this.getBlockType().getBlockParameter().get(i).getY(), UIVar.parameter_width, UIVar.parameter_height, Unproject.unproject()) && !this.getBlockType().getBlockParameter().get(i).getParameterType().isOutput() && this.getBlockType().getBlockParameter().get(i).getDataWires().size() == 0) {
                            if (!this.getBlockType().getBlockParameter().get(i).getParameterType().getVariableType().iscompatible(ProjectManager.getActProjectVar().movingDataWire.getParam_input().getParameterType().getVariableType()) || ProjectManager.getActProjectVar().movingDataWire.getParam_input().getBlock() == this) {
                                batch.setColor(1, 0.6f, 0.6f, alpha);
                            } else {
                                batch.setColor(0.6f, 1, 0.6f, alpha);

                            }
                        }
                    }
                    if (this.getBlockType().getBlockParameter().get(i).getParameterType().isOutput()) {
                        batch.draw(this.getBlockType().getBlockParameter().get(i).getParameterType().getVariableType().getTextureconnector(), (int) actualX, this.getY() - 7, UIVar.parameter_width, UIVar.parameter_height, 0, 0, AssetLoader.Plug_IntParameter.getWidth(), AssetLoader.Plug_IntParameter.getHeight(), false, true);
                        this.getBlockType().getBlockParameter().get(i).setX((int) actualX);
                        this.getBlockType().getBlockParameter().get(i).setY(this.getY() - 7);

                    } else {
                        batch.draw(this.getBlockType().getBlockParameter().get(i).getParameterType().getVariableType().getTextureconnector(), actualX, this.getY(), UIVar.parameter_width, UIVar.parameter_height);
                        this.getBlockType().getBlockParameter().get(i).setX((int) actualX);
                        this.getBlockType().getBlockParameter().get(i).setY(this.getY());
                        WindowManager.ParameterFont.getData().setScale(1f,1f);
                        glyphLayout.setText(WindowManager.ParameterFont, "" + this.getBlockType().getBlockParameter().get(i).getBlockParameterContent());
                        if (this.getBlockType().getBlockParameter().get(i).getDataWires().size() < 1) {
                            WindowManager.ParameterFont.draw(batch, glyphLayout, actualX + 15 - glyphLayout.width / 2, getY() + glyphLayout.height/2 +10);
                        }
                    }
                    actualX += UIVar.parameter_width;

                    batch.setColor(1, 1f, 1f, alpha);


                }
            } else if (this.getBlockType().getDescriptionImage() != null) {

                batch.draw(this.getBlockType().getDescriptionImage(), this.getX() + ((this.getW() - 50f) / 2f), this.getY() + this.getH() - 17 - 50, 50, 50);

            }


            batch.end();


            BlockModeSelection.setBounds(this.getX() + 11, this.getY() + 2, 20, 20);
            if (this.getBlockType().getBlockModes().size() > 1) {
                BlockModeSelection.draw(this);
            }


            if(this.getWire_right()!=null) {
                if(ProjectManager.getActProjectVar().movingWire!=this.getWire_right()) {
                        this.getWire_right().draw();
                }
            }

            try {
                if (this.getBlockType() != null && this.getBlockType().getBlockParameter() != null) {
                    for (Parameter parameter : this.getBlockType().getBlockParameter()) {
                        if (parameter.getDataWires() != null) {
                            for (DataWire dataWire : parameter.getDataWires()) {
                                if (dataWire == ProjectManager.getActProjectVar().movingDataWire) continue;
                                if (dataWire.getParam_input() == parameter) {
                                    dataWire.draw();
                                }
                            }
                        }
                    }
                }
            } catch (ConcurrentModificationException ignored) {
            }


        } catch (Exception e) {

            e.printStackTrace();

            if (batch.isDrawing()) {
                batch.end();
            }
        }

    }

    /**
     * @return the position of the two connectors
     */

    public Vector2 getWireConnectorRight() {
        wireConnector_right.set(this.getX() + blockType.getWidth() - 7, this.getY() + h / 3f + 12);
        return wireConnector_right;
    }

    /**
     * @return the position of the two connectors
     *
     */
    public Vector2 getWireConnectorLeft() {
        wireConnector_left.set(this.getX() + 2, this.getY() + h / 3f + 9);
        return wireConnector_left;
    }


    /***
     * @return the Block Type from this Block
     */
    public PlatformSpecificBlock getBlockType() {
        return blockType;
    }

    public void setBlockType(PlatformSpecificBlock blockType) {
        this.blockType = blockType;
    }

    public BlockToSaveGenerator getBlockToSaveGenerator() {
        return blocktoSaveGenerator;
    }

    /**
     * When the Size of the Block will be changed
     */
    public void changeSize(int widthDiff) {

        ArrayList<Block> blocks = new ArrayList<>();

        Block nextBlock = this.getRight();


        while (nextBlock != null) {

            if (blocks.contains(nextBlock)) {
                blocks.clear();
                break;
            }
            blocks.add(nextBlock);


            nextBlock.setX(nextBlock.getX() + widthDiff);
            nextBlock = nextBlock.getRight();

        }

    }


    public ArrayList<Block> getExtendedBlocks() {
        return extendedBlocks;
    }

    public void setExtendedBlocks(ArrayList<Block> extendedBlocks) {
        this.extendedBlocks = extendedBlocks;
    }

    public Wire getWire_left() {
        return wire_left;
    }

    public void setWire_left(Wire wire_left) {
        this.wire_left = wire_left;
    }

    public Wire getWire_right() {
        return wire_right;
    }

    public void setWire_right(Wire wire_right) {
        this.wire_right = wire_right;
    }


    public Vector2 getPos() {
        return pos;
    }


    public void onClick() {


        Program.logger.config("Clicked: " + blockType.getName());


    }

    public Vector2 getMovementDiff() {
        return movementDiff;
    }


}
