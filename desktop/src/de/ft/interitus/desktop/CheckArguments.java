package de.ft.interitus.desktop;

import de.ft.interitus.Var;

public class CheckArguments {
    public static void check() {
        if(Var.programmarguments.indexOf("-v")!=-1) {
            Var.verboseoutput = true;
        }

        if(Var.programmarguments.indexOf("-do")!=-1) {
            LoggingSystem.RedirectLog();
        }

        if(Var.programmarguments.indexOf("-dps")!=-1)  {
            Var.disablePluginSubSystem = true;
        }
    }
}
