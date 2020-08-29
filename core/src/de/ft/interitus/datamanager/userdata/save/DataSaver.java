/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager.userdata.save;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Var;
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

public class DataSaver {


    static public void save(final FileHandle handle) {


        Thread speichern = new Thread() {
            public void run() {


                ArrayList<SaveBlock> saveBlocks = BlockCalculator.save();


                String generateprojektname = "project" + System.currentTimeMillis();
                String generateprojektsettingsname = "projectsettings" + System.currentTimeMillis();
                String generaterunconfigurationen = "runconfig" + System.currentTimeMillis();


                try (FileOutputStream fos = new FileOutputStream(Data.tempfolder + "/" + generateprojektname);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(saveBlocks);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try (FileOutputStream fos = new FileOutputStream(Data.tempfolder + "/" + generaterunconfigurationen);
                     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(ProjectManager.getActProjectVar().deviceConfigurations);
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
                settings.put("it_version", Var.PROGRAMM_VERSION_ID); //Saves Interitus Version in Project File
                settings.put("pl_name",ProjectManager.getActProjectVar().projectType.getPluginRegister().getName());
                settings.put("pl_version",ProjectManager.getActProjectVar().projectType.getPluginRegister().getVersion());
                ArrayList<String> addons = new ArrayList<>();
                for(int i=0;i<ProjectManager.getActProjectVar().enabledAddons.size();i++) {
                    addons.add(ProjectManager.getActProjectVar().enabledAddons.get(i).getName());
                }
                settings.put("addons",addons);
                try {
                    Files.write(Paths.get(Data.tempfolder + "/" + generateprojektsettingsname), settings.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {


                    ArrayList<String> names = new ArrayList<>();
                    names.add("Program.itid");
                    names.add("Settings.itps");
                    names.add("RunConfig.itrc");
                    Zip.zipFiles(names, handle.file().getAbsolutePath(), Data.tempfolder + "/" + generateprojektname, Data.tempfolder + "/" + generateprojektsettingsname, Data.tempfolder + "/" + generaterunconfigurationen);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                File tempblockfile = new File(Data.tempfolder + "/" + generateprojektname);
                tempblockfile.delete();


                ProjectManager.getActProjectVar().changes = false;


            }


        };


        speichern.start();

    }
}
