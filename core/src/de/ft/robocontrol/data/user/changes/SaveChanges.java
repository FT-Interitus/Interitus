package de.ft.robocontrol.data.user.changes;

import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockVar;

import java.util.Stack;

public class SaveChanges {

    static RevertBlock revblock;
    private static Stack changes = new Stack();


    public static boolean checkstack() {
        return changes.isEmpty();
    }

    protected static void changedValue(Block block, boolean created, boolean deleted) {

        if(block.getRight()==null&&block.getLeft()==null) {
            revblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created,deleted, -1, -1);

        }else if(block.getRight()==null&&block.getLeft()!=null) {
             revblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created,deleted, block.getLeft().getIndex(),-1);

        }else if(block.getRight()!=null&&block.getLeft()==null) {
             revblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created,deleted, -1,block.getRight().getIndex());

        }else{
             revblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created,deleted, block.getLeft().getIndex(), block.getRight().getIndex());

        }



        changes.push(revblock);

    }
    public static void revert() {


        RevertBlock revert = (RevertBlock) changes.pop();






        if(revert.isDeleted()) {
            BlockVar.blocks.add(revert.getIndex(),new Block(revert.getIndex(),revert.getX(), revert.getY(),revert.getW(),revert.getH()));
            if(revert.getLeft_index()!=-1) {
                BlockVar.blocks.get(revert.getIndex()).setLeft(BlockVar.blocks.get(revert.getLeft_index()));
            }
            if(revert.getRight_index()!=-1) {
                BlockVar.blocks.get(revert.getIndex()).setRight(BlockVar.blocks.get(revert.getRight_index()));
            }

            return;
        }


        Block torevert = BlockVar.blocks.get(revert.getIndex());


        if(revert.isCreated()) {
            torevert.delete();
            return;
        }

        if(revert.getX()!=torevert.getX()) {
            torevert.setX(revert.getX());
        }

        if(revert.getY()!=torevert.getY()) {
            torevert.setY(revert.getY());
        }

        if(revert.getW()!=torevert.getW()) {
            torevert.setWH(revert.getW(),revert.getH());
        }

        if(revert.getH()!=torevert.getH()) {
            torevert.setWH(revert.getW(), revert.getH());
        }


       if(torevert.getLeft()==null) {
           if(revert.getLeft_index()!=-1) {
               torevert.setLeft(BlockVar.blocks.get(revert.getLeft_index()));
           }
       }else{
           if(revert.getLeft_index()==-1) {
               torevert.getLeft().setRight(null);
               torevert.setLeft(null);
           }else{
               if(revert.getLeft_index()!=torevert.getLeft().getIndex()) {
                   torevert.getLeft().setRight(null);
                   torevert.setLeft(BlockVar.blocks.get(revert.getLeft_index()));
               }
           }
       }


        if(torevert.getRight()==null) {
            if(revert.getRight_index()!=-1) {
                torevert.setRight(BlockVar.blocks.get(revert.getRight_index()));
            }
        }else{
            if(revert.getRight_index()==-1) {
                torevert.getRight().setLeft(null);
                torevert.setRight(null);
            }else{
                if(revert.getRight_index()!=torevert.getRight().getIndex()) {
                    torevert.getRight().setLeft(null);
                    torevert.setRight(BlockVar.blocks.get(revert.getRight_index()));
                }
            }
        }


    }

}
