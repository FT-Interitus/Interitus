/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;


import de.ft.interitus.utils.ArrayList;

import java.io.Serializable;

public abstract class SaveBlock implements Serializable {


    private static final long serialVersionUID = 2637840000001L; //Achtung änderungen können bewirken das ältere Programme nicht geladen werden können
    private final ArrayList<ArrayList<Integer>> nodes;
    private final ArrayList<String> parameters;
    private int x = 0;
    private int y = 0;
    private int index = -1;
    private int index_left = -1;
    private int index_right = -1;
    private boolean isspacebetweenrightblock = false;
    private int platformspecificblockid = 0;
    private final ArrayList<ArrayList<Integer>> datawires;
    private final ArrayList<ArrayList<Integer>> datawiresindex;
    private final int blockmodus;
    private final String addon;
    private final String blocksettings;


    public SaveBlock(int x, int y, int index, int index_left, int index_right, boolean isspacebetweenrightblock, ArrayList<ArrayList<Integer>> nodes, int platformspecificblockid, ArrayList<String> parameters, ArrayList<ArrayList<Integer>> datawires, ArrayList<ArrayList<Integer>> datawireindex, int BlockModus, String addon, String blocksettings) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.index_left = index_left;
        this.index_right = index_right;
        this.isspacebetweenrightblock = isspacebetweenrightblock;
        this.nodes = nodes;
        this.platformspecificblockid = platformspecificblockid;
        this.parameters = parameters;
        this.datawires = datawires;
        this.datawiresindex = datawireindex;
        this.blockmodus = BlockModus;
        this.addon = addon;
        this.blocksettings = blocksettings;


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

    public ArrayList<ArrayList<Integer>> getNodes() {
        return nodes;
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
}
