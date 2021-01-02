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

    @Override
    public SaveBlockV1 generate(Block block, ProjectVar projectVar) {


        dataWires.clear();
        dataWiresIndex.clear();
        dataWireMoving.clear();
        parameters.clear();


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




        ArrayList<SaveBlockV1> extendedBlocks = null;
        if (block.getExtendedBlocks() != null) {
            extendedBlocks = new ArrayList<>();
            for (Block extendedBlock : block.getExtendedBlocks()) {
                extendedBlocks.add(projectVar.projectType.getBlocktoSaveGenerator().generate(extendedBlock, projectVar));
            }


        }


        return new SaveBlockV1(block,block.getLeft(),block.getRight(),(ArrayList<String>) parameters.clone(),(ArrayList<ArrayList<Integer>>) dataWires.clone(),(ArrayList<ArrayList<Integer>>) dataWiresIndex.clone(),(ArrayList<ArrayList<ArrayList<Integer>>>) dataWireMoving.clone(),extendedBlocks!=null?(ArrayList<SaveBlockV1>) extendedBlocks.clone():null);
        //It isn't possible that it will return null!
    }
}
