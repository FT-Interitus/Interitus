package de.ft.interitus.data.user.clipboard;

public class SavedBlock {
    private int x;
    private int y;
    private int w;
    private int h;
    private int index;


    public SavedBlock(int x, int y, int w, int h, int index) { //TODO hier Blockart und Parameter übergeben
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.index = index;


    }


    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
