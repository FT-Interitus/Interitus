package de.ft.interitus.Block;


import com.badlogic.gdx.graphics.Texture;

public class Parameter {

    private Object Parameter;
    private Texture ParameterTexture;
    private String ParameterName;
    private String ParameterDescription;
    private String Unit;
    private Block block;

    public Parameter(Object parameter, Texture ParameterTexture, String ParameterName, String ParameterDescription,String Unit) {
        this.ParameterTexture=ParameterTexture;
        this.Parameter = parameter;
        this.ParameterName = ParameterName;
        this.ParameterDescription = ParameterDescription;
        this.Unit = Unit;



    }

    public Object getParameter() {
        return Parameter;
    }


    public String getParameterDescription() {
        return ParameterDescription;
    }

    public String getParameterName() {
        return ParameterName;
    }

    public void setParameterDescription(String parameterDescription) {
        ParameterDescription = parameterDescription;
    }

    public void setParameterName(String parameterName) {
        ParameterName = parameterName;
    }

    public void setParameter(Object parameter) {
        Parameter = parameter;
    }

    public void setParameterTexture(Texture parameterTexture) {
        ParameterTexture = parameterTexture;
    }

    public Texture getParameterTexture() {
        return ParameterTexture;
    }

    public String getUnit() {
        return Unit;
    }


    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
