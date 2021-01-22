/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.InputManager;
import de.ft.interitus.UI.ProgramGrid;
import de.ft.interitus.WindowManager;
import de.ft.interitus.utils.ShapeRenderer;

public class ExtendedBlocksApplicationListener implements ApplicationListener {
    public static OrthographicCamera cam;
    public static Viewport viewport;
    private OrthographicCamera UIcam;
    private SpriteBatch UIbatch;
    private ShapeRenderer shapeRenderer;
    private InputManager inputManager;


    @Override
    public void create() {
        cam = new OrthographicCamera(WindowAPI.getWidth(), WindowAPI.getHeight());

        viewport = new ScreenViewport(cam);

        UIbatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        cam.position.set(WindowAPI.getWidth() / 2f + 50, WindowAPI.getHeight() / 2f, 0);
        UIcam = new OrthographicCamera(WindowAPI.getWidth(), WindowAPI.getHeight());
        UIcam.position.set(WindowAPI.getWidth() / 2f + 50, WindowAPI.getHeight() / 2f, 0);

        inputManager = new InputManager();

        de.ft.interitus.UI.Viewport.init(cam, inputManager);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        cam.update();
        UIcam.update();
        UIbatch.setProjectionMatrix(UIcam.combined);
        shapeRenderer.setProjectionMatrix(UIcam.combined);
        Gdx.gl.glClearColor(Settings.theme.ClearColor().r, Settings.theme.ClearColor().g, Settings.theme.ClearColor().b, Settings.theme.ClearColor().a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ProgramGrid.draw(shapeRenderer, cam);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
