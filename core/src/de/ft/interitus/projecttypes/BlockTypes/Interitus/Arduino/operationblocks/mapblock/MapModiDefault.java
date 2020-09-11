package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.operationblocks.mapblock;

import com.badlogic.gdx.graphics.Texture;
import de.ft.interitus.Block.Parameter;
import de.ft.interitus.Block.ParameterType;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.BlockTypes.BlockModi;
import de.ft.interitus.projecttypes.BlockTypes.BlockSettings;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.ArduinoBlock;
import de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino.InitArduino;
import de.ft.interitus.utils.ArrayList;

public class MapModiDefault implements BlockModi, ArduinoBlock {
    ArrayList<Parameter> parameters = new ArrayList<>();
    Parameter value;
    Parameter bereich1minimum;
    Parameter bereich1maximum;
    Parameter bereich2minimum;
    Parameter bereich2maximum;
    Parameter map;
    public MapModiDefault(){


        bereich1minimum =new Parameter("", AssetLoader.Parameter_first,"Input-Bereich Minimum", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        bereich1maximum =new Parameter("", AssetLoader.Parameter_second,"Input-Bereich Maximum", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        bereich2minimum =new Parameter("", AssetLoader.Parameter_third,"Output-Bereich Minimum", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        bereich2maximum =new Parameter("", AssetLoader.Parameter_fourth,"Output-Bereich Maximum", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        map =new Parameter("", AssetLoader.Parameter_IO,"Input", "", "", new ParameterType(InitArduino.floatvar, false, false), true);
        value=new Parameter("",AssetLoader.Parameter_isequal,"output", "Das Ergebnis der Multiplikation (Produkt)", "", new ParameterType(InitArduino.floatvar, true, false), true);

        parameters.add(map);
        parameters.add(bereich1minimum);
        parameters.add(bereich1maximum);
        parameters.add(bereich2minimum);
        parameters.add(bereich2maximum);
        parameters.add(value);

    }
    @Override
    public ArrayList<Parameter> getBlockParameter() {
        return parameters;
    }

    @Override
    public BlockSettings getblocksettings() {
        return null;
    }

    @Override
    public int getWidth() {
        return 230;
    }

    @Override
    public String getname() {
        return "Map";
    }

    @Override
    public Texture getModiImage() {
        return AssetLoader.Parameter_Mal;
    }

    @Override
    public String getCode() {

        if(parameters.get(5).getDatawire().size()>0)  {

           return parameters.get(5).getVarName() +" = "+ "map("+parameters.get(0).getParameter()+","+parameters.get(1).getParameter()+","+parameters.get(2).getParameter()+","+parameters.get(3).getParameter()+","+parameters.get(4).getParameter()+");";


        }else{
            return "map("+parameters.get(0).getParameter()+","+parameters.get(1).getParameter()+","+parameters.get(2).getParameter()+","+parameters.get(3).getParameter()+","+parameters.get(4).getParameter()+");";

        }


    }
}
