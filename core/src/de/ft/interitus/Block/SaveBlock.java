package de.ft.interitus.Block;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class SaveBlock implements Serializable {


    private static final long serialVersionUID = 2637840000001L; //Achtung änderungen können bewirken das ältere Programme nicht geladen werden können
    ArrayList<ArrayList<Integer>> nodes;
    int x = 0;
    int y = 0;
    int index = -1;
    int index_links = -1;
    int index_rechts = -1;
    boolean isspacebetweenrightblock = false;
    int platformspecificblockid = 0;


    public SaveBlock(int x, int y, int index, int index_links, int index_rechts, boolean isspacebetweenrightblock, ArrayList<ArrayList<Integer>> nodes, int platformspecificblockid) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.index_links = index_links;
        this.index_rechts = index_rechts;
        this.isspacebetweenrightblock = isspacebetweenrightblock;
        this.nodes = nodes;
        this.platformspecificblockid = platformspecificblockid;


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
}
