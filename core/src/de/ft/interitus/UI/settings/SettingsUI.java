/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.settings;

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
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.settings.subitems.*;
import de.ft.interitus.Var;
import de.ft.interitus.plugin.ProgramRegistry;

import java.util.Timer;
import java.util.TimerTask;


public class SettingsUI extends VisWindow {


    public static RowLayout rowLayout;

    public static TestBuilder testBuilder;
    public static int SelectedItem = -1;
    final VisTable container = new VisTable();
    final Padding padding = new Padding(2, 3);
    ChangeListener listener = null;

    public SettingsUI() {
        super("Einstellungen");
        pack();
        setPosition(31, 35);


        new GridTableBuilder(4);
    }

    /***
     * -1 = Informationen
     *
     * 0 = Aussehen
     * 1 = Theme
     * 2 = Grafik
     * 3 = item 1.3
     *
     * 4 = Verhalten
     * 5 = Tastenkombinationen
     * 6 = Tipps
     * 7 = item 2.3
     *
     * 8 = Programme Einstellungen
     * 9 = Daten
     * 10 = item
     * 11 = Erweitert
     *
     * 12 Netzwerk
     * 13 Interitus Mobil
     * 14 Better Togetter
     * 15 Erweitert
     *
     * 16 = Plugin Store
     * 17.. Plguin
     *
     */


    public static boolean isopend() {
        try {

            return testBuilder.testopen();
        } catch (Exception e) {
            return false;
        }

    }

    public void show() {

        container.clearChildren();
        Instructions.add(container);

        testBuilder = new TestBuilder("Einstellungen", new StandardTableBuilder(padding));
        UIVar.isdialogeopend = true;

        UI.stage.addActor(testBuilder);


    }

    public static class TestNode extends Tree.Node {
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


            builder.setTablePadding(new Padding(20, 30, 20, 30));


            final VisTree tree = new VisTree();
            TestNode item1 = new TestNode(new VisLabel(" Aussehen "), 0);
            TestNode item2 = new TestNode(new VisLabel(" Verhalten "), 4);
            TestNode item3 = new TestNode(new VisLabel(" Programm Einstellungen "), 8);
            TestNode item4 = new TestNode(new VisLabel(" Netzwerk "), 12);
            TestNode item5;
            item5 = new TestNode(new VisLabel(" Plugins"), 16);


            item1.add(new TestNode(new VisLabel(" Theme"), 1));
            item1.add(new TestNode(new VisLabel(" Grafik"), 2));
            item1.add(new TestNode(new VisLabel(" item 1.3"), 3));

            item2.add(new TestNode(new VisLabel(" Tastenkombinationen"), 5));
            item2.add(new TestNode(new VisLabel(" Tipps"), 6));
            item2.add(new TestNode(new VisLabel(" Blöcke"), 7));

            item3.add(new TestNode(new VisLabel(" Daten"), 9));
            item3.add(new TestNode(new VisLabel(" Performance"), 10));
            item3.add(new TestNode(new VisLabel(" Erweitert"), 11));

            item4.add(new TestNode(new VisLabel(" Interitus Mobil"), 13));
            item4.add(new TestNode(new VisLabel(" Better Together"), 14));
            item4.add(new TestNode(new VisLabel(" Erweitert"), 15));
            //ADD Advanced Settings to ITM if Device is connect

            ProgramRegistry.addSettings(item5);

            item1.setExpanded(true);
            item2.setExpanded(true);
            item3.setExpanded(true);
            item4.setExpanded(true);

            tree.add(item1);
            tree.add(item2);
            tree.add(item3);
            tree.add(item4);
            tree.add(item5);


            tree.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {


                    SettingsUI.SelectedItem = ((TestNode) tree.getSelectedNode()).Mode;


                    container.clearChildren();

                    if (SelectedItem != 5 && Var.disableshortcuts) {
                        Var.disableshortcuts = false;
                    }

                    switch (SelectedItem) {


                        case -1:
                            Instructions.add(container);

                        case 0:
                            subitem1.add(container);
                            break;

                        case 1:
                            subitem2.add(container);

                            break;
                        case 2:

                            subitem3.add(container);

                            break;
                        case 3:
                            subitem4.add(container);
                            break;
                        case 4:
                            subitem5.add(container);
                            break;
                        case 5:
                            subitem6.add(container);
                            break;
                        case 6:
                            subitem7.add(container);
                            break;
                        case 7:
                            subitem8.add(container);
                            break;
                        case 8:
                            subitem9.add(container);
                            break;
                        case 9:
                            subitem10.add(container);
                            break;
                        case 10:
                            subitem11.add(container);
                            break;
                        case 11:
                            subitem12.add(container);
                            break;
                        case 12:
                            subitem13.add(container);
                            break;
                        case 13:
                            subitem14.add(container);
                            break;
                        case 14:
                            subitem15.add(container);
                            break;
                        case 15:
                            subitem16.add(container);
                            break;
                        case 16:


                            break;
                        default:

                            if (SelectedItem > 16) {
                                ProgramRegistry.getSettingsContainer(container, SelectedItem - 17);
                            }


                            break;


                    }


                    //testBuilder.pack();

                }
            });


            add(tree).expand().fill().padTop(15).padLeft(15).width(-10);
            //  builder.append(CellWidget.of(tree).padding(new Padding(15,15,0,0)).wrap());

            builder.append(CellWidget.of(new Separator()).fillY().padding(new Padding(0, -17, 0, 0)).wrap());


            builder.append(CellWidget.of(container).fillX().fillY().expandX().expandY().width(270).wrap());


/*
            builder.append(CellWidget.of(new Separator()).fillX().wrap());
            builder.row();
            builder.append(new VisLabel("Gefährliche Einstellungen"));
            builder.append(rowLayout, CellWidget.builder().padding(new Padding(0, 20, 0, 0)).wrap(), CellWidget.of(enabledangeroussettings).wrap());
            builder.row();

            builder.append(rowLayout, CellWidget.builder().fillX().expandX().expandX().width(500), CellWidget.of(updateurlfield).expandX().fillX().wrap());
            */

            builder.row();


            Table table = builder.build();
            add(table).expand().fill();
            sizeBy(600, 450);

            centerWindow();

            Timer time = new Timer();
            time.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    if (UIVar.isdialogeopend && !SettingsUI.isopend()) {
                        UIVar.isdialogeopend = false;
                        Var.disableshortcuts = false;
                        this.cancel();
                    }

                    if (!UIVar.isdialogeopend && SettingsUI.isopend()) {
                        UIVar.isdialogeopend = true;
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

}
