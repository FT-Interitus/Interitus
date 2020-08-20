/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.utils.ArrayList;

public class DefaultSaveBlock extends SaveBlock {


    public DefaultSaveBlock(int x, int y, int index, int index_links, int index_rechts, boolean isspacebetweenrightblock, ArrayList<ArrayList<Integer>> nodes, int platformspecificblockid, ArrayList<String> parameters,ArrayList<ArrayList<Integer>> datawires,ArrayList<ArrayList<Integer>> datawiresindex,int blockmodus) {
        super(x, y, index, index_links, index_rechts, isspacebetweenrightblock, nodes, platformspecificblockid, parameters,datawires,datawiresindex,blockmodus);
    }
}
