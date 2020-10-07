/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager.userdata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.Program;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.userdata.load.DataLoader;
import de.ft.interitus.datamanager.userdata.save.DataSaver;
import de.ft.interitus.projecttypes.ProjectManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import static de.ft.interitus.ProgramingSpace.saver;

public class UserInteractDataManagerDialog {
    static Thread open;
    static Thread save;

    public static void saveas() {
        save = new Thread() {
            @Override
            public void run() {

                try {


                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Speichern unter...");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Projektdatei (.itp)", "itp"));

                    int userSelection = fileChooser.showSaveDialog(saver);
                    fileChooser.setMultiSelectionEnabled(false);


                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        Program.logger.config("Save as file: " + fileToSave.getName());


                        if (fileToSave.getAbsolutePath().contains(".itp")) {
                            ProjectManager.getActProjectVar().path = fileToSave.getAbsolutePath();
                            DataSaver.save(Gdx.files.absolute(fileToSave.getAbsolutePath()));
                            ProjectManager.getActProjectVar().setFilename(fileToSave.getName());

                        } else {
                            ProjectManager.getActProjectVar().path = fileToSave.getAbsolutePath() + ".itp";
                            DataSaver.save(Gdx.files.absolute(fileToSave.getAbsolutePath() + ".itp"));
                            ProjectManager.getActProjectVar().setFilename(fileToSave.getName() + "");
                        }
                        Program.logger.config(ProjectManager.getActProjectVar().path);

                        if (Data.filename.size() > 9) {
                            Data.filename.remove(0);

                        }

                        if (Data.filename.indexOf(fileToSave.getName()) > -1) {
                            int temp = Data.filename.indexOf(fileToSave.getName());
                            Data.filename.remove(temp);
                            Data.path.remove(temp);
                        }


                        Data.filename.add(fileToSave.getName());
                        Data.path.add(fileToSave.getAbsolutePath());

                        UI.tabbar.getTabbs().get(Var.openprojectindex).getTabButton().setText(fileToSave.getName());

                        //  DataManager.saved();


                    }
                } catch (Exception e) {
                    e.printStackTrace();//for debug to find errors
                    DisplayErrors.error = e;
                }
            }

            //SAVE DATA


        };

        save.start();
    }


    public static void open() {
        open = new Thread() {
            @Override
            public void run() {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileFilter(new FileNameExtensionFilter("Projektdatei (.itp)", "itp"));
                int result = fileChooser.showOpenDialog(ProgramingSpace.saver);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    Program.logger.config("Selected file: " + selectedFile.getName());
                    FileHandle handle = Gdx.files.internal(selectedFile.getAbsolutePath());

                    DataLoader.load(handle, selectedFile.getName(), selectedFile.getAbsolutePath());

                    //  DataManager.saved();


                    if (Data.filename.size() > 9) {
                        Data.filename.remove(0);

                    }
                    if (Data.path.indexOf(selectedFile.getAbsolutePath()) > -1) {
                        int temp = Data.filename.indexOf(selectedFile.getName());
                        Data.filename.remove(temp);
                        Data.path.remove(temp);
                    }


                    Data.filename.add(selectedFile.getName());
                    Data.path.add(selectedFile.getAbsolutePath());

                }
            }
        };
        open.start();
    }

    /***
     *
     * @return a boolean if the open dialoge is opened
     */

    public static boolean isopenopen() {

        try {
            return open.isAlive();
        } catch (Exception e) {
            return false;
        }
    }

    /***
     *
     * @return a boolean if the save dialoge is opened
     */

    public static boolean issaveopen() {

        try {
            return save.isAlive();
        } catch (Exception e) {
            return false;
        }
    }

}
