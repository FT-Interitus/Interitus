/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.utils.ArrayList;

public class DefaultSaveBlockGenerator implements BlocktoSaveGenerator {
    ArrayList<ArrayList<Integer>> nodes;
    ArrayList<String> parameters = new ArrayList<>();
    ArrayList<Integer> leftdatawireconnections = new ArrayList<>();
    ArrayList<Integer> leftdatawireconnections_index = new ArrayList<>();

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

        leftdatawireconnections.clear();
        leftdatawireconnections_index.clear();
        parameters.clear();


        if (block.getBlocktype().getBlockParameter() != null) {
            for (int i = 0; i < block.getBlocktype().getBlockParameter().size(); i++) {
                try {
                    parameters.add(block.getBlocktype().getBlockParameter().get(i).getParameter().toString());
                }catch (NullPointerException e) {

                    parameters.add("param");

                }
            }

        }

            if(block.getBlocktype().getBlockParameter()!=null) {
                for (int i = 0; i < block.getBlocktype().getBlockParameter().size(); i++) {

                    if (!block.getBlocktype().getBlockParameter().get(i).getParameterType().isOutput()) {
                        leftdatawireconnections.add(-1);
                        leftdatawireconnections_index.add(-1);
                        continue;
                    }

                    if (block.getBlocktype().getBlockParameter().get(i).getDatawire() == null) {
                        leftdatawireconnections.add(-1);
                        leftdatawireconnections_index.add(-1);
                        continue;
                    }

                    leftdatawireconnections.add(block.getBlocktype().getBlockParameter().get(i).getDatawire().getParam_output().getBlock().getIndex());
                    leftdatawireconnections_index.add(block.getBlocktype().getBlockParameter().get(i).getDatawire().getParam_output().getBlock().getBlocktype().getBlockParameter().indexOf(block.getBlocktype().getBlockParameter().get(i).getDatawire().getParam_output()));
                }
            }


        if (block.getRight() != null) {
            if (block.getWire_right().isSpace_between_blocks()) {


                if (block.getLeft() == null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

                } else {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());
                }


            } else {

                if (block.getLeft() == null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

                } else {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());
                }

            }

        } else {

            if (block.getLeft() == null && block.getRight() != null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

            } else if (block.getLeft() != null && block.getRight() == null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());
            } else if (block.getLeft() != null && block.getRight() != null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

            } else if (block.getLeft() == null && block.getRight() == null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());

            } else {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), (ArrayList<Integer>) leftdatawireconnections.clone(), (ArrayList<Integer>) leftdatawireconnections_index.clone());
            }


        }

    }
}
