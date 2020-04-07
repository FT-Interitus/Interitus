package de.ft.robocontrol.UI.setup.steps;

import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;
import de.ft.robocontrol.UI.setup.SetupWindow;

import java.sql.Time;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Step2 {

    public static CharSequence text = "Name: ";
    public static VisLabel namelable = new VisLabel(text);
    public static CharSequence auftragtext = "Step 2";
    public static VisLabel auftrag = new VisLabel(auftragtext);
    public static Timer time = new Timer();

    public Step2() {

    }

    public static void step2(VisTable builder) {
        builder.add(auftrag).expandX().padBottom(-100).row();
        builder.add(namelable).expandX().padBottom(-200).padLeft(-100);
        SetupWindow.errorLabel.setText("STep2");


    }


    public static void update() {



        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {



            }
        },0,30);

    }
}
