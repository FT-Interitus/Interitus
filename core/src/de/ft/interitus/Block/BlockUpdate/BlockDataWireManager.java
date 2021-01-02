/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block.BlockUpdate;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.Unproject;

public class BlockDataWireManager {
    public static void update(Block block) {
        if (block.getBlockType() == null) return;
        if (block.getBlockType().getBlockParameter() == null) return;
        assert ProjectManager.getActProjectVar() != null;
        if (ProjectManager.getActProjectVar().movingDataWire != null) return;

        for (Parameter parameter : block.getBlockType().getBlockParameter()) {

            if (!parameter.getParameterType().isOutput()) continue;

            if (pressedOnParameterSpace(parameter)) {
                DataWire instance = new DataWire(parameter);
                parameter.getDataWires().add(instance);
                ProjectManager.getActProjectVar().movingDataWire = instance;
            }


        }


    }

    protected static boolean pressedOnParameterSpace(Parameter parameter) {
        boolean collisionCheck = CheckCollision.checkpointwithobject(parameter.getX(), parameter.getY(), UIVar.parameter_width, UIVar.parameter_height, Unproject.unproject());
        return collisionCheck && Gdx.input.isButtonJustPressed(0);
    }


    protected static boolean checkParameterEject(Block block) {
        if (block.getBlockType().getBlockParameter() != null) {

            for (Parameter parameter : block.getBlockType().getBlockParameter())
                if (CheckCollision.checkpointwithobject(parameter.getX(), parameter.getY(), UIVar.parameter_width, UIVar.parameter_height, Unproject.unproject()) && parameter.getDataWires().size() != 0 && !parameter.getParameterType().isOutput()) {
                    ejectDataWire(parameter.getDataWires().get(0));
                    parameter.getDataWires().clear();
                    return true;
                }

        }

        return false;
    }

    private static void ejectDataWire(DataWire dataWire) {
        assert ProjectManager.getActProjectVar() != null;
        dataWire.setParam_output(null);
        ProjectManager.getActProjectVar().movingDataWire = dataWire;

    }

}
