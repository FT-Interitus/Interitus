package de.ft.interitus.data.user.clipboard;

import de.ft.interitus.projecttypes.ProjectManager;

public class Manager {

    private static SavedBlock sb = null;


    public static boolean checkcopy() {
        return !(sb == null);
    }

    public static void Copy() {
        //TODO hier eventuelle Array anpassen wenn mehrere Makiert werden sollen


        sb = new SavedBlock(ProjectManager.getActProjectVar().markedblock.getX(), ProjectManager.getActProjectVar().markedblock.getY(), ProjectManager.getActProjectVar().markedblock.getW(), ProjectManager.getActProjectVar().markedblock.getH(), ProjectManager.getActProjectVar().markedblock.getIndex());


    }

    public static void paste() {
        //Block paste = new Block(BlockVar.blocks.size(), sb.getX(), sb.getY(), sb.getW(), sb.getH());

        //   BlockVar.blocks.add(paste);

    }


}
