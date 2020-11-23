/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

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
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.projectsettings.subitems.AddonSettings;
import de.ft.interitus.UI.projectsettings.subitems.Information;
import de.ft.interitus.UI.projectsettings.subitems.VersionControll;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.ProjectManager;

import java.util.Timer;
import java.util.TimerTask;

public class ProjectSettingsUI extends VisWindow {
    public static RowLayout rowLayout;
    public static TestBuilder testBuilder;
    public static int SelectedItem = 0;
    private final VisTable container = new VisTable();
    private final Padding padding = new Padding(2, 3);
    private VisTree tree;

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
        Information.add(container);

        testBuilder = new TestBuilder("Projekt-Einstellungen", new StandardTableBuilder(padding));
        UIVar.isdialogeopend = true;

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


            rowLayout = new RowLayout(new Padding(0, 0, 0, 5));


            setModal(true);
            closeOnEscape();
            addCloseButton();

            tree = new VisTree();
            rebuildTree();

            tree.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    if(tree.getSelectedNode()==null) return;


                        SelectedItem = ((TestNode) tree.getSelectedNode()).Mode;


                    container.clearChildren();

                    if (SelectedItem != 5 && Var.disableshortcuts) {
                        Var.disableshortcuts = false;
                    }

                    switch (SelectedItem) {
                        case 0 -> Information.add(container);
                        case 2 -> VersionControll.add(container);
                        case 1 -> ProjectManager.getActProjectVar().projectType.getProjectFunktions().projectsettings(container,ProjectManager.getActProjectVar().projectSettings);
                        case 3 -> AddonSettings.add(container);
                        default -> ProjectManager.getActProjectVar().enabledAddons.get(SelectedItem-4).getAddonSettings(container); // FIXME: 23.11.20 Crash!!!
                    }

                }
            });


            //    layout.convertToActor(new CreateTab());


            builder.setTablePadding(new Padding(20, 30, 20, 30));


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
                    if (UIVar.isdialogeopend && !isopend()) {
                        UIVar.isdialogeopend = false;
                        Var.disableshortcuts = false;
                        this.cancel();
                    }

                    if (!UIVar.isdialogeopend && isopend()) {
                        UIVar.isdialogeopend = true;
                    }
                }
            }, 0, 100);


        }

        public boolean testopen() {
            if (!super.getParent().isVisible()) {
                UIVar.isdialogeopend = false;
                Var.disableshortcuts = false;
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

    public void rebuildTree() {


       tree.clearChildren();

        TestNode item1 = new TestNode(new VisLabel(" Informationen "), 0);
        TestNode item2 = new TestNode(new VisLabel(" Einstellungen "),1);
        TestNode item3 = new TestNode(new VisLabel(" VCS "), 2);
        TestNode item4 = new TestNode(new VisLabel(" Addons "), 3);



        //ADD Advanced Settings to ITM if Device is connect


        item1.setExpanded(true);
        item2.setExpanded(true);
        item3.setExpanded(true);


        tree.add(item1);
        tree.add(item2);
        tree.add(item3);
        tree.add(item4);


        int counter = 0;
        for(Addon addon: ProjectManager.getActProjectVar().enabledAddons) {
            counter++;
            tree.add(new TestNode(new VisLabel(" " + addon.getName() + " "),counter+3));
        }




    }
}
