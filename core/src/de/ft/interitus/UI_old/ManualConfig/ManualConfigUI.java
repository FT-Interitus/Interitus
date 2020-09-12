/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI_old.ManualConfig;

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
import de.ft.interitus.UI_old.UI;
import de.ft.interitus.UI_old.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class ManualConfigUI extends VisWindow {


    public static final int MAX_NAME_LENGTH = 14;
    private static final int MAX_CONFIGURATIONS = 18;
    static TestBuilder testBuilder;
    final VisTable container = new VisTable();
    final Padding padding = new Padding(2, 3);
    private final VisTextButton create = new VisTextButton("Neu");
    private final VisTextButton delete = new VisTextButton("Entfernen");
    private final VisTree tree = new VisTree();
    private int SelectedItem;

    public ManualConfigUI() {
        super("");
        pack();
        setPosition(31, 35);
        new GridTableBuilder(4);

        delete.addListener(new ChangeListener() {


            @Override
            public void changed(ChangeEvent event, Actor actor) {


                Objects.requireNonNull(ProjectManager.getActProjectVar()).deviceConfigurations.remove(SelectedItem);
                SelectedItem = 0;
                container.clearChildren();
                RunConfigInformation.add(container);
                rebuildtree();

            }
        });
        create.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!(tree.getNodes().size >= MAX_CONFIGURATIONS)) {
                    Objects.requireNonNull(ProjectManager.getActProjectVar()).deviceConfigurations.add(new DeviceConfiguration(DeviceConfiguration.DEFAULT_NAME, new ArrayList<>()));
                    ProjectManager.getActProjectVar().projectType.getProjectFunktions().changedrunconfig();
                    SelectedItem = ProjectManager.getActProjectVar().deviceConfigurations.size() - 1;
                    container.clearChildren();
                    rebuildtree();
                    ProjectManager.getActProjectVar().projectType.getProjectFunktions().runconfigsettings(container, ProjectManager.getActProjectVar().deviceConfigurations.getLastObject());

                } else {
                    create.setDisabled(true);
                }
            }
        });


    }

    public static boolean isopend() {
        try {

            return testBuilder.testopen();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean updateNodeText(int ID, String name) {


        if (name.length() > MAX_NAME_LENGTH) {
            return false;
        }

        ((TestNode) this.tree.getNodes().get(ID)).getLabel().setText(name);


        return true;
    }

    private void rebuildtree() {
        tree.clearChildren();
        int i = 0;

        for (DeviceConfiguration configuration : ProjectManager.getActProjectVar().deviceConfigurations) {

            tree.add(new TestNode(new VisLabel(" " + configuration.getName() + " "), i));
            i++;
        }

    }

    public void show() {
        rebuildtree();
        container.clearChildren();
        if (ProjectManager.getActProjectVar().deviceConfigurations.size() > 0) {
            ProjectManager.getActProjectVar().projectType.getProjectFunktions().runconfigsettings(container, ProjectManager.getActProjectVar().deviceConfigurations.get(0));

        } else {
            RunConfigInformation.add(container);
        }


        testBuilder = new TestBuilder("Run Konfiguration", new StandardTableBuilder(padding));
        UIVar.isdialogeopend = true;

        UI.stage.addActor(testBuilder);


    }

    private static class RowLayout implements ActorLayout {
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

    static class TestNode extends Tree.Node {
        private final VisLabel label;
        public int Mode;

        public TestNode(Actor actor, int Mode) {
            super(actor);
            label = (VisLabel) actor;
            this.Mode = Mode;
        }

        public VisLabel getLabel() {
            return label;
        }


    }

    private class TestBuilder extends VisWindow {
        public TestBuilder(String name, final TableBuilder builder) {
            super(name);


            RowLayout rowLayout = new RowLayout(new Padding(0, 0, 0, 5));


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


            builder.setTablePadding(new Padding(20, 30, 20, 30));


            tree.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {


                    try {
                        if (tree.getSelectedNode() != null) {
                            if (SelectedItem != ((TestNode) tree.getSelectedNode()).Mode) {
                                ProjectManager.getActProjectVar().projectType.getProjectFunktions().changedrunconfig();
                            }
                            SelectedItem = ((TestNode) tree.getSelectedNode()).Mode;

                            container.clearChildren();
                            ProjectManager.getActProjectVar().projectType.getProjectFunktions().runconfigsettings(container, ProjectManager.getActProjectVar().deviceConfigurations.get(SelectedItem));

                        } else {
                            container.clearChildren();

                        }
                    } catch (NullPointerException e) {
                        container.clearChildren();
                        RunConfigInformation.add(container);
                        e.printStackTrace();

                    }


                    //testBuilder.pack();

                }
            });


            add(create).expand().padLeft(-50).padTop(-530);
            add(delete).expand().padLeft(-120).padTop(-530);
            add(tree).fill().expand().padTop(50).padLeft(-360).width(140);

            builder.append(CellWidget.of(new Separator()).fillY().padding(new Padding(0, -310, 0, 0)).wrap());


            builder.append(CellWidget.of(container).fillX().fillY().padding(new Padding(0, -50, 0, 0)).expandX().expandY().width(290).wrap());


            builder.row();


            Table table = builder.build();
            add(table).expand().fill();
            sizeBy(600, 450);

            centerWindow();

            Timer time = new Timer();
            time.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    if (UIVar.isdialogeopend && !ManualConfigUI.isopend()) {
                        UIVar.isdialogeopend = false;
                        Var.disableshortcuts = false;
                        this.cancel();
                    }

                    if (!UIVar.isdialogeopend && ManualConfigUI.isopend()) {
                        UIVar.isdialogeopend = true;
                    }


                    delete.setDisabled(tree.getSelectedNode() == null);


                    create.setDisabled(tree.getNodes().size >= MAX_CONFIGURATIONS);

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


    }


}
