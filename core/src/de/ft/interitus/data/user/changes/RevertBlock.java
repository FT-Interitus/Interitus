package de.ft.interitus.data.user.changes;

/**
 * @deprecated
 */
public class RevertBlock { //Block Duplicat das erstellt wird wenn ein Block bewegt wird
    private int x;
    private int y;
    private int w;
    private int h;
    private int index;
    private boolean created;
    private int left_index;
    private int right_index;
    private boolean deleted;

    /**
     * @param x
     * @param y
     * @param w
     * @param h
     * @param index
     * @param created
     * @param deleted
     * @param left_index
     * @param right_index
     */
    public RevertBlock(int x, int y, int w, int h, int index, boolean created, boolean deleted, int left_index, int right_index) {//TODO hier Blockart und Parameter übergeben
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.created = created;
        this.index = index;
        this.left_index = left_index;
        this.right_index = right_index;
        this.deleted = deleted;


    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
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

    public int getLeft_index() {
        return left_index;
    }

    public void setLeft_index(int left_index) {
        this.left_index = left_index;
    }

    public int getRight_index() {
        return right_index;
    }

    public void setRight_index(int right_index) {
        this.right_index = right_index;
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
