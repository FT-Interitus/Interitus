package de.ft.robocontrol.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.building.GridTableBuilder;
import com.kotcrab.vis.ui.building.OneRowTableBuilder;
import com.kotcrab.vis.ui.building.StandardTableBuilder;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.building.utilities.layouts.ActorLayout;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.*;
import de.ft.robocontrol.Settings;

public class SettingsUI extends VisWindow {

    public static VisTextField updateurlfield;

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
        public TestBuilder (String name, TableBuilder builder) {
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
                public void changed (ChangeEvent event, Actor actor) {


                    if(darktoggle.isChecked()) {
                        Settings.darkmode = true;
                    }else{
                        Settings.darkmode = false;
                    }
                }
            });

             updateurlfield = new VisTextField(Settings.updateurl);
            updateurlfield.setDisabled(true);


            updateurlfield.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

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

            builder.setTablePadding(new Padding(20,30,20,30));

            builder.append(darktoggle);
           builder.row();
           builder.append(new VisLabel(""));
           builder.row();

            builder.append(rowLayout,CellWidget.builder().fillX().expandX().expandX(),CellWidget.of(updateurlfield).expandX().fillX().wrap());
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

                builder.append(CellWidget.of(new Separator()).fillX().wrap());
                builder.row();

                builder.append(new VisLabel("second part"));
                builder.row();

                builder.append(new VisLabel("sliders"));
                builder.append(rowLayout, getSlider(false), getSlider(false), getSlider(false), getSlider(true));
                builder.row();

                builder.append(rowLayout, getCheckBoxArray(8));
            }

 */

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
