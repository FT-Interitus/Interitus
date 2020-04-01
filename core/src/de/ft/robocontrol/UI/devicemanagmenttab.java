package de.ft.robocontrol.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.util.form.SimpleFormValidator;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static com.badlogic.gdx.scenes.scene2d.ui.Cell.defaults;


public class devicemanagmenttab extends Tab {
    private String title;
    private Table content;

    public void TestFormValidator () {


        content.defaults().padRight(1);
        defaults().padLeft(1);

        VisTextButton cancelButton = new VisTextButton("cancel");
        VisTextButton acceptButton = new VisTextButton("accept");

        VisValidatableTextField Name = new VisValidatableTextField();


        VisLabel errorLabel = new VisLabel();
        errorLabel.setColor(Color.RED);

        VisTable buttonTable = new VisTable(true);
        buttonTable.add(errorLabel).expand().fill();
        buttonTable.add(cancelButton);
        buttonTable.add(acceptButton);

        content.add(new VisLabel("Name: "));
        content.add(Name).expand().fill();
        content.row();




        content.pack();
        content.setSize(content.getWidth() + 60, content.getHeight());
        content.setPosition(548, 85);
    }


    public devicemanagmenttab(String title) {
        super(false, false);
        this.title = title;

        VisList visList = new VisList();
        content = new VisTable();
       VisTextButton button = new VisTextButton("button");
       TestFormValidator();
        content.add(button).padRight(0);
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