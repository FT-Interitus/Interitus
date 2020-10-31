/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.WindowManager;
import de.ft.interitus.Program;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.UI.UIElements.check.CheckCollision;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.Unproject;

//TODO when moving Wire ProjectVar:changes should be true
public class DataWire {


    int input_x = 0;
    int input_y = 0;
    int output_x = 0;
    int output_y = 0;
    private Parameter param_input;
    private Parameter param_output;
    private boolean setCorsoronlyonce=false;
    private final Vector3 tempvector = new Vector3();
    private final Vector3 tempvector1 = new Vector3();

    private boolean[] moving=new boolean[10];


    /////Layout
    private int verschiebung_1_Horizontale =0;
    private int verschiebung_2_HorizontaleInput=0;
    private int verschiebung_3_HorizontaleOutput =0;
    private int verschiebung_4_VertikaleInput =0;
    private int verschiebung_5_VertikaleInput =0;


    /**
     * @param param_input
     */
    public DataWire(Parameter param_input) {
        ProjectManager.getActProjectVar().changes = true;
        this.param_input = param_input;

        ProjectManager.getActProjectVar().moveingdatawire = this;
        Program.logger.config("New Output");
    }

    /**
     * @param param_input
     * @param param_output
     */
    public DataWire(Parameter param_input,Parameter param_output) {
        this.param_output = param_output;
        this.param_input = param_input;

        param_output.getDataWires().add(this);
        param_input.getDataWires().add(this);
    }



    private void userLayoutMovment(){
        if((UIVar.DataWire[1][0]<=UIVar.DataWire[2][0] && (moving[0] || CheckMouse.isMouseover(UIVar.DataWire[1][0],UIVar.DataWire[1][1]-UIVar.DataWireMouseKollisionsFeld,UIVar.DataWire[2][0]-UIVar.DataWire[1][0],UIVar.DataWireMouseKollisionsFeld*2, true))) || (UIVar.DataWire[1][0]>UIVar.DataWire[2][0] && (moving[0] || CheckMouse.isMouseover(UIVar.DataWire[2][0],UIVar.DataWire[1][1]-UIVar.DataWireMouseKollisionsFeld,UIVar.DataWire[1][0]-UIVar.DataWire[2][0],UIVar.DataWireMouseKollisionsFeld*2, true)))){
            Gdx.graphics.setSystemCursor(SystemCursor.VerticalResize);
            setCorsoronlyonce=true;
            if(Gdx.input.isButtonJustPressed(0)) {
                moving[0] = true;
                ProjectManager.getActProjectVar().changes = true;
            }
            if(Gdx.input.isButtonPressed(0) && moving[0]){
                if(UIVar.doonce) {
                    UIVar.merkpos.set(Gdx.input.getX()*ProgramingSpace.cam.zoom, Gdx.input.getY()*ProgramingSpace.cam.zoom, verschiebung_4_VertikaleInput);
                    UIVar.doonce=false;
                }
                verschiebung_4_VertikaleInput= (int) ((int)UIVar.merkpos.y-Gdx.input.getY()*ProgramingSpace.cam.zoom+(int)UIVar.merkpos.z);
            }else if(!Gdx.input.isButtonPressed(0)){
                UIVar.doonce=true;
                moving[0]=false;
            }
        }else if((UIVar.DataWire[2][1]>UIVar.DataWire[3][1]  && (moving[1] || CheckMouse.isMouseover(UIVar.DataWire[2][0]-UIVar.DataWireMouseKollisionsFeld,UIVar.DataWire[3][1],UIVar.DataWireMouseKollisionsFeld*2, UIVar.DataWire[2][1]-UIVar.DataWire[3][1], true))) || (UIVar.DataWire[2][1]<=UIVar.DataWire[3][1]  && (moving[1] || CheckMouse.isMouseover(UIVar.DataWire[2][0]-UIVar.DataWireMouseKollisionsFeld,UIVar.DataWire[2][1],UIVar.DataWireMouseKollisionsFeld*2, UIVar.DataWire[3][1]-UIVar.DataWire[2][1], true)))){
            Gdx.graphics.setSystemCursor(SystemCursor.HorizontalResize);
            setCorsoronlyonce=true;
            if(Gdx.input.isButtonJustPressed(0)) {
                moving[1] = true;
                ProjectManager.getActProjectVar().changes = true;

            }
            if(Gdx.input.isButtonPressed(0)  && moving[1]){
                if(UIVar.doonce) {
                    UIVar.merkpos.set(Gdx.input.getX()*ProgramingSpace.cam.zoom, Gdx.input.getY()*ProgramingSpace.cam.zoom, verschiebung_2_HorizontaleInput);
                    UIVar.doonce=false;
                }
                verschiebung_2_HorizontaleInput= (int) (Gdx.input.getX()*ProgramingSpace.cam.zoom-(int)UIVar.merkpos.x+(int)UIVar.merkpos.z);
            }else if(!Gdx.input.isButtonPressed(0)){
                UIVar.doonce=true;
                moving[1]=false;
            }
        }else if((UIVar.DataWire[3][0]<=UIVar.DataWire[4][0] && (moving[2] || CheckMouse.isMouseover(UIVar.DataWire[3][0], UIVar.DataWire[3][1]-UIVar.DataWireMouseKollisionsFeld,UIVar.DataWire[4][0]-UIVar.DataWire[3][0],UIVar.DataWireMouseKollisionsFeld*2, true)))   ||  (UIVar.DataWire[3][0]>UIVar.DataWire[4][0] && (moving[2] || CheckMouse.isMouseover(UIVar.DataWire[4][0], UIVar.DataWire[3][1]-UIVar.DataWireMouseKollisionsFeld,UIVar.DataWire[3][0]-UIVar.DataWire[4][0],UIVar.DataWireMouseKollisionsFeld*2, true)))){
            Gdx.graphics.setSystemCursor(SystemCursor.VerticalResize);
            setCorsoronlyonce=true;

            if(Gdx.input.isButtonJustPressed(0)) {
                moving[2] = true;
                ProjectManager.getActProjectVar().changes = true;
            }
            if(Gdx.input.isButtonPressed(0)  && moving[2]){
                if(UIVar.doonce) {
                    UIVar.merkpos.set(Gdx.input.getX()*ProgramingSpace.cam.zoom, Gdx.input.getY()*ProgramingSpace.cam.zoom, verschiebung_1_Horizontale);
                    UIVar.doonce=false;
                }
                verschiebung_1_Horizontale= (int) ((int)UIVar.merkpos.y-Gdx.input.getY()*ProgramingSpace.cam.zoom+(int)UIVar.merkpos.z);
            }else if(!Gdx.input.isButtonPressed(0)){
                UIVar.doonce=true;
                moving[2]=false;
            }
        }else if((UIVar.DataWire[4][1]<=UIVar.DataWire[5][1] && (moving[3] || CheckMouse.isMouseover(UIVar.DataWire[4][0]-UIVar.DataWireMouseKollisionsFeld, UIVar.DataWire[4][1], UIVar.DataWireMouseKollisionsFeld*2,UIVar.DataWire[5][1]-UIVar.DataWire[4][1], true))) || (UIVar.DataWire[4][1]>UIVar.DataWire[5][1] && (moving[3] || CheckMouse.isMouseover(UIVar.DataWire[4][0]-UIVar.DataWireMouseKollisionsFeld, UIVar.DataWire[5][1], UIVar.DataWireMouseKollisionsFeld*2,UIVar.DataWire[4][1]-UIVar.DataWire[5][1], true)))){
            Gdx.graphics.setSystemCursor(SystemCursor.HorizontalResize);
            setCorsoronlyonce=true;

            if(Gdx.input.isButtonJustPressed(0)) {
                moving[3] = true;
                ProjectManager.getActProjectVar().changes = true;

            }
            if(Gdx.input.isButtonPressed(0)  && moving[3]){
                if(UIVar.doonce) {
                    UIVar.merkpos.set(Gdx.input.getX()*ProgramingSpace.cam.zoom, Gdx.input.getY()*ProgramingSpace.cam.zoom, verschiebung_3_HorizontaleOutput);
                    UIVar.doonce=false;
                }
                verschiebung_3_HorizontaleOutput= (int) (Gdx.input.getX()*ProgramingSpace.cam.zoom-(int)UIVar.merkpos.x+(int)UIVar.merkpos.z);
            }else if(!Gdx.input.isButtonPressed(0)){
                UIVar.doonce=true;
                moving[3]=false;
            }
        }else if((UIVar.DataWire[5][0]<=UIVar.DataWire[6][0] && (moving[4] || CheckMouse.isMouseover(UIVar.DataWire[5][0], UIVar.DataWire[5][1]-UIVar.DataWireMouseKollisionsFeld, UIVar.DataWire[6][0]-UIVar.DataWire[5][0], UIVar.DataWireMouseKollisionsFeld*2, true)))  ||  (UIVar.DataWire[5][0]>UIVar.DataWire[6][0] && (moving[4] || CheckMouse.isMouseover(UIVar.DataWire[6][0], UIVar.DataWire[5][1]-UIVar.DataWireMouseKollisionsFeld, UIVar.DataWire[5][0]-UIVar.DataWire[6][0], UIVar.DataWireMouseKollisionsFeld*2, true)))){
            Gdx.graphics.setSystemCursor(SystemCursor.VerticalResize);
            setCorsoronlyonce=true;

            if(Gdx.input.isButtonJustPressed(0)) {
                moving[4] = true;
                ProjectManager.getActProjectVar().changes = true;

            }
            if(Gdx.input.isButtonPressed(0)  && moving[4]){
                if(UIVar.doonce) {
                    UIVar.merkpos.set(Gdx.input.getX()*ProgramingSpace.cam.zoom, Gdx.input.getY()*ProgramingSpace.cam.zoom, verschiebung_5_VertikaleInput);
                    UIVar.doonce=false;
                }
                verschiebung_5_VertikaleInput= (int) ((int)UIVar.merkpos.y-Gdx.input.getY()*ProgramingSpace.cam.zoom+(int)UIVar.merkpos.z);
            }else if(!Gdx.input.isButtonPressed(0)){
                UIVar.doonce=true;
                moving[4]=false;
            }
        }else{
            if(setCorsoronlyonce) {
                Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
                setCorsoronlyonce=false;
            }

        }
    }

    /**
     * draws and renders the Data Wire
     */
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
                   if(parameter!=this.getParam_input()&&!parameter.getParameterType().isOutput()&&parameter.getDataWires().size()!=1&&parameter.getParameterType().typ.iscompatible(this.getParam_input().getParameterType().typ)&&parameter.getBlock()!=this.getParam_input().getBlock()) {


                       if(CheckCollision.checkpointwithobject(parameter.getX(),parameter.getY(), UIVar.parameter_width,UIVar.parameter_height,Unproject.unproject())&&Gdx.input.isButtonPressed(0)) {
                           ProjectManager.getActProjectVar().moveingdatawire=null;
                           param_output=parameter;
                           parameter.getDataWires().clear();
                           parameter.getDataWires().add(this);
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



        WindowManager.BlockshapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        WindowManager.BlockshapeRenderer.setColor(this.getParam_input().getParameterType().getTyp().getWirecolor());

        int parameter_middle_x=input_x+UIVar.parameter_width/2;
        int parameter_middle_output_x;

        if(param_output==null){
            parameter_middle_output_x = output_x;
        }else {
            parameter_middle_output_x = output_x + UIVar.parameter_width / 2;
        }
        if(output_y > input_y || UIVar.DataWire_OutputHorizontale_Y==0) {
            UIVar.DataWire_horizontal_Y=input_y - UIVar.first_curve_margin+ verschiebung_1_Horizontale;
        }else {
            UIVar.DataWire_horizontal_Y=output_y - UIVar.first_curve_margin+ verschiebung_1_Horizontale;
        }
        UIVar.DataWire_InputVertikale_X=parameter_middle_x+verschiebung_2_HorizontaleInput;
        UIVar.DataWire_OutputVertikale_X=parameter_middle_output_x+ verschiebung_3_HorizontaleOutput;
        UIVar.DataWire_InputHorizontale_Y=input_y-UIVar.first_curve_margin+ verschiebung_4_VertikaleInput;
        UIVar.DataWire_OutputHorizontale_Y=output_y-UIVar.first_curve_margin+ verschiebung_5_VertikaleInput;

        UIVar.DataWire[0][0]=parameter_middle_x;
        UIVar.DataWire[0][1]=input_y;

        UIVar.DataWire[1][0]=parameter_middle_x;
        UIVar.DataWire[1][1]=UIVar.DataWire_InputHorizontale_Y;

        UIVar.DataWire[2][0]=UIVar.DataWire_InputVertikale_X;
        UIVar.DataWire[2][1]=UIVar.DataWire_InputHorizontale_Y;

        UIVar.DataWire[3][0]=UIVar.DataWire_InputVertikale_X;
        UIVar.DataWire[3][1]=UIVar.DataWire_horizontal_Y;

        UIVar.DataWire[4][0]=UIVar.DataWire_OutputVertikale_X;
        UIVar.DataWire[4][1]=UIVar.DataWire_horizontal_Y;

        UIVar.DataWire[5][0]=UIVar.DataWire_OutputVertikale_X;
        UIVar.DataWire[5][1]=UIVar.DataWire_OutputHorizontale_Y;

        UIVar.DataWire[6][0]=parameter_middle_output_x;
        UIVar.DataWire[6][1]=UIVar.DataWire_OutputHorizontale_Y;

        UIVar.DataWire[7][0]=parameter_middle_output_x;
        UIVar.DataWire[7][1]=output_y;

        WindowManager.BlockshapeRenderer.rectLine(UIVar.DataWire[0][0], UIVar.DataWire[0][1], UIVar.DataWire[1][0], UIVar.DataWire[1][1],UIVar.thickness);//Verlängerung an input
        WindowManager.BlockshapeRenderer.rectLine(UIVar.DataWire[1][0], UIVar.DataWire[1][1], UIVar.DataWire[2][0], UIVar.DataWire[2][1],UIVar.thickness);//input horizontal verlängerung
        WindowManager.BlockshapeRenderer.rectLine(UIVar.DataWire[2][0], UIVar.DataWire[2][1], UIVar.DataWire[3][0], UIVar.DataWire[3][1], UIVar.thickness);//vertikale an input
        WindowManager.BlockshapeRenderer.rectLine(UIVar.DataWire[3][0], UIVar.DataWire[3][1], UIVar.DataWire[4][0], UIVar.DataWire[4][1], UIVar.thickness);//Horizontale
        WindowManager.BlockshapeRenderer.rectLine(UIVar.DataWire[4][0], UIVar.DataWire[4][1], UIVar.DataWire[5][0], UIVar.DataWire[5][1], UIVar.thickness);//Vertikale an output
        WindowManager.BlockshapeRenderer.rectLine(UIVar.DataWire[5][0], UIVar.DataWire[5][1], UIVar.DataWire[6][0], UIVar.DataWire[6][1], UIVar.thickness);//output horizontal verlängerung
        WindowManager.BlockshapeRenderer.rectLine(UIVar.DataWire[6][0], UIVar.DataWire[6][1], UIVar.DataWire[7][0], UIVar.DataWire[7][1], UIVar.thickness);//Verlängerung an output

        WindowManager.BlockshapeRenderer.end();
        userLayoutMovment();

        if(ProjectManager.getActProjectVar().moveingdatawire==this) {

            if(Gdx.input.isButtonJustPressed(0)) {
                int counter = 0;
                for (int i = 0; i < ProjectManager.getActProjectVar().visible_blocks.size(); i++) {


                    if(CheckCollision.checkmousewithblock(ProjectManager.getActProjectVar().visible_blocks.get(i)))  {
                        counter++;
                    }



                }

                if(counter==0) {
                    this.delete();
                }
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                this.delete();
            }
        }
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
            param_input.getDataWires().remove(this);
        }catch (NullPointerException e){

        }

        try {
            param_output.getDataWires().clear();
        }catch (NullPointerException e) {

        }
        param_output =null;
        param_input = null;

        if(ProjectManager.getActProjectVar().moveingdatawire==this) {
            ProjectManager.getActProjectVar().moveingdatawire=null;
        }

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
