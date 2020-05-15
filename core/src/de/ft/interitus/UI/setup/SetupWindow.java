package de.ft.interitus.UI.setup;

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
import de.ft.interitus.Block.Devices;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.setup.steps.ArduinoSteps.Step3;
import de.ft.interitus.UI.setup.steps.generalSteps.Step1;
import de.ft.interitus.UI.setup.steps.generalSteps.Step2;
import de.ft.interitus.data.VerbindungsSpeicher;

public class SetupWindow {
    public static VerbindungsSpeicher tempverbindungsspeicher = new VerbindungsSpeicher();
    public static int currentStep = 1;
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
        } catch (Exception e) {
            return false;
        }
    }


    public void show() {
        tempverbindungsspeicher = new VerbindungsSpeicher();
        if (setupBuilder == null) {
            content = new VisTable();
            setupBuilder = new SetupBuilder("Verbindungs Setup", new StandardTableBuilder(padding));
            Step1.step1(content);
            setupBuilder.pack();
        } else {
            content.clearChildren();
            Step1.step1(content);
            setupBuilder.pack();
        }

        UI.stage.addActor(setupBuilder);
    }


    private class SetupBuilder extends VisWindow {
        public SetupBuilder(String name, final TableBuilder builder) {
            super(name);


            setModal(true);

            setLayoutEnabled(true);
            builder.setTablePadding(new Padding(20, 30, 20, 30));

            builder.append(CellWidget.of(content).fillX().fillY().expandX().expandY().wrap());
            builder.row();


            Button_previouse.setDisabled(true);


            VisTable buttonTable = new VisTable(true);
            buttonTable.add(errorLabel).fillX().width(60).pad(350, 0, 0, 300);
            buttonTable.add(Button_cancle).fillX().width(60).pad(350, 0, 0, 0);
            buttonTable.add(Button_previouse).fillX().width(80).pad(350, 0, 0, 0);
            buttonTable.add(Button_next).fillX().width(50).pad(350, 0, 0, 0);


            builder.append(buttonTable);


            Table table = builder.build();

            add(table).expand().fill().size(600, 450);


            centerWindow();
            pack();


            //TODO add here the same Listener as in new Project

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
                    if (currentStep > 2) {
                        if (tempverbindungsspeicher.getDevice() == Devices.ARDUINO_MEGA || tempverbindungsspeicher.getDevice() == Devices.ARDUINO_UNO) {
                            switch (currentStep) {
                                case 3:
                                    Step3.close();
                                    break;

                            }
                        }


                    }

                    if (currentStep > 2) {
                        if (tempverbindungsspeicher.getDevice() == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPI4 || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPIZEROW) {
                            switch (currentStep) {
                                case 3:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step3.close();
                                    break;
                                case 4:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step4.close();
                                    break;
                                case 5:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step5.close();

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


                    if (currentStep > 2) {
                        if (tempverbindungsspeicher.getDevice() == Devices.ARDUINO_MEGA || tempverbindungsspeicher.getDevice() == Devices.ARDUINO_UNO) {
                            switch (currentStep) {
                                case 3:
                                    Step3.step3(content);
                                    break;

                            }
                        }

                        if (tempverbindungsspeicher.getDevice() == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPI4 || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPIZEROW) {


                            switch (currentStep) {
                                case 3:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step3.step3(content);
                                    break;
                                case 4:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step4.step4(content);
                                    break;
                                case 5:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step5.step5(content);
                                    break;
                            }


                        }
                    }


                    if (currentStep > 1) {
                        if (tempverbindungsspeicher.getDevice() == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPI4 || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPIZEROW) {


                        } else if (currentStep != 5) {
                            Button_previouse.setDisabled(false);
                        }
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

                    if (currentStep > 2) {
                        if (tempverbindungsspeicher.getDevice() == Devices.ARDUINO_MEGA || tempverbindungsspeicher.getDevice() == Devices.ARDUINO_UNO) {
                            switch (currentStep) {
                                case 3:
                                    Step3.close();
                                    break;

                            }
                        }

                        if (tempverbindungsspeicher.getDevice() == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPI4 || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPIZEROW) {
                            switch (currentStep) {
                                case 3:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step3.close();
                                    break;
                                case 4:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step4.close();
                                    Button_previouse.setDisabled(true); //Wenn die installation startet darf der nutzer nicht mehr zurückgehen
                                    break;
                                case 5:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step5.close();
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

                    if (currentStep > 2) {
                        if (tempverbindungsspeicher.getDevice() == Devices.ARDUINO_MEGA || tempverbindungsspeicher.getDevice() == Devices.ARDUINO_UNO) {
                            switch (currentStep) {
                                case 3:
                                    Step3.step3(content);
                                    break;

                            }
                        }
                        if (tempverbindungsspeicher.getDevice() == Devices.RASPBERRY3Bplus || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPI4 || tempverbindungsspeicher.getDevice() == Devices.RASPBERRYPIZEROW) {

                            switch (currentStep) {
                                case 3:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step3.step3(content);
                                    break;
                                case 4:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step4.step4(content);
                                    break;
                                case 5:
                                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step5.step5(content);
                                    Button_previouse.setDisabled(true);  //Wenn die installation startet darf der nutzer nicht mehr zurückgehen
                                    break;
                            }


                        }

                    }


                    if (currentStep == 1) {
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
                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step3.close();
                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step4.close();
                    de.ft.interitus.UI.setup.steps.RaspberryPiSteps.Step5.close();

                    currentStep = 1;
                    tempverbindungsspeicher = null;
                    setupBuilder.close();
                }
            });


        }


        public boolean testopen() {
            return super.getParent().isVisible();
        }
    }


}
