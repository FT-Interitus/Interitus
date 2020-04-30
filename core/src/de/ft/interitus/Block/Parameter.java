package de.ft.interitus.Block;


public class Parameter {

    private Object Parameter;

    public Parameter(Object parameter)  {

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
}
