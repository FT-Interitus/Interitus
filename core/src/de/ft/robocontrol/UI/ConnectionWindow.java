package de.ft.robocontrol.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.building.GridTableBuilder;
import com.kotcrab.vis.ui.building.OneRowTableBuilder;
import com.kotcrab.vis.ui.building.StandardTableBuilder;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.building.utilities.layouts.ActorLayout;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;
import de.ft.robocontrol.Settings;
import de.ft.robocontrol.data.VerbindungsSpeicher;

public class ConnectionWindow extends VisWindow {
public static VisLabel error;
//ublic static TabbedPane tabbedPane;
public static VisTextButton devicemanagebutton = new VisTextButton("Software brennen");

    public static VisTextButton neuladen_button = new VisTextButton("Neuladen");


  public static VisSelectBox<String> selectportlist;
  public static VisSelectBox<String> selectboardlist;
  public static TestBuilder testBuilder;

    TabbedPane.TabbedPaneStyle style = VisUI.getSkin().get(false ? "vertical" : "default", TabbedPane.TabbedPaneStyle.class);
    TabbedPane tabbedPane = new TabbedPane(style);
    final VisTable container = new VisTable();

    final Padding padding = new Padding(2, 3);
  public void verbindungstabs(){
        for(int i=0;i<VerbindungsSpeicher.verbundungen.size();i++){
            tabbedPane.insert(0,new devicemanagmenttab(VerbindungsSpeicher.verbundungen.get(i).name));
        }
    }
    public static void update () {
      try {
          testBuilder.pack();
      }catch (NullPointerException e) { //kann passieren wenn das Fenster noch nicht initialisiert wurde

      }

    }

    public ConnectionWindow() {

        super("Verbindungen");
        pack();
        setPosition(31, 35);

        setVisible(false);
        new GridTableBuilder(4);

        tabbedPane.addListener(new TabbedPaneAdapter() {
            @Override
            public void switchedTab(Tab tab) {
                container.clearChildren();
                container.add(tab.getContentTable()).expand().fill();
                try {
                    testBuilder.pack();
                }catch (Exception e) {

                }
            }
        });


    }


    public void show() {
      testBuilder = new TestBuilder("Verbindungen", new StandardTableBuilder(padding));
        UI.stage.addActor(testBuilder);
    }




    private class TestBuilder extends VisWindow {
        public TestBuilder (String name, TableBuilder builder) {
            super(name);



          RowLayout rowLayout = new RowLayout(new Padding(0, 0, 0, 5));



            setModal(true);
            closeOnEscape();
            addCloseButton();
            // setScale(200,200);





            ActorLayout layout = new ActorLayout() {
                @Override
                public Actor convertToActor(Actor... widgets) {
                    return null;
                }

                @Override
                public Actor convertToActor(CellWidget<?>... widgets) {
                    return null;
                }
            };


            //    layout.convertToActor(new CreateTab());


            builder.setTablePadding(new Padding(0,0,0,0));


            devicemanagmenttab devtest = new devicemanagmenttab("TestDevice");
           TestTab tt = new TestTab("+");

            tabbedPane.add(devtest);
        tabbedPane.add(tt);


        builder.append(CellWidget.of(tabbedPane.getTable()).expandX().fillX().wrap());
        builder.row();

            builder.append(container);



            Table table = builder.build();
            add(table).expand().fill();

            pack();
            centerWindow();
        }

        private VisSlider getSlider (boolean vertical) {
            VisSlider slider = new VisSlider(0, 100, 1, vertical);
            slider.setValue(MathUtils.random(20, 80));
            return slider;
        }

        private VisCheckBox[] getCheckBoxArray (int count) {
            VisCheckBox[] array = new VisCheckBox[count];

            for (int i = 0; i < count; i++)
                array[i] = new VisCheckBox("check");

            return array;
        }
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
        public String getTabTitle () {
            return title;
        }

        @Override
        public Table getContentTable () {
            return content;
        }
    }


    private class RowLayout implements ActorLayout {
        private Padding padding;

        public RowLayout (Padding padding) {
            this.padding = padding;
        }

        @Override
        public Actor convertToActor (Actor... widgets) {
            return convertToActor(CellWidget.wrap(widgets));
        }

        @Override
        public Actor convertToActor (CellWidget<?>... widgets) {
            OneRowTableBuilder builder = new OneRowTableBuilder(padding);

            for (CellWidget<?> widget : widgets)
                builder.append(widget);

            return builder.build();
        }
    }
}
