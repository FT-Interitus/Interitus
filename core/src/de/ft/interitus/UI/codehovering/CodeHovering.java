/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.codehovering;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Settings;
import de.ft.interitus.WindowManager;
import de.ft.interitus.utils.Unproject;

public class CodeHovering {

    public static void drawHovering() {


        WindowManager.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        WindowManager.BlockshapeRenderer.setColor(Settings.theme.ClearColor());


        WindowManager.BlockshapeRenderer.circle(Unproject.unproject().x, Unproject.unproject().y, 78, 50);


        WindowManager.BlockshapeRenderer.end();

    }


}
