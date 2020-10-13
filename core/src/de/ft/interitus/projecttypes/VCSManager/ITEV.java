/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.VCSManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Block.Saving.SaveBlockV1;
import de.ft.interitus.datamanager.BlockCalculator;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.userdata.Zip;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ITEV {
    public static void saverevision(String changes, String author) {


        Thread speichern = new Thread() {
            public void run() {


                ArrayList<SaveBlockV1> saveBlocks = BlockCalculator.save(ProjectManager.getActProjectVar());


                String generateprojektname = "project" + System.currentTimeMillis();
                String generateprojektsettingsname = "projectsettings" + System.currentTimeMillis();


                try (FileOutputStream fos = new FileOutputStream(Data.tempfolder + "/" + generateprojektname);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(saveBlocks);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                JSONObject settings = new JSONObject();
                settings.put("vcs", ProjectManager.getActProjectVar().vcs);
                settings.put("type", ProjectManager.getActProjectVar().projectType.getName());
                settings.put("zoom", ProjectManager.getActProjectVar().zoom);
                settings.put("pos_x", ProjectManager.getActProjectVar().cam_pos.x);
                settings.put("pos_y", ProjectManager.getActProjectVar().cam_pos.y);
                settings.put("time", ProjectManager.getActProjectVar().programmingtime + (System.currentTimeMillis() - ProjectManager.getActProjectVar().currentstarttime));
                try {
                    Files.write(Paths.get(Data.tempfolder + "/" + generateprojektsettingsname), settings.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {


                    ArrayList<String> names = new ArrayList<>();
                    names.add("Program.itid");
                    names.add("Settings.itps");
                    FileHandle handle = Gdx.files.external(ProjectManager.getActProjectVar().path);
                    Zip.zipFiles(names, handle.file().getAbsolutePath(), Data.tempfolder + "/" + generateprojektname, Data.tempfolder + "/" + generateprojektsettingsname);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                File tempblockfile = new File(Data.tempfolder + "/" + generateprojektname);
                tempblockfile.delete();


            }


        };


        speichern.start();

    }
}
