package de.ft.robocontrol.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.FocusManager;
import com.kotcrab.vis.ui.util.InputValidator;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.util.dialog.OptionDialogAdapter;
import com.kotcrab.vis.ui.util.form.SimpleFormValidator;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;

import javax.swing.event.ChangeEvent;

import java.util.Timer;
import java.util.TimerTask;

import static com.badlogic.gdx.scenes.scene2d.ui.Cell.defaults;


public class Devicemanagmenttab extends Tab {
    private String title;
    private Table content;

    public void TestFormValidator(final Devicemanagmenttab devicemanagmenttab) {


        content.defaults().padRight(1);
        defaults().padLeft(1);

        VisTextButton cancelButton = new VisTextButton("cancel");
        VisTextButton acceptButton = new VisTextButton("accept");

        final VisValidatableTextField Name = new VisValidatableTextField();


        final VisLabel errorLabel = new VisLabel();
        errorLabel.setColor(Color.RED);

        VisTable buttonTable = new VisTable(true);
        buttonTable.add(errorLabel).expand().fill();
        buttonTable.add(cancelButton);
        buttonTable.add(acceptButton);


        VisLabel device = new VisLabel();
        device.setText("Name:");
        Name.setText(getTabTitle());
        Name.addValidator(new InputValidator() {

            @Override
            public boolean validateInput(String input) {

                if(input.length()>14) {
                    return false;
                }else{
                    return true;
                }
            }
        });

        Name.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               // devicemanagmenttab.set
            }
        });
        Name.isInputValid();




        content.add(device).padLeft(-299);
        content.add(Name).expand().fill().padLeft(-120);
        content.row();





        content.pack();
        content.setSize(content.getWidth() + 60, content.getHeight());
        content.setPosition(548, 85);

    }


    public Devicemanagmenttab(String title) {
        super(false, false);
        this.title = title;

        VisList visList = new VisList();
        content = new VisTable();
    //   VisTextButton button = new VisTextButton("button");
       TestFormValidator(this);
       // content.add(button).padRight(0);

    }

    @Override
    public String getTabTitle () {
        return title;
    }

    @Override
    public Table getContentTable () {
        return content;
    }
}