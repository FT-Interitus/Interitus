package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSelectBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.spinner.ArraySpinnerModel;
import com.kotcrab.vis.ui.widget.spinner.Spinner;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Settings;

import java.util.ArrayList;

public class subitem3 {
    public static void add(VisTable builder) {
        builder.add(new VisLabel("Grafik Settings")).expandX().fillY();
        builder.row();


        final VisCheckBox checkBox = new VisCheckBox("VSync aktivieren", Settings.Vsync);
          final VisSelectBox<String> limitfps = new VisSelectBox<String>();

        final Array<String> stringArray = new Array<>();

        stringArray.add("20");
        stringArray.add("30");
        stringArray.add("40");
        stringArray.add("50");
        stringArray.add("60");
        stringArray.add("90");
        stringArray.add("120");
        stringArray.add("240");
        stringArray.add("Unlimited");
        if(Gdx.input.isKeyPressed(Input.Keys.E)||Settings.limitfps<=5&&Settings.limitfps!=0) { //Easteregg
            stringArray.add("5");
        }
        limitfps.setItems( stringArray.toArray());

        if(Settings.limitfps==0) {
            limitfps.setSelected("Unlimited");
        }else {
            limitfps.setSelected(String.valueOf(Settings.limitfps));
        }

       builder.add(checkBox).padTop(30);
        builder.row();
        builder.add(limitfps).padTop(30);
        limitfps.setDisabled(checkBox.isChecked());

checkBox.addListener(new ChangeListener() {
    @Override
    public void changed(ChangeEvent event, Actor actor) {
        Settings.Vsync = checkBox.isChecked();
        Gdx.graphics.setVSync(Settings.Vsync);
        limitfps.setDisabled(checkBox.isChecked());
    }
});

limitfps.addListener(new ChangeListener() {
    @Override
    public void changed(ChangeEvent event, Actor actor) {
          switch (limitfps.getSelected()) {
              case "20":
                  Settings.limitfps = 20;
                  break;
              case "30":
                  Settings.limitfps = 30;
                  break;
              case "40":
                  Settings.limitfps = 40;
                  break;
              case "50":
                  Settings.limitfps = 50;
                  break;
              case "60":
                  Settings.limitfps = 60;
                  break;
              case "90":
                  Settings.limitfps = 90;
                  break;
              case "120":
                  Settings.limitfps = 120;
                  break;
              case "240":
                  Settings.limitfps = 240;
                  break;
              case "Unlimited":
                  Settings.limitfps = 0;
                  break;
              case "5": //Easteregg
                  Settings.limitfps = 5;
                  break;



          }
    }
});



    }
}
