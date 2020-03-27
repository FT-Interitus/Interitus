package de.ft.robocontrol.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;

public class CreateTab extends Tab {
    Table content = new Table();

    public CreateTab() {
        super(false, false); //tab is not savable, tab is closeable by user

        content.add(new VisLabel("Some widget"));
        //content.add(...)
    }

    @Override
    public String getTabTitle () {
        return "Test Tab";
    }

    @Override
    public Table getContentTable () {
        return content;
    }
}