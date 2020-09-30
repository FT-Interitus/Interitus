/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.utils.ArrayList;

public class DefaultSaveBlock extends SaveBlock {

    @java.io.Serial
    private static final long serialVersionUID = -2944346798155533797L;

    public DefaultSaveBlock(int x, int y, int index, int index_links, int index_rechts, boolean isspacebetweenrightblock, ArrayList<ArrayList<Integer>> nodes, int platformspecificblockid, ArrayList<String> parameters,ArrayList<ArrayList<Integer>> datawires,ArrayList<ArrayList<Integer>> datawiresindex,int blockmodus,String addon,String blocksettings) {
        super(x, y, index, index_links, index_rechts, isspacebetweenrightblock, nodes, platformspecificblockid, parameters,datawires,datawiresindex,blockmodus,addon,blocksettings);
    }
}
