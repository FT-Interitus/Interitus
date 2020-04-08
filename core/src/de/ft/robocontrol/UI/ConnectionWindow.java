package de.ft.robocontrol.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.building.GridTableBuilder;
import com.kotcrab.vis.ui.building.OneRowTableBuilder;
import com.kotcrab.vis.ui.building.StandardTableBuilder;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.building.utilities.layouts.ActorLayout;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.data.VerbindungsSpeicher;
import de.ft.robocontrol.roboconnection.KnownDeviceManager;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConnectionWindow {
public static VisLabel error;
//ublic static TabbedPane tabbedPane;
public static VisTextButton devicemanagebutton = new VisTextButton("Software brennen");
    static TestTab tt;
    public static VisTextButton neuladen_button = new VisTextButton("Neuladen");
    public static VisSelectBox<String> selectportlist;
    public static VisSelectBox<String> selectboardlist;
    public static connectionbuilder testBuilder;
    final VisTable container = new VisTable();
    final Padding padding = new Padding(2, 3);
   public static TabbedPane.TabbedPaneStyle style = VisUI.getSkin().get(false ? "vertical" : "default", TabbedPane.TabbedPaneStyle.class);
   public static TabbedPane tabbedPane = new TabbedPane(style);

    public ConnectionWindow() {
        new GridTableBuilder(4);
        tabbedPane.addListener(new TabbedPaneAdapter() {


            @Override
            public void switchedTab(Tab tab) {
                container.clearChildren();
                container.add(tab.getContentTable()).expand().fill();


                try {
                    if (tab == tt) {

                        testBuilder.close();
                        KnownDeviceManager.addnewdevice();
                    }
                }catch (Exception e) {

                }
                try {
                    testBuilder.pack();
                } catch (Exception e) {

                }
            }
        });

        Var.isdialogeopend=true;

    }



    public static void update() {
        try {
            testBuilder.pack();
        } catch (NullPointerException e) { //kann passieren wenn das Fenster noch nicht initialisiert wurde
        }

    }


    public void show() {
        if(testBuilder==null) {
            testBuilder = new connectionbuilder("Verbindungen", new StandardTableBuilder(padding));
            testBuilder.pack();
        }
        UI.stage.addActor(testBuilder);
    }

    private class TestTab extends Tab {
        private String title;
        private Table content;

        public TestTab(String title) {
            super(false, false);
            this.title = title;

            VisList visList = new VisList();



            error = new VisLabel("");
            error.setColor(Color.RED);
            content = new VisTable();

            content.row();
            VisTable selectport = new VisTable(true);
            selectportlist = new VisSelectBox<String>();


            selectportlist.setItems("");
            VisTable selectboard = new VisTable(true);
            selectboardlist = new VisSelectBox<String>();
            selectboardlist.setItems("Arduino UNO", "Arduino MEGA");
            selectboard.pad(0, 30, 0, 0);
            content.add(neuladen_button).padRight(0);

            content.add(devicemanagebutton).padRight(-440);
            content.row();
            content.add(new VisLabel("Port:")).padLeft(30).padTop(50).padBottom(50);
            content.add(selectportlist).padTop(50).padBottom(50).padLeft(20);
            content.add(new VisLabel("Arduino:")).padLeft(20).padTop(50).padBottom(50);
            content.add(selectboardlist).padLeft(20).padRight(30).padBottom(50).padTop(50);
            content.row();
            content.add(error).padRight(-400);



        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public Table getContentTable() {
            return content;
        }
    }

    private class connectionbuilder extends VisWindow {
        public connectionbuilder(String name, TableBuilder builder) {
            super(name);


            setModal(true);
            closeOnEscape();
            addCloseButton();


            builder.setTablePadding(new Padding(0, 0, 0, 0));


             tt = new TestTab("+");
            tabbedPane.add(tt);
            tabbedPane.add(new Devicemanagmenttab("Test"));


            builder.append(CellWidget.of(tabbedPane.getTable()).expandX().fillX().wrap());
            builder.row();
            container.pack();
            builder.append(container);



            Table table = builder.build();

            add(table).expand().fill();
            //table.setSize(300,300);



            centerWindow();
            pack();
        }

        public void close() {
            super.close();
        }



    }

    private class RowLayout implements ActorLayout {
        private Padding padding;

        public RowLayout(Padding padding) {
            this.padding = padding;
        }

        @Override
        public Actor convertToActor(Actor... widgets) {
            return convertToActor(CellWidget.wrap(widgets));
        }

        @Override
        public Actor convertToActor(CellWidget<?>... widgets) {
            OneRowTableBuilder builder = new OneRowTableBuilder(padding);

            for (CellWidget<?> widget : widgets)
                builder.append(widget);

            return builder.build();
        }
    }
}
