/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.utils.ArrayList;

public class DefaultSaveBlockGenerator implements BlocktoSaveGenerator {
    ArrayList<ArrayList<Integer>> nodes;
    ArrayList<String> parameters = new ArrayList<>();
   ArrayList<ArrayList<Integer>> datawires = new ArrayList<>();
   ArrayList<ArrayList<Integer>> datawiresindex = new ArrayList<>();

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

        datawires.clear();
        datawiresindex.clear();
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
                    datawires.add(new ArrayList<>());
                    datawiresindex.add(new ArrayList<>());
                    if (!block.getBlocktype().getBlockParameter().get(i).getParameterType().isOutput()) {
                       datawires.get(i).add(-1);
                       datawiresindex.get(i).add(-1);
                        continue;
                    }

                    if (block.getBlocktype().getBlockParameter().get(i).getDatawire().size()==0) {
                        datawires.get(i).add(-1);
                        datawiresindex.get(i).add(-1);
                        continue;
                    }

                    for(DataWire dataWire:block.getBlocktype().getBlockParameter().get(i).getDatawire()) {
                        datawires.get(i).add(dataWire.getParam_output().getBlock().getIndex());
                        datawiresindex.get(i).add(dataWire.getParam_output().getBlock().getBlocktype().getBlockParameter().indexOf(dataWire.getParam_output()));

                    }

                }
            }


        if (block.getRight() != null) {
            if (block.getWire_right().isSpace_between_blocks()) {


                if (block.getLeft() == null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

                } else {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, true,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));
                }


            } else {

                if (block.getLeft() == null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

                } else {
                    return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));
                }

            }

        } else {

            if (block.getLeft() == null && block.getRight() != null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

            } else if (block.getLeft() != null && block.getRight() == null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));
            } else if (block.getLeft() != null && block.getRight() != null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

            } else if (block.getLeft() == null && block.getRight() == null) {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));

            } else {
                return new DefaultSaveBlock(block.getX(), block.getY(), block.getIndex(), -1, -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()));
            }


        }

    }
}
