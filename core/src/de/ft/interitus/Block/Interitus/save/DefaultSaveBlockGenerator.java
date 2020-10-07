/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.Block.Generators.BlocktoSaveGenerator;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Block.Saving.SaveBlockV1;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;

public class DefaultSaveBlockGenerator implements BlocktoSaveGenerator {
    ArrayList<ArrayList<Integer>> nodes;
    ArrayList<String> parameters = new ArrayList<>();
   ArrayList<ArrayList<Integer>> datawires = new ArrayList<>();
   ArrayList<ArrayList<Integer>> datawiresindex = new ArrayList<>();
   ArrayList<ArrayList<ArrayList<Integer>>> datawiremoveing = new ArrayList<>();
    String blocksettings ="";
   int blockmodus =0;

    @Override
    public SaveBlockV1 generate(Block block) {
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
        datawiremoveing.clear();
        parameters.clear();
        if(block.getBlocktype().blockModis.get(block.getBlocktype().actBlockModiIndex).getblocksettings()!=null) {
            blocksettings = block.getBlocktype().blockModis.get(block.getBlocktype().actBlockModiIndex).getblocksettings().getSettings();
        }else{
            blocksettings = null;
        }

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
                    datawiremoveing.add(new ArrayList<>());
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
                    int counter = 0;
                    for(DataWire dataWire:block.getBlocktype().getBlockParameter().get(i).getDatawire()) {
                        if(dataWire== ProjectManager.getActProjectVar().moveingdatawire) {
                            //Ignore moving DataWires while saving
                            continue;
                        }
                        datawires.get(i).add(dataWire.getParam_output().getBlock().getIndex());
                        datawiresindex.get(i).add(dataWire.getParam_output().getBlock().getBlocktype().getBlockParameter().indexOf(dataWire.getParam_output()));

                        datawiremoveing.get(i).add(new ArrayList<>());

                        datawiremoveing.get(i).get(counter).add(dataWire.getVerschiebung_1_Horizontale());
                        datawiremoveing.get(i).get(counter).add(dataWire.getVerschiebung_2_HorizontaleInput());
                        datawiremoveing.get(i).get(counter).add(dataWire.getVerschiebung_3_HorizontaleOutput());
                        datawiremoveing.get(i).get(counter).add(dataWire.getVerschiebung_4_VertikaleInput());
                        datawiremoveing.get(i).get(counter).add(dataWire.getVerschiebung_5_VertikaleInput());

                        counter++;

                    }

                }
            }

            blockmodus = block.getBlocktype().getActBlockModiIndex();


        if (block.getRight() != null) {
            if (block.getWire_right().isSpace_between_blocks()) {


                if (block.getLeft() == null && block.getRight() != null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, true, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

                } else {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, true,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));
                }


            } else {

                if (block.getLeft() == null && block.getRight() != null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>)datawiremoveing.clone()));
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

                } else {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));
                }

            }

        } else {

            if (block.getLeft() == null && block.getRight() != null) {
                return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

            } else if (block.getLeft() != null && block.getRight() == null) {
                return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));
            } else if (block.getLeft() != null && block.getRight() != null) {
                return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

            } else if (block.getLeft() == null && block.getRight() == null) {
                return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));

            } else {
                return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false,nodes, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(),  ((ArrayList<ArrayList<Integer>>) datawires.clone()),((ArrayList<ArrayList<Integer>>) datawiresindex.clone()),blockmodus,block.getBlocktype().addonname(),blocksettings,((ArrayList<ArrayList<ArrayList<Integer>>>) datawiremoveing.clone()));
            }


        }

    }
}
