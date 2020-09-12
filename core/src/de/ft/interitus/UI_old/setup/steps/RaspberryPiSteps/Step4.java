/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI_old.setup.steps.RaspberryPiSteps;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import de.ft.interitus.UI_old.setup.SetupWindow;

public class Step4 {
    public static VisTextField username = new VisTextField("pi");
    public static VisTextField password = new VisTextField("raspberry");
    public static VisTextButton trytoconnect = new VisTextButton("Verbinden");

    public static void step4(VisTable builder) {

        password.setDisabled(false);
        username.setDisabled(false);
        trytoconnect.setDisabled(false);

        SetupWindow.errorLabel.setColor(1, 0, 0, 1);
        SetupWindow.errorLabel.setText("Drücke auf Verbinden um zu bestätigen");
        SetupWindow.Button_next.setDisabled(true);
        builder.add(new VisLabel("Gibt hier die SSH Verbindungs-Daten ein.")).expandX().padRight(-120);
        builder.row();
        builder.add(new VisLabel("Benutername: ")).padRight(-1220).padLeft(-1650); //.padLeft(-450)
        builder.add(username).padLeft(-650);// .padLeft(-750)
        builder.add(new VisLabel("Passwort: ")).padLeft(-570).padRight(-200);//.padLeft(-475)
        builder.add(password).padRight(70).padLeft(-150); //.padLeft(-250)
        builder.row();

        SetupWindow.Button_previouse.setDisabled(false);
        builder.add(new VisLabel("Falls du nicht weißt was du hier einsetzen sollst:\n\nHier wird angegeben wie sich das Programm am Raspberry Pi anmeldet.\nWenn du deinen Pi frisch Installiert hast kannst du hier die Werte unverändert lassen.")).padBottom(-230).padTop(100).padRight(-80);
        builder.row();

        builder.add(trytoconnect).expandX().padBottom(-50);

        trytoconnect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                Thread connectthread = new Thread() {

                    @Override
                    public void run() {


                        if (trytoconnect.isPressed()) {
                            SetupWindow.Button_cancle.setDisabled(true);
                            SetupWindow.Button_previouse.setDisabled(true);
                            trytoconnect.setDisabled(true);

                            username.setDisabled(true);
                            password.setDisabled(true);
                            SetupWindow.Button_next.setDisabled(true);

                            if(true ) {                                  SetupWindow.errorLabel.setColor(0, 1, 0, 1);
                                SetupWindow.errorLabel.setText("Verbindung erfolgreich");
                                SetupWindow.Button_next.setDisabled(false);
                                trytoconnect.setDisabled(true);
                            } else {

                                SetupWindow.Button_next.setDisabled(true);

                                password.setDisabled(false);
                                username.setDisabled(false);
                                SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                                SetupWindow.errorLabel.setText("Keine Verbindung möglich");
                                trytoconnect.setDisabled(false);
                            }
                            SetupWindow.Button_cancle.setDisabled(false);
                            SetupWindow.Button_previouse.setDisabled(false);
                            trytoconnect.setDisabled(false);
                        }
                    }
                };

                connectthread.start();
            }
        });


    }

    public static void close() {
        try {
        } catch (NullPointerException e) {

        }
    }
}
