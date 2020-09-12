/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI_old.setup.steps.RaspberryPiSteps;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.UI_old.setup.SetupWindow;

public class Step5 {


    private static final VisLabel installationprogress = new VisLabel("System-Update...\n\n \n \n\n \n\n ");


    public static void step5(VisTable content) {

        final Thread install = new Thread() {
            @Override
            public void run() {
                SetupWindow.errorLabel.setColor(1, 0, 0, 1);
                SetupWindow.errorLabel.setText("Vorgang gestartet...");
              //  SSHConnection.update(SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().ip, SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().username, SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().password);

                installationprogress.setText("System-Update abgeschlossen\n\nPython installieren...\n\n \n\n \n\n ");
            //    SSHConnection.installpython(SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().ip, SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().username, SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().password);
                installationprogress.setText("System-Update abgeschlossen\n\nPython installieren abgeschlossen\n\n Weitere Aktion...\n\n \n\n ");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    DisplayErrors.error = e;
                }
                installationprogress.setText("System-Update abgeschlossen\n\nPython installieren abgeschlossen\n\nWeitere Aktion abgeschlossen\n\nNoch weiteres...\n\n ");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                installationprogress.setText("System-Update abgeschlossen\n\nPython installieren abgeschlossen\n\nWeitere Aktion abgeschlossen\n\nNoch weiteres abgeschlossen\n\n ");

                SetupWindow.errorLabel.setColor(0, 1, 0, 1);
                SetupWindow.errorLabel.setText("Vorgang erfolgreich abgeschlossen");

                SetupWindow.Button_next.setDisabled(false);
                SetupWindow.Button_cancle.setDisabled(false);
            }
        };

        install.start();


        SetupWindow.Button_cancle.setDisabled(true);
        SetupWindow.Button_next.setDisabled(true);
        SetupWindow.Button_previouse.setDisabled(true);

        content.add(new VisLabel("Das Gerät wird installiert... \n")).expandX().padBottom(-100).padTop(-100);
        // content.add(new VisImage(loadingAnimation)).expandX();
        content.row();

        content.add(installationprogress).expandX().padTop(25).padBottom(-185);

        content.row();
        //  content.add(new VisLabel("Python installieren")).expandX().padTop(-700).padBottom(-800);


    }

    public static void close() {

    }

}
