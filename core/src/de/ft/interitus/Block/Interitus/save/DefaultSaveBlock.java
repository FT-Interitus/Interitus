package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.SaveBlock;

import java.util.ArrayList;

public class DefaultSaveBlock extends SaveBlock {


    public DefaultSaveBlock(int x, int y, int index, int index_links, int index_rechts, boolean isspacebetweenrightblock, ArrayList<ArrayList<Integer>> nodes, int platformspecificblockid, ArrayList<String> parameters) {
        super(x, y, index, index_links, index_rechts, isspacebetweenrightblock, nodes, platformspecificblockid, parameters);
    }
}
