package de.ft.robocontrol.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;

public class ConnectionWindow extends VisWindow {

static TabbedPane tabbedPane;

    public ConnectionWindow() {
        super("Test");

        TableUtils.setSpacingDefaults(this);

        setResizable(true);
        addCloseButton();
        closeOnEscape();

        final VisTable container = new VisTable();

        TabbedPane.TabbedPaneStyle style = VisUI.getSkin().get(false ? "vertical" : "default", TabbedPane.TabbedPaneStyle.class);
        TabbedPane tabbedPane = new TabbedPane(style);
        tabbedPane.addListener(new TabbedPaneAdapter() {
            @Override
            public void switchedTab (Tab tab) {
                container.clearChildren();
                container.add(tab.getContentTable()).expand().fill();
            }
        });
        tabbedPane.add(new TestTab("tab1"));
        System.out.println("Hier");

            add(tabbedPane.getTable()).expandX().fillX();
            row();
            add(container).expand().fill();

        setSize(300, 200);
        centerWindow();

    }

    private static class TestTab extends Tab {
        private String title;
        private Table content;

        public TestTab (String title) {
            super(false, false);
            this.title = title;

            content = new VisTable();
            content.add(new VisLabel(title));
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

}
