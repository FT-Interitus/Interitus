package de.ft.interitus.data.user.changes;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.BlockVar;

import java.util.Stack;

/***
 * @deprecated
 */
public class SaveChanges {
/*
    static RevertBlock revblock;
    static RevertBlock redoblock;
    private static final Stack changes = new Stack();

    private static final Stack redo = new Stack();


    public static void clearstacks() {
        redo.clear();
        changes.clear();
    }


    public static boolean checkstack() {
        return changes.isEmpty();
    }

    public static boolean checkredostack() {
        return redo.isEmpty();
    }

    protected static void changedValue(Block block, boolean created, boolean deleted) {

        if (block.getRight() == -1 && block.getLeft() == -1) {
            revblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created, deleted, -1, -1);

        } else if (block.getRight() == -1 && block.getLeft() != -1) {
            revblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created, deleted, block.getLeft(), -1);

        } else if (block.getRight() != -1 && block.getLeft() == -1) {
            revblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created, deleted, -1, block.getRight());

        } else {
            revblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created, deleted, block.getLeft(), block.getRight());

        }


        changes.push(revblock);

    }


    protected static void redosave(Block block, boolean created, boolean deleted) {

        if (block.getRight() == -1 && block.getLeft() == -1) {
            redoblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created, deleted, -1, -1);

        } else if (block.getRight() == -1 && block.getLeft() != -1) {
            redoblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created, deleted, block.getLeft(), -1);

        } else if (block.getRight() != -1 && block.getLeft() == -1) {
            redoblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created, deleted, -1, block.getRight());

        } else {
            redoblock = new RevertBlock(block.getX(), block.getY(), block.getW(), block.getH(), block.getIndex(), created, deleted, block.getLeft(), block.getRight());

        }


        redo.push(redoblock);

    }


    public static void revert() {


        RevertBlock revert = (RevertBlock) changes.pop();


        if (revert.isDeleted()) { //TODO index wieder zurück
          //  BlockVar.blocks.add(revert.getIndex(), new Block(revert.getIndex(), revert.getX(), revert.getY(), revert.getW(), revert.getH()));
            if (revert.getLeft_index() != -1) {
                //BlockVar.blocks.get(revert.getIndex()).setLeft(BlockVar.blocks.get(revert.getLeft_index()));
            }
            if (revert.getRight_index() != -1) {
                //BlockVar.blocks.get(revert.getIndex()).setRight(BlockVar.blocks.get(revert.getRight_index()));
            }


            /////redo

            redosave(BlockVar.blocks.get(revert.getIndex()), true, false);


            /////

            return;
        }


        Block torevert = BlockVar.blocks.get(revert.getIndex());


        if (revert.isCreated()) {
            redosave(BlockVar.blocks.get(revert.getIndex()), false, true);
            torevert.delete(false);

            return;
        }

        redosave(BlockVar.blocks.get(revert.getIndex()), false, false);
        if (revert.getX() != torevert.getX()) {
            torevert.setX(revert.getX());
        }

        if (revert.getY() != torevert.getY()) {
            torevert.setY(revert.getY());
        }

        if (revert.getW() != torevert.getW()) {
            torevert.setWH(revert.getW(), revert.getH());
        }

        if (revert.getH() != torevert.getH()) {
            torevert.setWH(revert.getW(), revert.getH());
        }


        if (torevert.getLeft() == -1) {
            if (revert.getLeft_index() != -1) {
                torevert.setLeft(BlockVar.blocks.get(revert.getLeft_index()));
            }
        } else {
            if (revert.getLeft_index() == -1) {
                torevert.getLeft().setRight(null);
                torevert.setLeft(null);
            } else {
                if (revert.getLeft_index() != torevert.getLeft().getIndex()) {
                    torevert.getLeft().setRight(null);
                    torevert.setLeft(BlockVar.blocks.get(revert.getLeft_index()));
                }
            }
        }


        if (torevert.getRight() == null) {
            if (revert.getRight_index() != -1) {
                torevert.setRight(BlockVar.blocks.get(revert.getRight_index()));
            }
        } else {
            if (revert.getRight_index() == -1) {
                torevert.getRight().setLeft(null);
                torevert.setRight(null);
            } else {
                if (revert.getRight_index() != torevert.getRight().getIndex()) {
                    torevert.getRight().setLeft(null);
                    torevert.setRight(BlockVar.blocks.get(revert.getRight_index()));
                }
            }
        }


    }


    public static void redo() {

        RevertBlock revert = (RevertBlock) redo.pop();


        if (revert.isDeleted()) {
           // BlockVar.blocks.add(revert.getIndex(), new Block(revert.getIndex(), revert.getX(), revert.getY(), revert.getW(), revert.getH()));
            if (revert.getLeft_index() != -1) {
                BlockVar.blocks.get(revert.getIndex()).setLeft(BlockVar.blocks.get(revert.getLeft_index()));
            }
            if (revert.getRight_index() != -1) {
                BlockVar.blocks.get(revert.getIndex()).setRight(BlockVar.blocks.get(revert.getRight_index()));
            }


            /////redo

            changedValue(BlockVar.blocks.get(revert.getIndex()), true, false);


            /////

            return;
        }


        Block torevert = BlockVar.blocks.get(revert.getIndex());


        if (revert.isCreated()) {
            changedValue(BlockVar.blocks.get(revert.getIndex()), false, true);
            torevert.delete(false);


            return;
        }

        changedValue(BlockVar.blocks.get(revert.getIndex()), false, false);

        if (revert.getX() != torevert.getX()) {
            torevert.setX(revert.getX());
        }

        if (revert.getY() != torevert.getY()) {
            torevert.setY(revert.getY());
        }

        if (revert.getW() != torevert.getW()) {
            torevert.setWH(revert.getW(), revert.getH());
        }

        if (revert.getH() != torevert.getH()) {
            torevert.setWH(revert.getW(), revert.getH());
        }


        if (torevert.getLeft() == null) {
            if (revert.getLeft_index() != -1) {
                torevert.setLeft(BlockVar.blocks.get(revert.getLeft_index()));
            }
        } else {
            if (revert.getLeft_index() == -1) {
                torevert.getLeft().setRight(null);
                torevert.setLeft(null);
            } else {
                if (revert.getLeft_index() != torevert.getLeft().getIndex()) {
                    torevert.getLeft().setRight(null);
                    torevert.setLeft(BlockVar.blocks.get(revert.getLeft_index()));
                }
            }
        }


        if (torevert.getRight() == null) {
            if (revert.getRight_index() != -1) {
                torevert.setRight(BlockVar.blocks.get(revert.getRight_index()));
            }
        } else {
            if (revert.getRight_index() == -1) {
                torevert.getRight().setLeft(null);
                torevert.setRight(null);
            } else {
                if (revert.getRight_index() != torevert.getRight().getIndex()) {
                    torevert.getRight().setLeft(null);
                    torevert.setRight(BlockVar.blocks.get(revert.getRight_index()));
                }
            }
        }


    }

*/
}
