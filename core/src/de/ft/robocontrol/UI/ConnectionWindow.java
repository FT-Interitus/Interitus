package de.ft.robocontrol.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;
import de.ft.robocontrol.Var;

public class ConnectionWindow extends VisWindow {
public static VisLabel error;
public static TabbedPane tabbedPane;
public static VisTextButton devicemanagebutton = new VisTextButton("Software brennen");
  public static VisSelectBox<String> selectportlist;
  public static VisSelectBox<String> selectboardlist;

    public ConnectionWindow() {
        super("Verbindungen");
        setPosition(310, 350);
      //  TableUtils.setSpacingDefaults(this);
       // centerWindow();
        setResizable(false);
        pack();
        centerWindow();

        addCloseButton();
        closeOnEscape();

        final VisTable container = new VisTable();
        container.pack();

        TabbedPane.TabbedPaneStyle style = VisUI.getSkin().get(false ? "vertical" : "default", TabbedPane.TabbedPaneStyle.class);
        TabbedPane tabbedPane = new TabbedPane(style);
        tabbedPane.addListener(new TabbedPaneAdapter() {
            @Override
            public void switchedTab (Tab tab) {
                container.clearChildren();
                container.add(tab.getContentTable()).expand().fill();
            }
        });
        tabbedPane.add(new TestTab("Gerät hinzufügen"));




            add(tabbedPane.getTable()).expandX().fillX();
            row();
            add(container).expand().fill();

        setModal(true);
        closeOnEscape();

       // setSize(300, 200);
     //   centerWindow();

pack();

    }



    private static class TestTab extends Tab {
        private String title;
        private Table content;

        public TestTab (String title) {
            super(false, false);
            this.title = title;

            VisList visList = new VisList();



            error = new VisLabel("");
            error.setColor(Color.RED);
            content = new VisTable();

            content.row();
            VisTable selectport = new VisTable(true);
           selectportlist  = new VisSelectBox<String>();




            selectportlist.setItems("");
            VisTable selectboard = new VisTable(true);
            selectboardlist = new VisSelectBox<String>();
            selectboardlist.setItems("Arduino UNO", "Arduino MEGA");
            selectboard.pad(0,30,0,0);

            content.add(devicemanagebutton).padRight(-655);
            content.row();
            content.add(new VisLabel("Port:")).padLeft(30).padTop(50).padBottom(50);
            content.add(selectportlist).padTop(50).padBottom(50).padLeft(20);
            content.add(new VisLabel("Arduino:")).padLeft(20).padTop(50).padBottom(50);
            content.add(selectboardlist).padLeft(20).padRight(30).padBottom(50).padTop(50);
           content.row();
           content.add(error).padRight(-400);



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
