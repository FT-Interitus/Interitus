package de.ft.interitus.compiler.Arduino;

import de.ft.interitus.Block.Block;
import de.ft.interitus.Programm;
import de.ft.interitus.compiler.Compiler;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.BlockTypes.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.ProjectTypes;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class ArduinoCompiler implements Compiler {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    @Override
    public String compile() {

        return compilesketch();
    }

    @Override
    public boolean compileandrun() {
        compileandrun(getBoards().getJSONObject(0));
        return true;
    }


    private boolean compileandrun(JSONObject device) {

        String folder = System.currentTimeMillis()+"";
        String filename = folder+".ino";

        new File(System.getProperty("user.home") + "/"+ Data.foldername+"/temp/"+folder+"/").mkdir();


        try {
            new File(System.getProperty("user.home") + "/"+ Data.foldername+"/temp/"+folder+"/"+filename).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (PrintWriter out = new PrintWriter(System.getProperty("user.home") + "/"+ Data.foldername+"/temp/"+folder+"/"+filename)) {
            out.println(compile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        if(!new File(System.getProperty("user.home") + "/"+".arduino15").exists()){
            new File(System.getProperty("user.home") + "/"+".arduino15").mkdir();
            downloaddeviceconfig();
        }else if(!new File(System.getProperty("user.home") + "/"+".arduino15/package_index.json").exists()) {
            downloaddeviceconfig();
        }







        String compile_to_hex = "";
        String upload = "";

        if (isWindows()) {

        } else if (isMac()) {

        } else if (isUnix()) {

            compile_to_hex = "./libs/arduino/cli/linux/arduino-cli compile -b "+ device.getJSONArray("boards").getJSONObject(0).getString("FQBN")+" "+System.getProperty("user.home") + "/"+ Data.foldername+"/temp/"+folder+"/"+filename;

            upload = "./libs/arduino/cli/linux/arduino-cli upload -b "+device.getJSONArray("boards").getJSONObject(0).getString("FQBN")+" "+ System.getProperty("user.home") + "/"+ Data.foldername+"/temp/"+folder+"/"+" -p " + device.getString("address")+" -v";
        } else {
            Programm.logger.severe("You OS is not supported");
        }

        runcommand(compile_to_hex);
        runcommand(upload);

        new File(System.getProperty("user.home") + "/"+ Data.foldername+"/temp/"+folder+"/"+filename).delete();



        return true;
    }


    private static String compilesketch() {
        String Programm = "";
        Block a = ProjectManager.getActProjectVar().blocks.get(0);

        Programm = ((ArduinoBlock) a.getBlocktype()).getCode()+"\n";
        while (a.getRight() != null) {

            //block.getRight().setX(block.getRight().getX() + block.getW());
            a = a.getRight();

            Programm = Programm + ((ArduinoBlock) a.getBlocktype()).getCode()+"\n";

        }
        Programm=Programm+"}\n";

        /////////////Loop Teil

        a = ProjectManager.getActProjectVar().blocks.get(1);

        Programm = Programm + ((ArduinoBlock) a.getBlocktype()).getCode()+"\n";
        while (a.getRight() != null) {

            //block.getRight().setX(block.getRight().getX() + block.getW());
            a = a.getRight();

            Programm = Programm + ((ArduinoBlock) a.getBlocktype()).getCode()+"\n";

        }
        Programm=Programm+"}\n";




        return Programm;
    }



    private static void downloaddeviceconfig() {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL("https://downloads.arduino.cc/packages/package_index.json").openStream());
             FileOutputStream fileOS = new FileOutputStream(System.getProperty("user.home") + "/"+".arduino15/package_index.json")) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            // handles IO exceptions
        }
    }

    //OS tesster
    private static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    private static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    private static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }

    private static String runcommand(String command) {

        Runtime rt = Runtime.getRuntime();
        Process pr = null;
        try {

            pr = rt.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = null;
        String output = null;
        String outputfromcli = "";

        String strCurrentLine;
        while (true) {
            try {
                if ((strCurrentLine = input.readLine()) == null){

                    break;
                }else{
                    Programm.logger.config(strCurrentLine);
                     outputfromcli+=strCurrentLine;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


return outputfromcli;

    }

    public static JSONArray getBoards( ) {

        String get_device = "";

        String update_index ="";
        String install_avr="";

        if (isWindows()) {

        } else if (isMac()) {

        } else if (isUnix()) {

            get_device = "./libs/arduino/cli/linux/arduino-cli board list --format json";
            install_avr ="./libs/arduino/cli/linux/arduino-cli core install arduino:avr@1.6.21";
            update_index ="./libs/arduino/cli/linux/arduino-cli core update-index";

        } else {
            Programm.logger.severe("You OS is not supported");
        }

        runcommand(install_avr);
        runcommand(update_index);

       return new JSONArray(runcommand(get_device));
    }




}
