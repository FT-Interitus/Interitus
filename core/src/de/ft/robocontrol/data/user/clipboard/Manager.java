package de.ft.robocontrol.data.user.clipboard;

import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockVar;

public class Manager {

    private static SavedBlock sb = null;


    public static boolean checkcopy() {
        return !(sb == null);
    }

    public static void Copy() {
        //TODO hier eventuelle Array anpassen wenn mehrere Makiert werden sollen


        sb = new SavedBlock(BlockVar.markedblock.getX(), BlockVar.markedblock.getY(), BlockVar.markedblock.getW(), BlockVar.markedblock.getH(), BlockVar.markedblock.getIndex());


    }

    public static void paste() {
        Block paste = new Block(BlockVar.blocks.size(), sb.getX(), sb.getY(), sb.getW(), sb.getH());

        BlockVar.blocks.add(paste);

    }


}
