/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Saving;


import de.ft.interitus.utils.ArrayList;
import org.lwjgl.system.CallbackI;

import java.io.Serializable;



public class SaveBlockV1 implements Serializable {


    /***
     * @deprecated because we are using gson
     */
    private static final long serialVersionUID = 2637840000002L;


    private final ArrayList<String> parameters;
    private final int x;
    private final int y;
    private final int index;
    private final int index_links;
    private final int index_rechts;
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
     * @param x
     * @param y
     * @param index
     * @param index_links
     * @param index_rechts
     * @param isspacebetweenrightblock
     * @param platformspecificblockid
     * @param parameters
     * @param datawires
     * @param datawireindex
     * @param BlockModus
     * @param addon
     * @param blocksettings
     * @param datawiresmoveing
     */
    public SaveBlockV1(int x, int y, int index, int index_links, int index_rechts, boolean isspacebetweenrightblock, int platformspecificblockid, ArrayList<String> parameters, ArrayList<ArrayList<Integer>> datawires, ArrayList<ArrayList<Integer>> datawireindex, int BlockModus, String addon, String blocksettings, ArrayList<ArrayList<ArrayList<Integer>>> datawiresmoveing) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.index_links = index_links;
        this.index_rechts = index_rechts;
        this.isspacebetweenrightblock = isspacebetweenrightblock;
        this.platformspecificblockid = platformspecificblockid;
        this.parameters = parameters;
        this.datawires = datawires;
        this.datawiresindex = datawireindex;
        this.datawiresmoveing = datawiresmoveing;
        this.blockmodus = BlockModus;
        this.addon = addon;
        this.blocksettings = blocksettings;
        this.includedBlocks =null;


    }

    /**
     * Do not change Constructor because GSON can't create the object with extendable Blocks
     * @param x
     * @param y
     * @param index
     * @param index_links
     * @param index_rechts
     * @param isspacebetweenrightblock
     * @param platformspecificblockid
     * @param parameters
     * @param datawires
     * @param datawireindex
     * @param BlockModus
     * @param addon
     * @param blocksettings
     * @param datawiresmoveing
     * @param includedBlocks
     */
    public SaveBlockV1(int x, int y, int index, int index_links, int index_rechts, boolean isspacebetweenrightblock, int platformspecificblockid, ArrayList<String> parameters, ArrayList<ArrayList<Integer>> datawires, ArrayList<ArrayList<Integer>> datawireindex, int BlockModus, String addon, String blocksettings, ArrayList<ArrayList<ArrayList<Integer>>> datawiresmoveing, ArrayList<SaveBlockV1> includedBlocks) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.index_links = index_links;
        this.index_rechts = index_rechts;
        this.isspacebetweenrightblock = isspacebetweenrightblock;
        this.platformspecificblockid = platformspecificblockid;
        this.parameters = parameters;
        this.datawires = datawires;
        this.datawiresindex = datawireindex;
        this.datawiresmoveing = datawiresmoveing;
        this.blockmodus = BlockModus;
        this.addon = addon;
        this.blocksettings = blocksettings;
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

    public int getIndex_links() {
        return index_links;
    }

    public int getIndex_rechts() {
        return index_rechts;
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
