/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UIElements.check.CheckKollision;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.Unproject;

public class DataWire {

    public String varName;
    int input_x = 0;
    int input_y = 0;
    int output_x = 0;
    int output_y = 0;
    private Parameter param_input;
    private Parameter param_output;

    /////Layout
    private int verschiebung_1_Horizontale =-100;
    private int verschiebung_2_HorizontaleInput=40;
    private int verschiebung_3_HorizontaleOutput =-40;
    private int verschiebung_4_VertikaleInput =-20;
    private int verschiebung_5_VertikaleInput =-10;



    public DataWire(Parameter param_input) {
        this.param_input = param_input;




        ProjectManager.getActProjectVar().moveingdatawire = this;
        Programm.logger.config("New Output");
    }

    public DataWire(Parameter param_input,Parameter param_output) {
        this.param_output = param_output;
        this.param_input = param_input;

        param_output.setDatawire(this);
        param_input.setDatawire(this);
    }

    public void setLayout(int verschiebung_1_Horizontale, int verschiebung_2_HorizontaleInput, int verschiebung_3_HorizontaleOutput, int verschiebung_4_VertikaleInput, int verschiebung_5_VertikaleInput){
        this.verschiebung_1_Horizontale=verschiebung_1_Horizontale;
        this.verschiebung_2_HorizontaleInput=verschiebung_2_HorizontaleInput;
        this.verschiebung_3_HorizontaleOutput=verschiebung_3_HorizontaleOutput;
        this.verschiebung_4_VertikaleInput=verschiebung_4_VertikaleInput;
        this.verschiebung_5_VertikaleInput=verschiebung_5_VertikaleInput;
    }

    public void draw() {

       input_x = param_input.getX();
       input_y = param_input.getY();

        if(ProjectManager.getActProjectVar().moveingdatawire==this) {
            output_x = ((int) Unproject.unproject().x);
            output_y = ((int) Unproject.unproject().y);



           for(Block block: ProjectManager.getActProjectVar().blocks) {
               if(block.getBlocktype().getBlockParameter()==null) {
                   continue;
               }
               for(Parameter parameter:block.getBlocktype().getBlockParameter()) {
                   if(parameter!=this.getParam_input()&&!parameter.getParameterType().isOutput()&&parameter.getDatawire()==null&&parameter.getParameterType().typ==this.getParam_input().getParameterType().typ) {


                       if(CheckKollision.checkmousewithobject(parameter.getX(),parameter.getY(), UIVar.parameter_width,UIVar.parameter_height,Unproject.unproject())&&Gdx.input.isButtonPressed(0)) {
                           param_output=parameter;
                           parameter.setDatawire(this);
                       }

                   }
               }
           }

        }

        if(param_output==null)  {
            if(this!=ProjectManager.getActProjectVar().moveingdatawire) {
                ProjectManager.getActProjectVar().moveingdatawire = this;
            }
        }else{
            output_x=param_output.getX();
            output_y=param_output.getY();
            if(ProjectManager.getActProjectVar().moveingdatawire==this) {
                ProjectManager.getActProjectVar().moveingdatawire = null;
            }
        }



        ProgrammingSpace.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ProgrammingSpace.BlockshapeRenderer.setColor(0.234f, 0.247f,0.254f,1);
        int parameter_middle_x=input_x+UIVar.parameter_width/2;
        int parameter_middle_output_x;



        if(param_output==null){
            parameter_middle_output_x = output_x;
        }else {
            parameter_middle_output_x = output_x + UIVar.parameter_width / 2;
        }
        if(output_y > input_y || UIVar.DataWire_OutputHorizontale_Y==0) {
            UIVar.DataWire_horizontal_Y=input_y - UIVar.first_curve_margin+ verschiebung_1_Horizontale;
            UIVar.DataWire_InputVertikale_X=parameter_middle_x+verschiebung_2_HorizontaleInput;
            UIVar.DataWire_OutputVertikale_X=parameter_middle_output_x+ verschiebung_3_HorizontaleOutput;
            UIVar.DataWire_InputHorizontale_Y=input_y-UIVar.first_curve_margin+ verschiebung_4_VertikaleInput;
            UIVar.DataWire_OutputHorizontale_Y=output_y-UIVar.first_curve_margin+ verschiebung_5_VertikaleInput;

            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_x, input_y, parameter_middle_x, UIVar.DataWire_InputHorizontale_Y,UIVar.thickness);//Verlängerung an input
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_x, UIVar.DataWire_InputHorizontale_Y, UIVar.DataWire_InputVertikale_X,UIVar.DataWire_InputHorizontale_Y,UIVar.thickness);//input horizontal verlängerung
            ProgrammingSpace.BlockshapeRenderer.rectLine(UIVar.DataWire_InputVertikale_X, UIVar.DataWire_InputHorizontale_Y, UIVar.DataWire_InputVertikale_X, UIVar.DataWire_horizontal_Y, UIVar.thickness);//vertikale an input
            ProgrammingSpace.BlockshapeRenderer.rectLine(UIVar.DataWire_InputVertikale_X, UIVar.DataWire_horizontal_Y, UIVar.DataWire_OutputVertikale_X, UIVar.DataWire_horizontal_Y, UIVar.thickness);//Horizontale
            ProgrammingSpace.BlockshapeRenderer.rectLine(UIVar.DataWire_OutputVertikale_X, UIVar.DataWire_horizontal_Y, UIVar.DataWire_OutputVertikale_X, UIVar.DataWire_OutputHorizontale_Y, UIVar.thickness);//Vertikale an output
            ProgrammingSpace.BlockshapeRenderer.rectLine(UIVar.DataWire_OutputVertikale_X,UIVar.DataWire_OutputHorizontale_Y, parameter_middle_output_x, UIVar.DataWire_OutputHorizontale_Y, UIVar.thickness);//output horizontal verlängerung
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_output_x, output_y, parameter_middle_output_x, UIVar.DataWire_OutputHorizontale_Y, UIVar.thickness);//Verlängerung an output
        }else {

            UIVar.DataWire_horizontal_Y=output_y - UIVar.first_curve_margin+ verschiebung_1_Horizontale;
            UIVar.DataWire_InputVertikale_X=parameter_middle_x+verschiebung_2_HorizontaleInput;
            UIVar.DataWire_OutputVertikale_X=parameter_middle_output_x+ verschiebung_3_HorizontaleOutput;
            UIVar.DataWire_InputHorizontale_Y=input_y-UIVar.first_curve_margin+ verschiebung_4_VertikaleInput;
            UIVar.DataWire_OutputHorizontale_Y=output_y-UIVar.first_curve_margin+ verschiebung_5_VertikaleInput;

            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_x, input_y, parameter_middle_x, UIVar.DataWire_InputHorizontale_Y,UIVar.thickness);//Verlängerung an input
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_x, UIVar.DataWire_InputHorizontale_Y, UIVar.DataWire_InputVertikale_X,UIVar.DataWire_InputHorizontale_Y,UIVar.thickness);//input horizontal verlängerung
            ProgrammingSpace.BlockshapeRenderer.rectLine(UIVar.DataWire_InputVertikale_X, UIVar.DataWire_InputHorizontale_Y, UIVar.DataWire_InputVertikale_X, UIVar.DataWire_horizontal_Y, UIVar.thickness);//vertikale an input
            ProgrammingSpace.BlockshapeRenderer.rectLine(UIVar.DataWire_InputVertikale_X, UIVar.DataWire_horizontal_Y, UIVar.DataWire_OutputVertikale_X, UIVar.DataWire_horizontal_Y, UIVar.thickness);//Horizontale
            ProgrammingSpace.BlockshapeRenderer.rectLine(UIVar.DataWire_OutputVertikale_X, UIVar.DataWire_horizontal_Y, UIVar.DataWire_OutputVertikale_X, UIVar.DataWire_OutputHorizontale_Y, UIVar.thickness);//Vertikale an output
            ProgrammingSpace.BlockshapeRenderer.rectLine(UIVar.DataWire_OutputVertikale_X,UIVar.DataWire_OutputHorizontale_Y, parameter_middle_output_x, UIVar.DataWire_OutputHorizontale_Y, UIVar.thickness);//output horizontal verlängerung
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_output_x, output_y, parameter_middle_output_x, UIVar.DataWire_OutputHorizontale_Y, UIVar.thickness);//Verlängerung an output

        }
        ProgrammingSpace.BlockshapeRenderer.end();

    }

    public Parameter getParam_input() {
        return param_input;
    }

    public void setParam_input(Parameter param_input) {
        this.param_input = param_input;
    }

    public Parameter getParam_output() {
        return param_output;
    }

    public void setParam_output(Parameter param_output) {
        this.param_output = param_output;
    }

    public void delete() {

        try {
            param_input.setDatawire(null);
        }catch (NullPointerException e){

        }

        try {
            param_output.setDatawire(null);
        }catch (NullPointerException e) {

        }
        param_output =null;
        param_input = null;

    }

    public int getVerschiebung_1_Horizontale() {
        return verschiebung_1_Horizontale;
    }

    public int getVerschiebung_2_HorizontaleInput() {
        return verschiebung_2_HorizontaleInput;
    }

    public int getVerschiebung_3_HorizontaleOutput() {
        return verschiebung_3_HorizontaleOutput;
    }

    public int getVerschiebung_4_VertikaleInput() {
        return verschiebung_4_VertikaleInput;
    }

    public int getVerschiebung_5_VertikaleInput() {
        return verschiebung_5_VertikaleInput;
    }

    public void setVerschiebung_1_Horizontale(int verschiebung_1_Horizontale) {
        this.verschiebung_1_Horizontale = verschiebung_1_Horizontale;
    }

    public void setVerschiebung_2_HorizontaleInput(int verschiebung_2_HorizontaleInput) {
        this.verschiebung_2_HorizontaleInput = verschiebung_2_HorizontaleInput;
    }

    public void setVerschiebung_3_HorizontaleOutput(int verschiebung_3_HorizontaleOutput) {
        this.verschiebung_3_HorizontaleOutput = verschiebung_3_HorizontaleOutput;
    }

    public void setVerschiebung_4_VertikaleInput(int verschiebung_4_VertikaleInput) {
        this.verschiebung_4_VertikaleInput = verschiebung_4_VertikaleInput;
    }

    public void setVerschiebung_5_VertikaleInput(int verschiebung_5_VertikaleInput) {
        this.verschiebung_5_VertikaleInput = verschiebung_5_VertikaleInput;
    }
}
