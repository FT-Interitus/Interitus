package de.ft.interitus.UI.ManualConfig;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
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
import de.ft.interitus.Programm;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ManualConfigUI extends VisWindow {

    private static final int MAX_CONFIGURATIONS = 18;

    final VisTable container = new VisTable();
    final Padding padding = new Padding(2, 3);
    private RowLayout rowLayout;
    static TestBuilder testBuilder;
    private int SelectedItem;
    private EventListener listener;
    private VisTextButton create = new VisTextButton("Neu");
    private VisTextButton delete = new VisTextButton("Entfernen");
   private final VisTree tree = new VisTree();
    public ManualConfigUI() {
        super("");
        pack();
        setPosition(31, 35);
        new GridTableBuilder(4);

        delete.addListener(new ChangeListener() {



            @Override
            public void changed(ChangeEvent event, Actor actor) {


                    ProjectManager.getActProjectVar().deviceConfigurations.remove(SelectedItem);
                    SelectedItem = 0;
                    container.clearChildren();
                    RunConfigInformation.add(container);
                    rebuildtree();

            }
        });
        create.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!(tree.getNodes().size>=MAX_CONFIGURATIONS)) {
                    ProjectManager.getActProjectVar().deviceConfigurations.add(new DeviceConfiguration("New Config", new ArrayList<DeviceParameter>()));
                    SelectedItem = ProjectManager.getActProjectVar().deviceConfigurations.size() - 1;
                    container.clearChildren();
                    rebuildtree();
                    ProjectManager.getActProjectVar().projectType.getProjectFunktions().runconfigsettings(container, ProjectManager.getActProjectVar().deviceConfigurations.getLastObject());

                }else{
                    create.setDisabled(true);
                }
            }
        });


    }

    protected void updateNodeText(int ID,String name) {


        ((TestNode) this.tree.getNodes().get(ID)).getLabel().setText(name);

    }

    private void rebuildtree() {
        tree.clearChildren();
        int i=0;

        for(DeviceConfiguration configuration: ProjectManager.getActProjectVar().deviceConfigurations) {

            tree.add(new TestNode(new VisLabel(" "+configuration.getName()+" "),i));

            i++;
        }

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
        RunConfigInformation.add(container);

        testBuilder = new TestBuilder("Run Konfiguration", new StandardTableBuilder(padding));
        UIVar.isdialogeopend = true;

        UI.stage.addActor(testBuilder);


    }


    private class TestBuilder extends VisWindow {
        public TestBuilder(String name, final TableBuilder builder) {
            super(name);


            rowLayout = new RowLayout(new Padding(0, 0, 0, 5));


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
                        SelectedItem = ((TestNode) tree.getSelectedNode()).Mode;
                        container.clearChildren();
                        ProjectManager.getActProjectVar().projectType.getProjectFunktions().runconfigsettings(container,ProjectManager.getActProjectVar().deviceConfigurations.get(SelectedItem));

                    }catch (NullPointerException e) {
                        container.clearChildren();
                        RunConfigInformation.add(container);

                    }








                    //testBuilder.pack();

                }
            });


          add(create).expand().padLeft(-50).padTop(-530);
            add(delete).expand().padLeft(-120).padTop(-530);
            add(tree).fill().expand().padTop(50).padLeft(-360).width(140);
            //  builder.append(CellWidget.of(tree).padding(new Padding(15,15,0,0)).wrap());

            builder.append(CellWidget.of(new Separator()).fillY().padding(new Padding(0, -310, 0, 0)).wrap());


            builder.append(CellWidget.of(container).fillX().fillY().padding(new Padding(0,-50,0,0)).expandX().expandY().width(290).wrap());



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


                    if(tree.getSelectedNode()==null) {
                        delete.setDisabled(true);
                    }else{
                        delete.setDisabled(false);
                    }


                    if(tree.getNodes().size>=MAX_CONFIGURATIONS) {
                        create.setDisabled(true);
                    }else{
                        create.setDisabled(false);
                    }

                }
            }, 0, 100);


        }

        public boolean testopen() {
            if (!super.getParent().isVisible()) {
                UIVar.isdialogeopend = false;
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

    static class TestNode extends Tree.Node {
        private VisLabel label;
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







}
