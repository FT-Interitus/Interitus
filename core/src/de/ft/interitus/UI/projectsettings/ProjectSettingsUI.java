package de.ft.interitus.UI.projectsettings;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.projectsettings.subitems.Informations;
import de.ft.interitus.UI.projectsettings.subitems.Settings;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.UI.settings.subitems.*;
import de.ft.interitus.Var;
import de.ft.interitus.plugin.PluginGateway;
import de.ft.interitus.plugin.PluginManagerHandler;

import java.util.Timer;
import java.util.TimerTask;

public class ProjectSettingsUI extends VisWindow {
    public static RowLayout rowLayout;
    private  VisTree tree;
    public static TestBuilder testBuilder;
    public static int SelectedItem = 0;

    final VisTable container = new VisTable();
    final Padding padding = new Padding(2, 3);
    ChangeListener listener = null;

    public ProjectSettingsUI() {
        super("Projekt-Einstellungen");
        pack();
        setPosition(31, 35);


        new GridTableBuilder(4);
    }



    public static boolean isopend() {
        try {

            return testBuilder.testopen();
        } catch (Exception e) {
            return false;
        }
    }

    public void show() {

        container.clearChildren();
        Informations.add(container);

        testBuilder = new  TestBuilder("Projekt-Einstellungen", new StandardTableBuilder(padding));
        Var.isdialogeopend=true;

        UI.stage.addActor(testBuilder);


    }

    static class TestNode extends Tree.Node {
        public VisLabel label;
        public int Mode;

        public TestNode(Actor actor, int Mode) {
            super(actor);
            label = (VisLabel) actor;
            this.Mode = Mode;
        }


    }

    private class TestBuilder extends VisWindow {
        public TestBuilder(String name, final TableBuilder builder) {
            super(name);


            rowLayout = new  RowLayout(new Padding(0, 0, 0, 5));


            setModal(true);
            closeOnEscape();
            addCloseButton();






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


            builder.setTablePadding(new Padding(20, 30, 20, 30));

        tree = new VisTree();

            TestNode item1 = new  TestNode(new VisLabel(" Informationen "), 0);
            TestNode item2 = new  TestNode(new VisLabel(" Einstellungen "), 1);

            //ADD Advanced Settings to ITM if Device is connect



            item1.setExpanded(true);
            item2.setExpanded(true);


            tree.add(item1);
            tree.add(item2);



            tree.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {


                     SelectedItem = (( TestNode) tree.getSelectedNode()).Mode;


                    container.clearChildren();

                    if(SelectedItem!=5&&Var.disableshortcuts) {
                        Var.disableshortcuts = false;
                    }

                    switch (SelectedItem) {


                        case 0:
                            Informations.add(container);
                            break;

                        case 1:
                            Settings.add(container);
                            break;
                    }

                }
            });


            add(tree).expand().fill().padTop(15).padLeft(15).width(-10);
            //  builder.append(CellWidget.of(tree).padding(new Padding(15,15,0,0)).wrap());

            builder.append(CellWidget.of(new Separator()).fillY().padding(new Padding(0, -17, 0, 0)).wrap());


            builder.append(CellWidget.of(container).fillX().fillY().expandX().expandY().width(270).wrap());


            builder.row();


            Table table = builder.build();
            add(table).expand().fill();
            sizeBy(500, 300);

            centerWindow();

            Timer time = new Timer();
            time.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    if(Var.isdialogeopend&&! isopend())  {
                        Var.isdialogeopend = false;
                        Var.disableshortcuts = false;
                        this.cancel();
                    }

                    if(!Var.isdialogeopend&& isopend()) {
                        Var.isdialogeopend = true;
                    }
                }
            },0,100);



        }

        public boolean testopen() {
            if(!super.getParent().isVisible()) {
                Var.isdialogeopend = false;
                Var.disableshortcuts = false;
                this.removeListener(listener);
            }
            return super.getParent().isVisible();


        }

        private VisSlider getSlider(boolean vertical) {
            VisSlider slider = new VisSlider(0, 100, 1, vertical);
            slider.setValue(MathUtils.random(20, 80));
            return slider;
        }

        private VisCheckBox[] getCheckBoxArray(int count) {
            VisCheckBox[] array = new VisCheckBox[count];

            for (int i = 0; i < count; i++)
                array[i] = new VisCheckBox("check");

            return array;
        }
    }

    private class RowLayout implements ActorLayout {
        private final Padding padding;

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
