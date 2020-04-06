package de.ft.robocontrol;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import de.ft.robocontrol.loading.Loading;

public class Programm extends Game {

    public static Programm INSTANCE;
    public static boolean inLoading = true;

    public Programm() {

        INSTANCE = this;
    }
    @Override
    public void create() {
        setScreen(new Loading());
    }



}


