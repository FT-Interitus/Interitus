package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.SaveBlock;

import java.util.ArrayList;

public class DefaultSaveBlock extends SaveBlock {
   public DefaultSaveBlock(int x, int y, int index, int index_left, int index_right, boolean isspacebetweenrightblock, ArrayList<ArrayList<Integer>> nodes, int platformspecificblockid) {
       super(x,y,index,index_left,index_right,isspacebetweenrightblock,nodes,platformspecificblockid);


   }

}
