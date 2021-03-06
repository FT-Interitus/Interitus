/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.setup.steps.ArduinoSteps;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.ft.interitus.Program;
import de.ft.interitus.deviceconnection.arduino.BurnProgramm;
import de.ft.interitus.deviceconnection.arduino.SerialConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step3 {
    public static VisSelectBox<String> selectportlist;
    public static VisTextButton neuladen_button = new VisTextButton("Neuladen");
    public static VisTextButton brennen_button = new VisTextButton("Firmware aufspielen");
    public static VisLabel brenntext = new VisLabel("");
    public static VisLabel arduinosgefunden = new VisLabel("Arduinos: ");

    public static CharSequence auftragtext = "Bitte Stelle eine Verbindung zum Arduino her";
    public static VisLabel auftrag = new VisLabel(auftragtext);
    public static Timer time;
    public static boolean a;
    public static boolean isBurning = false;


    public static void brennen() {
        final String[] getrennt = selectportlist.getSelected().split(" ");

        Thread burningThread = new Thread() {
            @Override
            public void run() {
                isBurning = true;
                try {


                    if (true) {
                        BurnProgramm.burn(0, getrennt[0], "sketch_uno.hex");
                    }

                } catch (Exception e) {

                }
                isBurning = false;
            }
        };
        if (!burningThread.isAlive()) {
            burningThread.start();
        } else {

        }
    }

    public static void step3(final VisTable builder) {
        time = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                arduinosgefunden.setText("Arduinos: " + SerialConnection.Arduinos.size());

                if (isBurning) {
                    Program.logger.config(BurnProgramm.ausgabe);
                    brenntext.setText(BurnProgramm.ausgabe);
                    if (BurnProgramm.ausgabe.contains("Brennen hat funktioniert, nun kannst du dein Gerät konfigurieren")) {


                    }
                }
                if (SerialConnection.isRunning) {
                    brenntext.setText(SerialConnection.Authentifikation.getOutput());
                }

                if (SerialConnection.isRunning || isBurning == true) {
                    neuladen_button.setDisabled(true);
                    brennen_button.setDisabled(true);
                    selectportlist.setDisabled(true);
                } else {
                    neuladen_button.setDisabled(false);
                    brennen_button.setDisabled(false);
                    selectportlist.setDisabled(false);
                }

            }
        });
        time.start();

        selectportlist = new VisSelectBox<String>();

        builder.add(auftrag).expandX().row();
        builder.add(selectportlist);
        builder.add(neuladen_button).row();
        builder.add(brennen_button).padBottom(-50).row();
        builder.add(brenntext).padBottom(-200);
        builder.add(arduinosgefunden).padBottom(-500);
        selectportlist.setItems(SerialConnection.getPortNames());

        neuladen_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SerialConnection.searchArduino();
            }
        });

        brennen_button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                brennen();
            }
        });

    }

    public static void close() {
        if (time != null) {
            if (time.isRunning()) {
                time.stop();
            }
        }
    }
}
