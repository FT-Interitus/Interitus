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
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.settings.subitems.*;

public class SettingsUI extends VisWindow {

    public static VisTextField updateurlfield;
   public static RowLayout rowLayout;
   public static VisCheckBox darktoggle;
   public static TestBuilder testBuilder;
    final VisTable container = new VisTable();
    public static int SelectedItem = -1;
    /***
     * -1 = Informationen
     *
     * 0 = Aussehen
     * 1 = Theme
     * 2 = item 1.2
     * 3 = item 1.3
     *
     * 4 = Verhalten
     * 5 = Tastenkombinationen
     * 6 = Tipps
     * 7 = item 2.3
     *
     * 8 = Programme Einstellungen
     * 9 = Daten
     * 10 = item 3.2
     * 11 = item 3.3
     *
     */


    public static boolean isopend() {
        try {

            return testBuilder.testopen();
        }catch (Exception e) {
            return false;
        }
    }

    private static boolean accepteddangerous = false;
    final Padding padding = new Padding(2, 3);

    public SettingsUI() {
        super("Einstellungen");
        pack();
        setPosition(31, 35);


        new GridTableBuilder(4);
    }

    public void show() {

        container.clearChildren();
        instructions.add(container);
        testBuilder = new TestBuilder("Einstellungen", new StandardTableBuilder(padding));
        UI.stage.addActor(testBuilder);




    }


    private class TestBuilder extends VisWindow {
        public boolean testopen() {
            return super.getParent().isVisible();


        }

        public TestBuilder(String name, final TableBuilder builder) {
            super(name);



           rowLayout  = new RowLayout(new Padding(0, 0, 0, 5));


            setModal(true);
            closeOnEscape();
            addCloseButton();

            addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {


                }
            });


            // setScale(200,200);


           darktoggle = new VisCheckBox("Schaltet den Dark-Mode an und aus");
            darktoggle.setChecked(Settings.darkmode);
            darktoggle.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {


                    Settings.darkmode = darktoggle.isChecked();
                }
            });

            updateurlfield = new VisTextField(Settings.updateurl);
            updateurlfield.setDisabled(true);


            updateurlfield.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    Settings.updateurl = updateurlfield.getText();
                }
            });


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

            final VisCheckBox enabledangeroussettings;
            enabledangeroussettings = new VisCheckBox("Freischalten");
            enabledangeroussettings.setChecked(false);
            enabledangeroussettings.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    if (!enabledangeroussettings.isChecked()) {

                        updateurlfield.setDisabled(true);
                    } else {

                        if (accepteddangerous) {
                            enabledangeroussettings.setChecked(true);
                            updateurlfield.setDisabled(false);
                        } else {
                            String[] möglichkeiten = {"Trotzdem fortfahren", "Abbrechen"};


                            final int nothing = 1;
                            final int everything = 2;


                            //confirmdialog may return result of any type, here we are just using ints
                            Dialogs.showConfirmDialog(UI.stage, "Kritische Einstellungen", "\nWenn du die Update-URL falsch änderst, kann es passieren, das das Programm sich nicht mehr updatet.\n Änderen musst du in der Regel nur etwas, falls du die Anweisung per Mail bekommen hast.\n",
                                    möglichkeiten, new Integer[]{nothing, everything},
                                    new ConfirmDialogListener<Integer>() {
                                        @Override
                                        public void result(Integer result) {
                                            if (result == nothing) {


                                                accepteddangerous = true;
                                                updateurlfield.setDisabled(false);

                                            }

                                            if (result == everything) {

                                                enabledangeroussettings.setChecked(false);

                                            }


                                        }
                                    });
                        }
                    }

                }
            });

            final VisTree tree = new VisTree();
            TestNode item1 = new TestNode(new VisLabel(" Aussehen "),0);
            TestNode item2 = new TestNode(new VisLabel(" Verhalten "),4);
            TestNode item3 = new TestNode(new VisLabel(" Programm Einstellungen "),8);

            item1.add(new TestNode(new VisLabel(" Theme"),1));
            item1.add(new TestNode(new VisLabel(" item 1.2"),2));
            item1.add(new TestNode(new VisLabel(" item 1.3"),3));

            item2.add(new TestNode(new VisLabel(" Tastenkombinationen "),5));
            item2.add(new TestNode(new VisLabel(" Tipps"),6));
            item2.add(new TestNode(new VisLabel(" item 2.3"),7));

            item3.add(new TestNode(new VisLabel(" Daten"),9));
            item3.add(new TestNode(new VisLabel(" item 3.2"),10));
            item3.add(new TestNode(new VisLabel(" item 3.3"),11));

            item1.setExpanded(true);

            tree.add(item1);
            tree.add(item2);
            tree.add(item3);


            tree.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {



              SettingsUI.SelectedItem =  ((TestNode) tree.getSelectedNode()).Mode;


                    container.clearChildren();


                    switch (SelectedItem) {


                        case -1:
                            instructions.add(container);

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
                        default:
                            break;





                    }


                //testBuilder.pack();

                }
            });


           add(tree).expand().fill().padTop(15).padLeft(15).width(-10);
          //  builder.append(CellWidget.of(tree).padding(new Padding(15,15,0,0)).wrap());

            builder.append(CellWidget.of(new Separator()).fillY().padding(new Padding(0,-17,0,0)).wrap());



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
            sizeBy(600,450);

            centerWindow();
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

    static class TestNode extends Tree.Node {
        public VisLabel label;
        public int Mode;
        public TestNode (Actor actor, int Mode) {
            super(actor);
            label = (VisLabel) actor;
            this.Mode = Mode;
        }



    }

}
