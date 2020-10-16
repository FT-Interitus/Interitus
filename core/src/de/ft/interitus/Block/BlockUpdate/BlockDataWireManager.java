/*
 * Copyright (c) 2020.
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
        if (block.getBlocktype() == null) return;
        if (block.getBlocktype().getBlockParameter() == null) return;
        assert ProjectManager.getActProjectVar() != null;
        if (ProjectManager.getActProjectVar().moveingdatawire != null) return;

        for (Parameter parameter : block.getBlocktype().getBlockParameter()) {

            if (!parameter.getParameterType().isOutput()) continue;

            if (pressedOnParameterSpace(parameter)) {
                DataWire instance = new DataWire(parameter);
                parameter.getDataWires().add(instance);
                ProjectManager.getActProjectVar().moveingdatawire = instance;
            }


        }


    }

    private static boolean pressedOnParameterSpace(Parameter parameter) {
        boolean collisionCheck = CheckCollision.checkpointwithobject(parameter.getX(), parameter.getY(), UIVar.parameter_width, UIVar.parameter_height, Unproject.unproject());
        return collisionCheck && Gdx.input.isButtonJustPressed(0);
    }

}
