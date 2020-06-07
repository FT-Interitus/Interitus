package de.ft.interitus.Block;


import java.io.Serializable;

public abstract class SaveBlock implements Serializable {
    int x=0;
    int y=0;
    int index=-1;
    int index_links=-1;
    int index_rechts=-1;

    public SaveBlock(int x, int y , int index, int index_links,int index_rechts) {
this.x = x;
this.y = y;
this.index = index;
this.index_links = index_links;
this.index_rechts = index_rechts;
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

}
