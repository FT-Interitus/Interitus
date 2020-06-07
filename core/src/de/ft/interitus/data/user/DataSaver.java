package de.ft.interitus.data.user;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.BlockVar;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.Var;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DataSaver {



    static public void save(final FileHandle handle) {


        Thread speichern = new Thread() {
            public void run() {


                ArrayList<SaveBlock> saveBlocks = new ArrayList<>();

                for(int i=0;i< BlockVar.blocks.size();i++) {
                saveBlocks.add(    BlockVar.blocks.get(i).getBlocktoSaveGenerator().generate(BlockVar.blocks.get(i)));
                }


                try (FileOutputStream fos = new FileOutputStream (handle.file());
                     ObjectOutputStream oos = new ObjectOutputStream (fos)) {
                    oos.writeObject (saveBlocks);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try {
                    Zip.zipFiles(System.getProperty("user.home")+"/blub.zip",handle.file());
                } catch (IOException e) {
                    e.printStackTrace();
                }






                /*
                try {


                    FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+"/compressed.zip");
                    ZipOutputStream zipOut = new ZipOutputStream(fos);
                    File fileToZip = handle.file();
                    FileInputStream fis = new FileInputStream(fileToZip);
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zipOut.putNextEntry(zipEntry);
                    byte[] bytes = new byte[1024];
                    int length;
                    while((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                    zipOut.close();
                    fis.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/




            }






        };


        speichern.start();

    }
}
