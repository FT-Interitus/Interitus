package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Block.Wire;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;

public class DefaultSaveBlockGenerator implements BlocktoSaveGenerator {
     ArrayList<ArrayList<Integer>> nodes;
    @Override
    public SaveBlock generate(Block block) {
nodes=new ArrayList<>();

        if(block.getWire_right()!=null) {


            boolean goout = false;
            int nodecounter = 0;
            ArrayList<Integer> actnode = new ArrayList<>();
            Wire gofrom = block.getWire_right();
            while(!goout) {
                try {
                    if (!gofrom.getRight_connection().amiablock()) {
                       actnode.clear();
                        nodecounter++;
                        gofrom = gofrom.getRight_connection().getWire_right();
                        actnode.add(gofrom.getRight_connection().getX());
                        actnode.add(gofrom.getRight_connection().getY());
                        actnode.add(gofrom.getRight_connection().getW());
                        actnode.add(gofrom.getRight_connection().getH());
                        nodes.add( actnode);
                    } else {
                        goout = true;
                    }

                }catch (NullPointerException e)  {
                    goout = true;
                    break;
                }


            }

            if(nodes.size()==0) {
                nodes = null;
            }


        }

        if(block.getRight()!=-1) {

            if(block.getWire_right().isSpace_between_blocks()) {
                return new DefaultSaveBlock(block.getX(),block.getY(),block.getIndex(),block.getLeft(),block.getRight(),true,nodes);
            }else{
                return new DefaultSaveBlock(block.getX(),block.getY(),block.getIndex(),block.getLeft(),block.getRight(),false,nodes);

            }

        }else {
            return new DefaultSaveBlock(block.getX(),block.getY(),block.getIndex(),block.getLeft(),block.getRight(),false,nodes);
        }

    }
}
