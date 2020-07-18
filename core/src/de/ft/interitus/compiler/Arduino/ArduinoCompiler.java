package de.ft.interitus.compiler.Arduino;

import de.ft.interitus.Block.Block;
import de.ft.interitus.compiler.Compiler;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.device.BlockTypes.Arduino.Arduino.ArduinoBlock;

public class ArduinoCompiler implements Compiler {


    @Override
    public String compile() {
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



        System.out.println("Code:   \n"+ Programm);
        return Programm;
    }
}
