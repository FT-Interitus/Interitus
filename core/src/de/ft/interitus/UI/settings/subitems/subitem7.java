package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Settings;

public class subitem7 {
    public static VisCheckBox tipps = new VisCheckBox("Tipps aktivieren");
    public static VisCheckBox personaltipps = new VisCheckBox("Personalisiere Tipps");
    public static void add(VisTable builder) {


        tipps.setChecked(Settings.hints);
        personaltipps.setChecked(Settings.personalhits);


        if(!tipps.isChecked()) {
            personaltipps.setDisabled(true);
        }else{
            personaltipps.setDisabled(false);
        }


        builder.add(new VisLabel("Personalisyte Tipps")).expandX().fillY();
    builder.row();
        builder.add(tipps).expandX().fillY().padTop(25);
        builder.row();
        builder.add(personaltipps).expandX().fillY().padTop(10);

        tipps.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {


                Settings.hints = tipps.isChecked();

                if(tipps.isChecked()) {
                    personaltipps.setDisabled(false);
                }else{
                    personaltipps.setDisabled(true);
                }

            }
        });
        personaltipps.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.personalhits = personaltipps.isChecked();
            }
        });

        //TODO hier toggle für tipps
    }
}
