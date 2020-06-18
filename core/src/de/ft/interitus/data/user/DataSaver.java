package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.data.programm.Data;
import de.ft.interitus.projecttypes.ProjectManager;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataSaver {


    static public void save(final FileHandle handle) {


        Thread speichern = new Thread() {
            public void run() {


                ArrayList<SaveBlock> saveBlocks = new ArrayList<>();

                for (int i = 0; i < ProjectManager.getActProjectVar().blocks.size(); i++) {
                    saveBlocks.add(ProjectManager.getActProjectVar().blocks.get(i).getBlocktoSaveGenerator().generate(ProjectManager.getActProjectVar().blocks.get(i)));
                }


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
                settings.put("type",ProjectManager.getActProjectVar().projectType.getName());
                settings.put("zoom", ProjectManager.getActProjectVar().zoom);
                settings.put("pos_x",ProjectManager.getActProjectVar().cam_pos.x);
                settings.put("pos_y",ProjectManager.getActProjectVar().cam_pos.y);
                try {
                    Files.write(Paths.get(Data.tempfolder + "/" + generateprojektsettingsname), settings.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {


                    ArrayList<String> names = new ArrayList<>();
                    names.add("Program.itid");
                    names.add("Settings.itps");
                    Zip.zipFiles(names, handle.file().getAbsolutePath(), Data.tempfolder + "/" + generateprojektname,Data.tempfolder+"/"+generateprojektsettingsname);
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
