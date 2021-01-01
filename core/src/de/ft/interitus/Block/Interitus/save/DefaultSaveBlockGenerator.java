/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.Interitus.save;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.Block.Generators.BlockToSaveGenerator;
import de.ft.interitus.Block.Saving.SaveBlockV1;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.utils.ArrayList;

public class DefaultSaveBlockGenerator implements BlockToSaveGenerator {
    ArrayList<String> parameters = new ArrayList<>();
    ArrayList<ArrayList<Integer>> dataWires = new ArrayList<>();
    ArrayList<ArrayList<Integer>> dataWiresIndex = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<Integer>>> dataWireMoving = new ArrayList<>();
    String blocksettings = "";
    int blockmodus = 0;

    @Override
    public SaveBlockV1 generate(Block block, ProjectVar projectVar) {


        dataWires.clear();
        dataWiresIndex.clear();
        dataWireMoving.clear();
        parameters.clear();
        if (block.getBlocktype().blockModis.get(block.getBlocktype().actBlockModiIndex).getblocksettings() != null) {
            blocksettings = block.getBlocktype().blockModis.get(block.getBlocktype().actBlockModiIndex).getblocksettings().getSettings();
        } else {
            blocksettings = null;
        }

        if (block.getBlocktype().getBlockParameter() != null) {
            for (int i = 0; i < block.getBlocktype().getBlockParameter().size(); i++) {
                try {
                    parameters.add(block.getBlocktype().getBlockParameter().get(i).getParameter().toString());
                } catch (NullPointerException e) {

                    parameters.add("param");

                }
            }

        }

        if (block.getBlocktype().getBlockParameter() != null) {

            for (int i = 0; i < block.getBlocktype().getBlockParameter().size(); i++) {
                dataWires.add(new ArrayList<>());
                dataWiresIndex.add(new ArrayList<>());
                dataWireMoving.add(new ArrayList<>());
                if (!block.getBlocktype().getBlockParameter().get(i).getParameterType().isOutput()) {
                    dataWires.get(i).add(-1);
                    dataWiresIndex.get(i).add(-1);
                    continue;
                }

                if (block.getBlocktype().getBlockParameter().get(i).getDataWires().size() == 0) {
                    dataWires.get(i).add(-1);
                    dataWiresIndex.get(i).add(-1);
                    continue;
                }
                int counter = 0;
                for (DataWire dataWire : block.getBlocktype().getBlockParameter().get(i).getDataWires()) {
                    if (dataWire == projectVar.movingDataWire) {
                        //Ignore moving DataWires while saving
                        continue;
                    }
                    dataWires.get(i).add(dataWire.getParam_output().getBlock().getIndex());
                    dataWiresIndex.get(i).add(dataWire.getParam_output().getBlock().getBlocktype().getBlockParameter().indexOf(dataWire.getParam_output()));

                    dataWireMoving.get(i).add(new ArrayList<>());

                    dataWireMoving.get(i).get(counter).add(dataWire.getVerschiebung_1_Horizontale());
                    dataWireMoving.get(i).get(counter).add(dataWire.getVerschiebung_2_HorizontaleInput());
                    dataWireMoving.get(i).get(counter).add(dataWire.getVerschiebung_3_HorizontaleOutput());
                    dataWireMoving.get(i).get(counter).add(dataWire.getVerschiebung_4_VertikaleInput());
                    dataWireMoving.get(i).get(counter).add(dataWire.getVerschiebung_5_VertikaleInput());

                    counter++;

                }

            }
        }

        blockmodus = block.getBlocktype().getActBlockModeIndex();


        ArrayList<SaveBlockV1> extendedBlocks = null;
        if (block.getExtendedBlocks() != null) {
            extendedBlocks = new ArrayList<>();

            for (Block extendedBlock : block.getExtendedBlocks()) {

                extendedBlocks.add(projectVar.projectType.getBlocktoSaveGenerator().generate(extendedBlock, projectVar));


            }


        }


        if (block.getExtendedBlocks() == null) {
            if (block.getRight() != null) {

                if (block.getWire_right().isVisible()) {


                    if (block.getLeft() == null && block.getRight() != null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), true, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                    } else if (block.getLeft() != null && block.getRight() == null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, true, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));
                    } else if (block.getLeft() != null && block.getRight() != null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                    } else if (block.getLeft() == null && block.getRight() == null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, true, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                    }


                } else {

                    if (block.getLeft() == null && block.getRight() != null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                    } else if (block.getLeft() != null && block.getRight() == null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));
                    } else if (block.getLeft() != null && block.getRight() != null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                    } else if (block.getLeft() == null && block.getRight() == null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                    } else {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));
                    }

                }

            } else {

                if (block.getLeft() == null && block.getRight() != null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));

                } else {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()));
                }


            }

        } else {
            if (block.getRight() != null) {
                if (false) {


                    if (block.getLeft() == null && block.getRight() != null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), true, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());

                    } else if (block.getLeft() != null && block.getRight() == null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, true, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());
                    } else if (block.getLeft() != null && block.getRight() != null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());

                    } else if (block.getLeft() == null && block.getRight() == null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, true, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());

                    }


                } else {

                    if (block.getLeft() == null && block.getRight() != null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());

                    } else if (block.getLeft() != null && block.getRight() == null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());
                    } else if (block.getLeft() != null && block.getRight() != null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());

                    } else if (block.getLeft() == null && block.getRight() == null) {
                        return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());

                    }

                }

            } else {

                if (block.getLeft() == null && block.getRight() != null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());
                } else if (block.getLeft() != null && block.getRight() == null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());
                } else if (block.getLeft() != null && block.getRight() != null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), block.getLeft().getIndex(), block.getRight().getIndex(), false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());
                } else if (block.getLeft() == null && block.getRight() == null) {
                    return new SaveBlockV1(block.getX(), block.getY(), block.getIndex(), -1, -1, false, block.getBlocktype().getID(), (ArrayList<String>) parameters.clone(), ((ArrayList<ArrayList<Integer>>) dataWires.clone()), ((ArrayList<ArrayList<Integer>>) dataWiresIndex.clone()), blockmodus, block.getBlocktype().getAddonName(), blocksettings, ((ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone()), (ArrayList<SaveBlockV1>) extendedBlocks.clone());
                }


            }

        }

        //It isn't possible that it will return null!
        return null;
    }
}
