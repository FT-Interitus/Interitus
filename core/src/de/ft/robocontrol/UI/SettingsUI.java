package de.ft.robocontrol.UI;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
import de.ft.robocontrol.Settings;

public class SettingsUI extends VisWindow {

    public static VisTextField updateurlfield;


    private static boolean accepteddangerous = false;
    final Padding padding = new Padding(2, 3);

    public SettingsUI() {
        super("Einstellungen");
        pack();
        setPosition(31, 35);


        new GridTableBuilder(4);
    }

    public void show() {
        UI.stage.addActor(new TestBuilder("Einstellungen", new StandardTableBuilder(padding)));
    }


    private class TestBuilder extends VisWindow {
        public TestBuilder(String name, TableBuilder builder) {
            super(name);


            RowLayout rowLayout = new RowLayout(new Padding(0, 0, 0, 5));


            setModal(true);
            closeOnEscape();
            addCloseButton();
            // setScale(200,200);


            final VisCheckBox darktoggle = new VisCheckBox("Schaltet den Dark-Mode an und aus");
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


            builder.append(rowLayout, CellWidget.builder().fillX().expandX().wrap(), CellWidget.of(darktoggle).expandX().fillY().wrap());
            builder.row();
            builder.append(CellWidget.of(new Separator()).fillX().wrap());
            builder.row();
            builder.append(new VisLabel("Gefährliche Einstellungen"));
            builder.append(rowLayout, CellWidget.builder().padding(new Padding(0, 20, 0, 0)).wrap(), CellWidget.of(enabledangeroussettings).wrap());
            builder.row();

            builder.append(rowLayout, CellWidget.builder().fillX().expandX().expandX().width(500), CellWidget.of(updateurlfield).expandX().fillX().wrap());
            builder.row();



/*
            builder.append(new VisLabel("title label"));
            builder.row();

            builder.append(new VisLabel("path"));
            builder.append(rowLayout, CellWidget.builder().fillX(),
                    CellWidget.of(new VisTextField()).expandX().fillX().wrap(),
                    CellWidget.of(new VisTextButton("choose")).padding(new Padding(0, 0)).wrap());
            builder.row();

            builder.append(new VisLabel("name"));
            builder.append(CellWidget.of(new VisTextField()).expandX().fillX().wrap());
            builder.row();

            builder.append(new VisLabel("description"));
            builder.append(CellWidget.of(new VisTextField()).fillX().wrap());
            builder.row();



            //rest of content won't fit on screen with OneRowTableBuilder
            if (builder instanceof OneRowTableBuilder == false) {
                builder.append(new VisLabel("checkboxes"));
                builder.append(rowLayout, getCheckBoxArray(5));
                builder.row();


                builder.row();

                builder.append(new VisLabel("second part"));
                builder.row();

                builder.append(new VisLabel("sliders"));
              //  builder.append(rowLayout, getSlider(false), getSlider(false), getSlider(false), getSlider(true));
                builder.row();

              //  builder.append(rowLayout, getCheckBoxArray(8));
            }

*/

            Table table = builder.build();
            add(table).expand().fill();

            pack();
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

}
