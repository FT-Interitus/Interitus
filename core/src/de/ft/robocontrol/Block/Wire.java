package de.ft.robocontrol.Block;

public class Wire {
    private Block left_connection;
    private Block right_connection;

    private boolean space_between_blocks = false;


    public Wire(Block left_connection,Block right_connection) {
        this.left_connection = left_connection;
        this.right_connection = right_connection;
    }

    public void draw() {



        if(!space_between_blocks) {
            //Draw
        }


    }

    public boolean isSpace_between_blocks() {
        return space_between_blocks;
    }


    public Block getLeft_connection() {
        return left_connection;
    }

    public Block getRight_connection() {
        return right_connection;
    }

    public void setSpace_between_blocks(boolean space_between_blocks) {
        this.space_between_blocks = space_between_blocks;
    }
}
