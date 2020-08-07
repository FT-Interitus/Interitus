/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.setup.steps.RaspberryPiSteps;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.InputValidator;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisValidatableTextField;
import de.ft.interitus.UI.setup.SetupWindow;
import de.ft.interitus.utils.NetworkScan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Step3 {
    private static final String[] ipselectable = new String[NetworkScan.device.size() + 1]; //Plus eins weil noch ein Bitte auswählen hinzugefügt wird
    public static Timer time;
    public static VisValidatableTextField ipadresse;
    public static VisSelectBox<String> selectIPAdress;

    public static void step3(VisTable builder) {


        time = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {


            }
        });

        time.start();
        if (NetworkScan.piaddress != "") {
            builder.add(new VisLabel("Ein Raspberry Pi wurde automatisch gefunden.")).expandX();
        } else {
            builder.add(new VisLabel("Bitte gibt die IP-Adresse des Raspberry Pi's ein.")).expandX();
        }

        builder.row();
        builder.add(new VisLabel("IP-Adresse:")).padLeft(150).padBottom(-90).padLeft(-200);

        try {

            if(true ) {
                ipadresse = new VisValidatableTextField("");
                try {
                    if (!InetAddress.getByName(ipadresse.getText()).isReachable(50)) {
                        SetupWindow.Button_next.setDisabled(true);
                        SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                        SetupWindow.errorLabel.setText("Die IP-Adresse ist nicht erreichbar");
                    }
                } catch (IOException e) {

                }

            } else {
                ipadresse = new VisValidatableTextField(NetworkScan.piaddress);
            }
        } catch (NullPointerException e) {
            ipadresse = new VisValidatableTextField(NetworkScan.piaddress);
        }

        ipadresse.addValidator(new InputValidator() {
            @Override
            public boolean validateInput(String input) {
                if (input.length() - input.replace(".", "").length() == 3) {

                    try {
                        if (!input.split("\\.")[3].isEmpty()) {
                            if (!input.split("\\.")[2].isEmpty()) {
                                if (!input.split("\\.")[1].isEmpty()) {
                                    if (!input.split("\\.")[0].isEmpty()) {
                                        InetAddress testdevice = null;
                                        try {
                                            try {
                                                testdevice = InetAddress.getByName(input);
                                            } catch (UnknownHostException e) {

                                            }


                                            if (testdevice.isReachable(50)) {

                                                SetupWindow.Button_next.setDisabled(false);
                                                SetupWindow.errorLabel.setColor(0, 1, 0, 1);
                                                SetupWindow.errorLabel.setText("Alle Voraussetzungen erfüllt");
                                            } else {
                                                SetupWindow.Button_next.setDisabled(true);
                                                SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                                                SetupWindow.errorLabel.setText("Die IP-Adresse ist nicht erreichbar");
                                            }
                                        } catch (IOException e) {
                                            SetupWindow.Button_next.setDisabled(true);
                                            SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                                            SetupWindow.errorLabel.setText("Die IP-Adresse ist nicht erreichbar");
                                        } catch (NullPointerException e) {

                                        }

                                        return true;

                                    } else {
                                        SetupWindow.Button_next.setDisabled(true);
                                        SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                                        SetupWindow.errorLabel.setText("Bitte gebe eine gültige IP-Adresse an");
                                        return false;
                                    }
                                } else {
                                    SetupWindow.Button_next.setDisabled(true);
                                    SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                                    SetupWindow.errorLabel.setText("Bitte gebe eine gültige IP-Adresse an");
                                    return false;
                                }
                            } else {
                                SetupWindow.Button_next.setDisabled(true);
                                SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                                SetupWindow.errorLabel.setText("Bitte gebe eine gültige IP-Adresse an");
                                return false;
                            }
                        } else {
                            SetupWindow.Button_next.setDisabled(true);
                            SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                            SetupWindow.errorLabel.setText("Bitte gebe eine gültige IP-Adresse an");
                            return false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        SetupWindow.Button_next.setDisabled(true);
                        SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                        SetupWindow.errorLabel.setText("Bitte gebe eine gültige IP-Adresse an");
                        return false;
                    }
                } else {
                    SetupWindow.Button_next.setDisabled(true);
                    SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                    SetupWindow.errorLabel.setText("Bitte gebe eine gültige IP-Adresse an");
                    return false;
                }
            }
        });
        builder.add(ipadresse).expandX().padLeft(-470).padBottom(-90);
        selectIPAdress = new VisSelectBox<>();

        for (int i = 0; i < NetworkScan.device.size() + 1; i++) {
            if (i == 0) {
                ipselectable[i] = "Bitte Auswählen";
            } else {
                try {
                    ipselectable[i] = "IP: " + NetworkScan.device.get(i - 1).getHostAddress() + "/" + NetworkScan.device.get(i - 1).getHostName();
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }

        }
        selectIPAdress.setItems(ipselectable);
        builder.row();
        builder.add(new VisLabel("Falls du die IP-Adresse nicht weißt, sind hier alle Adressen einmal aufgelistet:")).padBottom(-220);
        builder.row();
        builder.add(selectIPAdress).expandX().padBottom(-300);
        selectIPAdress.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!selectIPAdress.getSelected().contains("Bitte Auswählen")) {
                    ipadresse.setText(selectIPAdress.getSelected().split(":")[1].split("/")[0].replace(" ", ""));
                }

            }
        });

    }

    public static void close() {
        try {
        } catch (NullPointerException e) {

        }


        if (time != null) {
            if (time.isRunning()) {
                time.stop();
            }
        }
    }

}
