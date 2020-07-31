package de.ft.interitus.UI.UIElements.dropdownmenue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ShapeRenderer;
import de.ft.interitus.ProgrammingSpace;

import java.util.ArrayList;

public class DropDownMenue implements UIElement {
    ArrayList<DropDownElement>elements=new ArrayList<>();
    int x;
    int y;
    int w=100;
    int h=20;
    int radius=5;
    boolean opened=false;
    private int longestText=0;
    Color bordercolor;
    Color fillColor;
    DropDownElement selectedElement=null;
    GlyphLayout glyphLayout=new GlyphLayout();
    Button aufklappbutton;


    public DropDownMenue(int x, int y, Color bordercolor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.bordercolor = bordercolor;
        this.fillColor = fillColor;
        aufklappbutton=new Button();
        aufklappbutton.setVisible(false);
    }

    private void longestText(){

        for(int i=0;i<elements.size();i++){
            glyphLayout.setText(UI.font,elements.get(i).getText());
            if(glyphLayout.width>this.longestText){
                this.longestText=(int)glyphLayout.width;
            }
        }
    }

    public void addelement(DropDownElement e){
        elements.add(e);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        this.x=x;
        this.y=y;
        this.h=h;
    }

    public DropDownElement getSelectedElement(){
        return selectedElement;
    }

    public void draw(){
        ProgrammingSpace.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ProgrammingSpace.shapeRenderer.setColor(bordercolor);
        ProgrammingSpace.shapeRenderer.roundendrect(x,y,w,h,radius);
        ProgrammingSpace.shapeRenderer.setColor(fillColor);
        ProgrammingSpace.shapeRenderer.roundendrect(x+1,y+1,w-2,h-2,radius);
        ProgrammingSpace.shapeRenderer.end();
        UI.UIbatch.begin();

            if(selectedElement!=null) {
                glyphLayout.setText(UI.font, selectedElement.getText());
                this.w=(int)glyphLayout.width+16+20+10;
                UI.UIbatch.draw(selectedElement.getElementImage(),x+5,y+2,16,16);
                UI.font.draw(UI.UIbatch, glyphLayout, x+16+10, y + h / 2 + glyphLayout.height / 2);
                UI.UIbatch.draw(AssetLoader.aufklapppfeil, x+16+10+glyphLayout.width+5,y+5);
            }
        UI.UIbatch.end();

        aufklappbutton.setBounds(this.x,this.y, this.w,this.h);
            if(aufklappbutton.isjustPressednormal()){
                opened=!opened;
            }

            if(opened){
                DropDownElement aktualelement;

                ProgrammingSpace.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled);
                ProgrammingSpace.shapeRenderer.setColor(1,1,0,1);
                longestText();
                ProgrammingSpace.shapeRenderer.rect(this.x,this.y-this.h*elements.size(),longestText+16+20+10,this.h*elements.size());
                ProgrammingSpace.shapeRenderer.end();

                for(int i=0;i<elements.size();i++){
                    aktualelement=elements.get(i);

                    int aktualY=this.y-this.h-(i*this.h);
                    UI.UIbatch.begin();

                    glyphLayout.setText(UI.font, aktualelement.getText());
                    UI.UIbatch.draw(aktualelement.getElementImage(),x+5,aktualY+2,16,16);
                    UI.font.draw(UI.UIbatch, glyphLayout, x+16+10, aktualY + h / 2 + glyphLayout.height / 2);
                    elements.get(i).getButton().setBounds(x,aktualY,longestText+16+20+10,h);
                    if(elements.get(i).getButton().isPresseded()){
                        selectedElement=elements.get(i);
                    }
                    UI.UIbatch.end();


                }
            }


        if(Gdx.input.isButtonPressed(0) && !aufklappbutton.isPresseded()){
            opened=false;
        }

    }
}
