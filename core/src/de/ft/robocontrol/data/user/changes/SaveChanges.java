package de.ft.robocontrol.data.user.changes;

import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockVar;

import java.util.Stack;

public class SaveChanges {
    private static Block changedblock;
    private static Stack changes = new Stack();

    public static void changedValue(Block block) {

        changedblock= block;
        changes.push(block);

    }
    public static void revert() {
        Block revert = (Block) changes.peek();
        if(revert==null) {

        }

        BlockVar.blocks.set(BlockVar.blocks.get(revert.getIndex()).getIndex(),revert);

    }

}
