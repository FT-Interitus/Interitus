/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckKollision;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectTypes;
import de.ft.interitus.utils.Unproject;

public class DataWire {

    public String varName;
    int input_x = 0;
    int input_y = 0;
    int output_x = 0;
    int output_y = 0;
    private Parameter param_input;
    private Parameter param_output;



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

        if(input_y<output_y) {
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_x, input_y, parameter_middle_x, input_y - UIVar.first_curve_margin, UIVar.thickness);
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_output_x, output_y, parameter_middle_output_x, output_y - UIVar.first_curve_margin, UIVar.thickness);
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_x, input_y - UIVar.first_curve_margin, parameter_middle_output_x, input_y - UIVar.first_curve_margin, UIVar.thickness);
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_output_x, input_y - UIVar.first_curve_margin, parameter_middle_output_x, output_y - UIVar.first_curve_margin, UIVar.thickness);
        }else{
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_x, input_y, parameter_middle_x, output_y-UIVar.first_curve_margin, UIVar.thickness);
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_output_x, output_y, parameter_middle_output_x, output_y - UIVar.first_curve_margin, UIVar.thickness);
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_x, output_y - UIVar.first_curve_margin, parameter_middle_output_x, output_y - UIVar.first_curve_margin, UIVar.thickness);
            ProgrammingSpace.BlockshapeRenderer.rectLine(parameter_middle_output_x, output_y - UIVar.first_curve_margin, parameter_middle_output_x, output_y, UIVar.thickness);
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

}
