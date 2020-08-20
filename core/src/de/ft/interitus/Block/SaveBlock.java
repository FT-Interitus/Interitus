/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;


import de.ft.interitus.utils.ArrayList;

import java.io.Serializable;
import java.lang.reflect.Array;

public abstract class SaveBlock implements Serializable {


    private static final long serialVersionUID = 2637840000001L; //Achtung änderungen können bewirken das ältere Programme nicht geladen werden können
    private final ArrayList<ArrayList<Integer>> nodes;
    private final ArrayList<String> parameters;
    private int x = 0;
    private int y = 0;
    private int index = -1;
    private int index_links = -1;
    private int index_rechts = -1;
    private boolean isspacebetweenrightblock = false;
    private int platformspecificblockid = 0;
    private ArrayList<ArrayList<Integer>> datawires;
    private ArrayList<ArrayList<Integer>> datawiresindex;
    private int blockmodus;



    public SaveBlock(int x, int y, int index, int index_links, int index_rechts, boolean isspacebetweenrightblock, ArrayList<ArrayList<Integer>> nodes, int platformspecificblockid, ArrayList<String> parameters, ArrayList<ArrayList<Integer>> datawires,ArrayList<ArrayList<Integer>> datawireindex,int BlockModus) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.index_links = index_links;
        this.index_rechts = index_rechts;
        this.isspacebetweenrightblock = isspacebetweenrightblock;
        this.nodes = nodes;
        this.platformspecificblockid = platformspecificblockid;
        this.parameters = parameters;
        this.datawires = datawires;
        this.datawiresindex = datawireindex;
        this.blockmodus = BlockModus;


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
}
