package de.ft.robocontrol.data.user.changes;

public class RevertBlock {
    private int x;
    private int y;
    private int w;
    private int h;
    private int index;
    private boolean created;
    private int left_index;
    private int right_index;
    private boolean deleted;
    public  RevertBlock(int x, int y, int w, int h, int index, boolean created, boolean deleted, int left_index, int right_index) {
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

    public int getH() {
        return h;
    }

    public int getIndex() {
        return index;
    }

    public int getLeft_index() {
        return left_index;
    }

    public int getRight_index() {
        return right_index;
    }

    public int getW() {
        return w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLeft_index(int left_index) {
        this.left_index = left_index;
    }

    public void setRight_index(int right_index) {
        this.right_index = right_index;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
