package de.ft.robocontrol.utils;

import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.data.user.changes.DataManager;
import de.ft.robocontrol.data.user.changes.SaveChanges;

public class ClearActOpenProgramm {
    static final Thread clear =new Thread() {
        @Override
        public void run() {

        }
    };
    public static void clear() {


         try {
            while (BlockVar.blocks.size()!=-1) {
                BlockVar.blocks.get(0).delete();
            }

        }catch (Exception e) {

         }

        BlockVar.blocks.clear();

        BlockVar.biggestblock = null;
        BlockVar.markedblock = null;
        BlockVar.ismoving = false;
        BlockVar.showduplicat.clear();


        BlockVar.uberlapptmitmarkedblock.clear();
        BlockVar.blockmitdergrostenuberlappungmitmarkiertemblock = null;

        DataManager.saved();
        DataManager.filename = "New File";
        DataManager.path = "";
        SaveChanges.clearstacks();
    }
}
