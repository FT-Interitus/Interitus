/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.compiler.Interitus.EV3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Program;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.compiler.Compiler;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.deviceconnection.ev3connection.Device;
import de.ft.interitus.deviceconnection.ev3connection.Ev3SystemUtils;
import de.ft.interitus.deviceconnection.ev3connection.SystemOperations;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.Ev3Block;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3.programmsequence.Thread.ThreadBlock;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.OSChecker;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class EV3compiler implements Compiler {
    private static Notification notification;
    private int userthreadcounter = 0;

    public void vmthread(StringBuilder sb, String threadName, String... insert) {
        sb.append("\nvmthread " + threadName + "\n{\n");
        for (int i = 0; i < insert.length; i++) {
            sb.append("  " + insert[i] + "\n");
        }
        sb.append("\n}");
    }

    public void userthread(StringBuilder sb, String... insert) {
        vmthread(sb, "userthread" + userthreadcounter, insert);
        userthreadcounter++;
    }

    public StringBuilder compileSketch() {
        userthreadcounter = 0;
        Program.logger.config("Compile ev3");
        StringBuilder Programm = new StringBuilder();

        EV3Thread MainThread = new EV3Thread("Main");
        ArrayList<EV3Thread> userthreads = new ArrayList<>();

        for (Block block : ProjectManager.getActProjectVar().blocks) {
            if (block.getBlocktype() instanceof ThreadBlock) {
                Block neighbour = block.getRight();
                EV3Thread tempThread = null;
                if (neighbour != null) {
                    tempThread = new EV3Thread("UserThreadNr" + (userthreads.size() + 1));
                }
                while (neighbour != null) {
                    //Program.logger.config(((Ev3Block) neighbour.getBlocktype().getBlockModis().get(neighbour.getBlocktype().getActBlockModiIndex())).getCode());
                    MainThread.addLine("OBJECT_START(UserThreadNr" + (userthreads.size() + 1) + ")");
                    tempThread.addLine(((Ev3Block) neighbour.getBlocktype().getBlockModis().get(neighbour.getBlocktype().getActBlockModiIndex())).getCode());
                    userthreads.add(tempThread);
                    neighbour = neighbour.getRight();
                }
                    MainThread.addLine("OBJECT_START(UserThreadNr" + (userthreads.size() + 1) + ")");
                    userthreads.add(tempThread);


            }
        }}

        Programm.append(MainThread.getThread());
        for (EV3Thread thread : userthreads) {
            Programm.append(thread.getThread());
        }

        Program.logger.config("\n" + Programm.toString());
        return Programm;
    }

    @Override
    public String compile() {

        return compileSketch().toString();
    }

    @Override
    public boolean compileandrun() {
        notification = new Notification(AssetLoader.arduinomegaimage, "Umwandlung...", "\nProjekt wird in Code umgewandelt").setCloseable(false).setAlivetime(9000).rollin(false);
        notification.setProgressbarvalue(0);
        NotificationManager.sendNotification(notification);


        String filename = UUID.randomUUID().toString().replace("-", "") + ".lms";

        FileHandle fh = Gdx.files.absolute(Data.tempfolder.getAbsolutePath() + "/" + filename);

        notification.setTitle("Compilieren...");
        notification.setMessage("Projekt wird in ein\nausführbares Programm compiliert");
        notification.setProgressbarvalue(30);


        String program = compile();
        fh.writeString(program, false);


        String java = "";
        if (OSChecker.isWindows()) {

            java = System.getProperty("java.home") + "/bin/java.exe";

        } else if (OSChecker.isUnix()) {
            java = System.getProperty("java.home") + "/bin/java";

        } else if (OSChecker.isMac()) {

            java = System.getProperty("java.home") + "/bin/java";

        }


        try {

            ProcessBuilder pb = new ProcessBuilder(java, "-jar","assembler.jar", Data.tempfolder.getAbsolutePath() + "/" + filename.replace(".lms", ""));
            pb.directory(new File(System.getProperty("user.dir") + "/" + "libs/ev3/"));


            final Process process = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = in.readLine()) != null) {
                Program.logger.config(line);
            }
            BufferedReader in1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line1 = null;
            while((line1 = in1.readLine()) != null) {
                Program.logger.config(line1);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        notification.setTitle("Hochladen...");
        notification.setMessage("\nProjekt wird hochgeladen");
        notification.setProgressbarvalue(70);

        try {
            Ev3SystemUtils.Delete_File("/home/root/lms2012/prjs/"+ProjectManager.getActProjectVar().getFilename(), ((Device) UI.runselection.getSelectedElement().getIdentifier()));
        } catch (Exception e) {
            Program.logger.config("First Time Deploy");
        }
        notification.setProgressbarvalue(80);

        try {
            Ev3SystemUtils.create_Dir("/home/root/lms2012/prjs/"+ProjectManager.getActProjectVar().getFilename(), ((Device) UI.runselection.getSelectedElement().getIdentifier()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        notification.setProgressbarvalue(100);

        try {
            Ev3SystemUtils.downloadFile("/home/root/lms2012/prjs/"+ProjectManager.getActProjectVar().getFilename()+"/"+ProjectManager.getActProjectVar().getFilename()+".rbf",Gdx.files.absolute(Data.tempfolder.getAbsolutePath() + "/" + filename.replace(".lms", ".rbf")).readString(), ((Device) UI.runselection.getSelectedElement().getIdentifier()));
        } catch (Exception e) {
            e.printStackTrace();
        }



        notification.close();

        return true;
    }

    @Override
    public String getCompilerVersion() {
        return null;
    }

    @Override
    public void interupt() {

    }
}
