package de.ft.interitus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.VisUI;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.data.user.experience.ExperienceManager;
import de.ft.interitus.loading.Loading;
import de.ft.interitus.plugin.PluginManagerHandler;
import de.ft.interitus.plugin.store.ReadStorePlugins;
import de.ft.interitus.utils.NetworkScan;

public class Programm extends Game {

    public static Programm INSTANCE;
    public static boolean inLoading = true;



    public Programm() {



        INSTANCE = this;
    }
    @Override
    public void create() {

      //  SSHConnection.update("192.168.2.112","pi","Pi-Server");
        Thread loadplugins = new Thread() {
            @Override
            public void run() {
                Var.pluginManager = new PluginManagerHandler();
                DisplayErrors.error = Var.pluginManager.init();

            }
        };
        VisUI.load(VisUI.SkinScale.X1);


        try {
            loadplugins.start(); //Plugins laden
            ReadStorePlugins.read(); //Ersten 10 Plugins im Store laden
        }catch (Exception e) {
            Var.nointernetconnection = true;
        }

        Thread seachnetwork = new Thread() {
            @Override
            public void run() {
                NetworkScan.get();
            }
        };
        seachnetwork.start();
        Data.init();
        ExperienceManager.init();
        setScreen(new Loading());
    }


    public void dispose() {
        System.out.println("Save");
        Data.close();
        System.out.println("saved");
    }


    public void pause() {

    }


}


