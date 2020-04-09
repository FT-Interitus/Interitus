package de.ft.robocontrol.UI.setup.steps.RaspberryPiSteps;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.*;
import de.ft.robocontrol.UI.setup.SetupWindow;
import de.ft.robocontrol.roboconnection.raspberrypi.SSHConnection;

public class Step4 {
    public static VisTextField username = new VisTextField("pi");
    public static VisTextField password =new VisTextField("raspberry");
    public static VisTextButton trytoconnect = new VisTextButton("Verbinden");
    public static void step4(VisTable builder){

        password.setDisabled(false);
        username.setDisabled(false);
        trytoconnect.setDisabled(false);

        SetupWindow.errorLabel.setColor(1,0,0,1);
        SetupWindow.errorLabel.setText("Drücke auf Verbinden um zu bestätigen");
        SetupWindow.Button_next.setDisabled(true);
        builder.add(new VisLabel("Gibt hier die SSH Verbindungs-Daten ein.")).expandX().padRight(-120);
        builder.row();
        builder.add(new VisLabel("Benutername: ")).padRight(-1220).padLeft(-1650); //.padLeft(-450)
        builder.add(username).padLeft(-650);// .padLeft(-750)
        builder.add(new VisLabel("Passwort: ")).padLeft(-570).padRight(-200);//.padLeft(-475)
        builder.add(password).padRight(70).padLeft(-150); //.padLeft(-250)
        builder.row();

        builder.add(new VisLabel("Falls du nicht weißt was du hier einsetzen sollst:\n\nHier wird angegeben wie sich das Programm am Raspberry Pi anmeldet.\nWenn du deinen Pi frisch Installiert hast kannst du hier die Werte unverändert lassen.")).padBottom(-230).padTop(100).padRight(-80);
builder.row();

builder.add(trytoconnect).expandX().padBottom(-50);

trytoconnect.addListener(new ChangeListener() {
    @Override
    public void changed(ChangeEvent event, Actor actor) {

        if(trytoconnect.isPressed()) {
            username.setDisabled(true);
            password.setDisabled(true);
            SetupWindow.Button_next.setDisabled(true);

            if(SSHConnection.checkconnection(SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().ip,username.getText(),password.getText())) {
                SetupWindow.errorLabel.setColor(0,1,0,1);
                SetupWindow.errorLabel.setText("Verbindung erfolgreich");
                SetupWindow.Button_next.setDisabled(false);
                trytoconnect.setDisabled(true);
                SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().password = password.getText();
                SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().username = username.getText();
            }else{
                SetupWindow.Button_next.setDisabled(false);
                password.setDisabled(false);
                username.setDisabled(false);
                SetupWindow.errorLabel.setColor(1,0,0,1);
                SetupWindow.errorLabel.setText("Keine Verbindung möglich");
                trytoconnect.setDisabled(false);
                SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().password = password.getText();
                SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().username = username.getText();
            }
        }
    }
});


    }

    public static void close() {

        SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().password = password.getText();
        SetupWindow.tempverbindungsspeicher.getRaspberrypispeicher().username = username.getText();

    }
}
