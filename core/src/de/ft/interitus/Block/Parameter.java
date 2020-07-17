package de.ft.interitus.Block;


import com.badlogic.gdx.graphics.Texture;

public class Parameter {

    private Object Parameter;
    private Texture ParameterTexture;

    public Parameter(Object parameter, Texture ParameterTexture) {
        this.ParameterTexture=ParameterTexture;
        this.Parameter = parameter;

     /*   if(parameter instanceof String) {
            System.out.println("Test");
            TODO um Type zu bestimmen
        }

      */

    }

    public Object getParameter() {
        return Parameter;
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
}
