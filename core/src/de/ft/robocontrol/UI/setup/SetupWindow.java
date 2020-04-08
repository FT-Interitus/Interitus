package de.ft.robocontrol.UI.setup;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.building.GridTableBuilder;
import com.kotcrab.vis.ui.building.StandardTableBuilder;
import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import de.ft.robocontrol.Block.Devices;
import de.ft.robocontrol.UI.UI;
import de.ft.robocontrol.UI.setup.steps.ArduinoSteps.Step3;
import de.ft.robocontrol.UI.setup.steps.generalSteps.Step1;
import de.ft.robocontrol.UI.setup.steps.generalSteps.Step2;
import de.ft.robocontrol.data.VerbindungsSpeicher;

public class SetupWindow {
    public static VerbindungsSpeicher tempverbindungsspeicher=new VerbindungsSpeicher();
    public static int currentStep=1;
    public static SetupBuilder setupBuilder;
    public static VisTextButton Button_next = new VisTextButton("Next");
    public static VisTextButton Button_previouse = new VisTextButton("Previouse");
    public static VisTextButton Button_cancle = new VisTextButton("Cancel");
    public static VisLabel errorLabel = new VisLabel("error");
    public static VisTable content;
    final Padding padding = new Padding(2, 3);

    public SetupWindow() {

        new GridTableBuilder(4);
    }


    public static void update() {
        try {
            setupBuilder.pack();
        } catch (NullPointerException e) { //kann passieren wenn das Fenster noch nicht initialisiert wurde
        }

    }

    public static boolean isopend() {
        try {
            return setupBuilder.testopen();
        }catch (Exception e) {
            return false;
        }
    }


    public void show() {
        tempverbindungsspeicher=new VerbindungsSpeicher();
        if (setupBuilder == null) {
            content = new VisTable();
            setupBuilder = new SetupBuilder("Verbindungs Setup", new StandardTableBuilder(padding));
                Step1.step1(content);
            setupBuilder.pack();
        }else{
            content.clearChildren();
            Step1.step1(content);
            setupBuilder.pack();
        }

        UI.stage.addActor(setupBuilder);
    }


    private class SetupBuilder extends VisWindow {
        public SetupBuilder(String name, TableBuilder builder) {
            super(name);


            setModal(true);

            setLayoutEnabled(true);
            builder.setTablePadding(new Padding(20, 30, 20, 30));

            builder.append(CellWidget.of(content).fillX().fillY().expandX().expandY().wrap());
            builder.row();



            Button_previouse.setDisabled(true);



            VisTable buttonTable = new VisTable(true);
            buttonTable.add(errorLabel).fillX().width(60).pad(350,0,0,300);
            buttonTable.add(Button_cancle).fillX().width(60).pad(350,0,0,0);
            buttonTable.add(Button_previouse).fillX().width(80).pad(350,0,0,0);
            buttonTable.add(Button_next).fillX().width(50).pad(350,0,0,0);



            builder.append(buttonTable);



            Table table = builder.build();

            add(table).expand().fill().size(600,450);


            centerWindow();
            pack();



            Button_next.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    switch (currentStep) {
                        case 1:
                            Step1.close();
                            break;
                        case 2:
                            Step2.close();
                            break;

                    }
if(currentStep>2) {
    if (tempverbindungsspeicher.device == Devices.ARDUINO_MEGA || tempverbindungsspeicher.device == Devices.ARDUINO_UNO) {
        switch (currentStep) {
            case 3:
                Step3.close();
                break;

        }
    }




}

                    if(currentStep>2) {
                        if (tempverbindungsspeicher.device == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.device == Devices.RASPBERRYPI4 || tempverbindungsspeicher.device == Devices.RASPBERRYPIZEROW ) {
                            switch (currentStep) {
                                case 3:
                                    de.ft.robocontrol.UI.setup.steps.RaspberryPiSteps.Step3.close();
                                    break;

                            }
                        }
                    }


                    currentStep++;
                    content.clearChildren();
                    switch (currentStep) {
                        case 1:
                            Step1.step1(content);
                            break;
                        case 2:
                            Step2.step2(content);
                            break;

                    }


                    if(currentStep>2) {
                        if (tempverbindungsspeicher.device == Devices.ARDUINO_MEGA || tempverbindungsspeicher.device == Devices.ARDUINO_UNO) {
                            switch (currentStep) {
                                case 3:
                                    Step3.step3(content);
                                    break;

                            }
                        }

                        if (tempverbindungsspeicher.device == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.device == Devices.RASPBERRYPI4 || tempverbindungsspeicher.device == Devices.RASPBERRYPIZEROW) {


                            switch (currentStep)  {
                                case 3:
                                    de.ft.robocontrol.UI.setup.steps.RaspberryPiSteps.Step3.step3(content);
                                    break;
                            }


                        }
                    }


                    if(currentStep>1) {
    Button_previouse.setDisabled(false);
}

                }


            });

            Button_previouse.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    switch (currentStep) {
                        case 1:
                            Step1.close();
                            break;
                        case 2:
                            Step2.close();
                            break;

                    }

                    if(currentStep>2) {
                        if (tempverbindungsspeicher.device == Devices.ARDUINO_MEGA || tempverbindungsspeicher.device == Devices.ARDUINO_UNO) {
                            switch (currentStep) {
                                case 3:
                                    Step3.close();
                                    break;

                            }
                        }

                        if (tempverbindungsspeicher.device == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.device == Devices.RASPBERRYPI4 || tempverbindungsspeicher.device == Devices.RASPBERRYPIZEROW) {
                            switch (currentStep) {
                                case 3:
                                    de.ft.robocontrol.UI.setup.steps.RaspberryPiSteps.Step3.close();
                                    break;
                            }

                        }
                    }


                    currentStep--;
                    content.clearChildren();
                    switch (currentStep) {
                        case 1:
                            Step1.step1(content);
                            break;
                        case 2:
                            Step2.step2(content);
                            break;

                    }

                    if(currentStep>2) {
                        if (tempverbindungsspeicher.device == Devices.ARDUINO_MEGA || tempverbindungsspeicher.device == Devices.ARDUINO_UNO) {
                            switch (currentStep) {
                                case 3:
                                    Step3.step3(content);
                                    break;

                            }
                        }
                        if (tempverbindungsspeicher.device == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.device == Devices.RASPBERRYPI4 || tempverbindungsspeicher.device == Devices.RASPBERRYPIZEROW) {

                            switch (currentStep) {
                                case 3:
                                    de.ft.robocontrol.UI.setup.steps.RaspberryPiSteps.Step3.step3(content);
                                    break;
                            }


                        }

                        }


                    if(currentStep==1) {
                        Button_previouse.setDisabled(true);
                    }
                }
            });

            Button_cancle.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {


                    Step1.close();
                    Step2.close();
                    Step3.close();
                    de.ft.robocontrol.UI.setup.steps.RaspberryPiSteps.Step3.close();

                    currentStep=1;
                    tempverbindungsspeicher=null;
                    setupBuilder.close();
                }
            });


        }


        public boolean testopen() {
            return super.getParent().isVisible();
        }
    }


}
