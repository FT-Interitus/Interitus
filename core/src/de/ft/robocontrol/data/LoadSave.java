package de.ft.robocontrol.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.utils.JSONParaser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import static de.ft.robocontrol.MainGame.saver;

public class LoadSave {
    public static void saveas() {
        Thread save = new Thread() {
            @Override
            public void run() {


                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Speichern unter...");

                int userSelection = fileChooser.showSaveDialog(saver);
                fileChooser.setMultiSelectionEnabled(false);


                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());

                    if(fileToSave.getAbsolutePath().contains(".rac")) {
                        Var.path = fileToSave.getAbsolutePath();
                        JSONParaser.writerarray(Gdx.files.absolute(fileToSave.getAbsolutePath()));

                    }else{
                        Var.path = fileToSave.getAbsolutePath()+".rac";
                        JSONParaser.writerarray(Gdx.files.absolute(fileToSave.getAbsolutePath()+".rac"));
                    }
                    System.out.println(Var.path);
                }
            };
            //SAVE DATA



        };

        save.start();
    }


    public static void open() {
        Thread open = new Thread() {
            @Override
            public void run() {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                fileChooser.setFileFilter(new FileNameExtensionFilter("Projektdatei (.rac)", "rac"));
                int result = fileChooser.showOpenDialog(MainGame.saver);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    FileHandle handle = Gdx.files.internal(selectedFile.getAbsolutePath());
                    Var.path = selectedFile.getAbsolutePath();
                    JSONParaser.load(handle);

                }
            }
        } ;
        open.start();
    }
}
