/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Saving;


import de.ft.interitus.Block.Block;
import de.ft.interitus.utils.ArrayList;

import java.io.Serial;
import java.io.Serializable;



public class SaveBlockV1 implements Serializable {


    /***
     * @deprecated because we are using gson
     */
    @Serial
    private static final long serialVersionUID = 2637840000002L;


    private final ArrayList<String> parameters;
    private final int x;
    private final int y;
    private final int index;
    private final int index_left;
    private final int index_right;
    private final boolean isspacebetweenrightblock;
    private final int platformspecificblockid;
    private final ArrayList<ArrayList<Integer>> datawires;
    private final ArrayList<ArrayList<Integer>> datawiresindex;
    private final ArrayList<ArrayList<ArrayList<Integer>>> datawiresmoveing;
    private final int blockmodus;
    private final String addon;
    private final String blocksettings;
    private final ArrayList<SaveBlockV1> includedBlocks;


    /***
     * Do not change Constructor because GSON can't create the object
     * @param parameters
     * @param datawires
     * @param datawireindex
     * @param datawiresmoveing
     */
    public SaveBlockV1(Block block, Block left, Block right, ArrayList<String> parameters, ArrayList<ArrayList<Integer>> datawires, ArrayList<ArrayList<Integer>> datawireindex, ArrayList<ArrayList<ArrayList<Integer>>> datawiresmoveing,ArrayList<SaveBlockV1> includedBlocks) {
        this.x = block.getX();
        this.y = block.getY();
        this.index = block.getIndex();

        if(left!=null)
            this.index_left = left.getIndex();
        else
            this.index_left = -1;

        if(right!=null) {
            this.index_right = right.getIndex();
        }else{
            this.index_right = -1;
        }

        this.isspacebetweenrightblock = block.getWire_right() != null && block.getWire_right().isVisible();
        this.platformspecificblockid = block.getBlockType().getID();
        this.parameters = parameters;
        this.datawires = datawires;
        this.datawiresindex = datawireindex;
        this.datawiresmoveing = datawiresmoveing;
        this.blockmodus = block.getBlockType().getActBlockModeIndex();
        this.addon = block.getBlockType().getAddonName();
        this.blocksettings = block.getBlockType().blockModis.get(block.getBlockType().getActBlockModeIndex()).getblocksettings()!=null?block.getBlockType().blockModis.get(block.getBlockType().actBlockModiIndex).getblocksettings().getSettings():null;
        this.includedBlocks =includedBlocks;

    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getIndex() {
        return index;
    }

    public int getIndex_left() {
        return index_left;
    }

    public int getIndex_right() {
        return index_right;
    }

    public boolean isIsspacebetweenrightblock() {
        return isspacebetweenrightblock;
    }



    public int getPlatformspecificblockid() {
        return platformspecificblockid;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public ArrayList<ArrayList<Integer>> getDatawires() {
        return datawires;
    }

    public ArrayList<ArrayList<Integer>> getDatawiresindex() {
        return datawiresindex;
    }

    public int getBlockmodus() {
        return blockmodus;
    }

    public String getAddon() {
        return addon;
    }

    public String getBlocksettings() {
        return blocksettings;
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getDatawiresmoveing() {
        return datawiresmoveing;
    }

    public ArrayList<SaveBlockV1> getIncludedBlocks() {
        return includedBlocks;
    }
}
