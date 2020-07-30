package de.ft.interitus.desktop;

import de.ft.interitus.Programm;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.programmdata.Data;

public class CheckArguments {
    public static void check() {




        if(Var.programmarguments.indexOf("-dps")!=-1)  {
            Var.disablePluginSubSystem = true;
        }

        if(Var.programmarguments.indexOf("-inc")!=-1)  {
            Var.disableprogrammnotclosedwarniung = true;
        }
        if(Var.programmarguments.indexOf("-d")!=-1) {
            Var.debug=true;
        }

        if(Var.programmarguments.indexOf("-a")!=-1) {
            Var.savemode=true;
        }

        if(Var.programmarguments.indexOf("-ud")!=-1) {

            try {
                if(!Var.programmarguments.get(Var.programmarguments.indexOf("-ud")+1).startsWith("-")) {

                    Data.foldername = Var.programmarguments.get(Var.programmarguments.indexOf("-ud") + 1);
                }else{
                    Programm.logger.severe("Error in Programm Arguments");
                }
            }catch (Exception e) {
                Programm.logger.severe("Error in Programm Arguments");
            }

        }

        if(Var.programmarguments.indexOf("-ni")!=-1) {
            Var.nointernetconnection = true;
        }

    }
}
