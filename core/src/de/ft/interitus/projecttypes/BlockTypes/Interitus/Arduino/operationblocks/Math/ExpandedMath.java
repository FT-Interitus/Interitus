/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.Math;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class ExpandedMath implements BlockModi, ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter a;
    Parameter b;
    Parameter c;
    Parameter d;
    Parameter Ergebnis;
    BlockSettings blockSettings = new BlockSettings();

    public ExpandedMath() {
        a = new Parameter("", AssetLoader.Parameter_a, "A", "Erster Parameter", "", new ParameterType(InitArduino.floatvar, false, false), true);
        b = new Parameter("", AssetLoader.Parameter_b, "B", "Zweiter Parameter", "", new ParameterType(InitArduino.floatvar, false, false), true);
        c = new Parameter("", AssetLoader.Parameter_c, "C", "Dritter Parameter", "", new ParameterType(InitArduino.floatvar, false, false), true);
        d = new Parameter("", AssetLoader.Parameter_d, "D", "Vierter Parameter", "", new ParameterType(InitArduino.floatvar, false, false), true);
        Ergebnis = new Parameter("", AssetLoader.Parameter_isequal, "Ergebnis", "Das Ergebnis", "", new ParameterType(InitArduino.floatvar, true, false), true);
        parameters.add(a);
        parameters.add(b);
        parameters.add(c);
        parameters.add(d);
        parameters.add(Ergebnis);
        blockSettings.setSettings("a+b-c/d");

    }

    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameters;
    }

    @Override
    public BlockSettings getblocksettings() {
        return blockSettings;
    }

    @Override
    public int getWidth() {
        return 200;
    }

    @Override
    public String getname() {
        return "Erweiterte Funktion";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_fkt;
    }

    @Override
    public String getCode() {


        String tempstring =blockSettings.getSettings();
        tempstring = replace(tempstring, "a", (String) a.getParameter());
        tempstring = replace(tempstring, "b", (String) b.getParameter());
        tempstring = replace(tempstring, "c", (String) c.getParameter());
        tempstring = replace(tempstring, "d", (String) d.getParameter());

        System.out.println(tempstring);

        if (parameters.get(4).getDatawire().size() > 0) {
            return parameters.get(4).getVarName() + " = " + "("  +tempstring+ ");";

        } else {
            return "(" +tempstring+ ");";
        }
    }



    private String replace(String replacestring, String target, String replacement) {

        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMONOPQRSTUVWXYZ";

        StringBuffer stringBuffer = new StringBuffer(replacestring);

       int fromIndex = 0;
        while ((fromIndex = replacestring.indexOf(target, fromIndex)) != -1) {



            fromIndex++;

            boolean replaceleftok = false;
            boolean replacerightok = false;


            int indexofreplacement = replacestring.indexOf(target, fromIndex-1);

            try {
                if (!letters.contains(replacestring.substring(indexofreplacement+1,indexofreplacement + 2))) {



                    replacerightok = true;

                }
            } catch (StringIndexOutOfBoundsException e) {
                replacerightok = true;
            }




            try {
                if (!letters.contains(replacestring.substring(indexofreplacement-1,indexofreplacement))) {


                    replaceleftok = true;

                }
            } catch (StringIndexOutOfBoundsException e) {
                replaceleftok = true;
            }

            if (replaceleftok && replacerightok) {

                stringBuffer.replace(fromIndex-1, fromIndex, replacement);
            }

        }


        return stringBuffer.toString();
    }

    @Override
    public String getHeaderCode() {
        return null;
    }


}
