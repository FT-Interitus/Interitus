package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Block.Wire;

import java.util.ArrayList;

public class DefaultSaveBlockGenerator implements BlocktoSaveGenerator {
    ArrayList<ArrayList<Integer>> nodes;

    @Override
    public SaveBlock generate(Block block) {
        nodes = new ArrayList<>();

        if (block.getWire_right() != null) {


            boolean goout = false;
            int nodecounter = 0;
            ArrayList<Integer> actnode = new ArrayList<>();
            Wire gofrom = block.getWire_right();
            while (!goout) {
                try {
                    if (!gofrom.getRight_connectionObject().amiablock()) {

                        actnode.clear();
                        nodecounter++;
                        actnode.add(gofrom.getRight_connectionObject().getwirenode().getX());
                        actnode.add(gofrom.getRight_connectionObject().getwirenode().getY());
                        actnode.add(gofrom.getRight_connectionObject().getwirenode().getW());
                        actnode.add(gofrom.getRight_connectionObject().getwirenode().getH());
                        gofrom = gofrom.getRight_connectionObject().getwirenode().getWire_right();

                        nodes.add((ArrayList<Integer>) actnode.clone());

                    } else {
                        goout = true;

                    }

                } catch (NullPointerException e) {
                    goout = true;
                    break;
                }


            }

            if (nodes.size() == 0) {
                nodes = null;
            }


        }

        if (block.getRight() != null) {

            if (block.getWire_right().isSpace_between_blocks()) {


                if (block.getLeft() == null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), true, nodes, block.getBlocktype().getID());

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, true, nodes, block.getBlocktype().getID());
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID());

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, true, nodes, block.getBlocktype().getID());

                } else {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, true, nodes, block.getBlocktype().getID());
                }


            } else {

                if (block.getLeft() == null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, nodes, block.getBlocktype().getID());

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, nodes, block.getBlocktype().getID());
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID());

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false, nodes, block.getBlocktype().getID());

                } else {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false, nodes, block.getBlocktype().getID());
                }

            }

        } else {

            if (block.getLeft() == null && block.getRight() != null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, nodes, block.getBlocktype().getID());

            } else if (block.getLeft() != null && block.getRight() == null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, nodes, block.getBlocktype().getID());
            } else if (block.getLeft() != null && block.getRight() != null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID());

            } else if (block.getLeft() == null && block.getRight() == null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false, nodes, block.getBlocktype().getID());

            } else {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false, nodes, block.getBlocktype().getID());
            }


        }

    }
}
