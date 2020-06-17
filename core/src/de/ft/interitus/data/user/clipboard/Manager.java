package de.ft.interitus.data.user.clipboard;

import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Var;

public class Manager {

    private static SavedBlock sb = null;


    public static boolean checkcopy() {
        return !(sb == null);
    }

    public static void Copy() {
        //TODO hier eventuelle Array anpassen wenn mehrere Makiert werden sollen


        sb = new SavedBlock(Var.openprojects.get(Var.openprojectindex).markedblock.getX(), Var.openprojects.get(Var.openprojectindex).markedblock.getY(), Var.openprojects.get(Var.openprojectindex).markedblock.getW(), Var.openprojects.get(Var.openprojectindex).markedblock.getH(), Var.openprojects.get(Var.openprojectindex).markedblock.getIndex());


    }

    public static void paste() {
        //Block paste = new Block(BlockVar.blocks.size(), sb.getX(), sb.getY(), sb.getW(), sb.getH());

     //   BlockVar.blocks.add(paste);

    }


}
