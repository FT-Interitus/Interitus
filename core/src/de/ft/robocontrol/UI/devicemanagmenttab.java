package de.ft.robocontrol.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisList;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;

public class devicemanagmenttab extends Tab {
    private String title;
    private Table content;

    public devicemanagmenttab(String title) {
        super(false, false);
        this.title = title;

        VisList visList = new VisList();





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