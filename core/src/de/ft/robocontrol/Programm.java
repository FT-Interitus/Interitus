package de.ft.robocontrol;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import de.ft.robocontrol.loading.AssetLoader;
import de.ft.robocontrol.loading.Loading;

public class Programm extends Game {

   public static boolean inWelcome = false;
    public  static boolean inProgramm = false;
    public static boolean inLoading = true;
    public static Programm INSTANCE;


    public Programm() {
        INSTANCE = this;
    }
    @Override
    public void create() {


        System.out.println("Test");

        setScreen(new Loading());


    }






}
