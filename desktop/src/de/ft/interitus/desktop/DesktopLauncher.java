package de.ft.interitus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class DesktopLauncher {
	DesktopLauncher SESSION = this;

	/***
	 *
	 * @param arg -nogui open no window
	 *            -v Verbose output not included yet
	 */

	public static void main (String[] arg) throws FileNotFoundException {

		Var.programmarguments.addAll(Arrays.asList(arg));

		if(Var.programmarguments.indexOf("-v")!=-1) {
			Var.verboseoutput = true;
		}

		if(Var.programmarguments.indexOf("-do")!=-1) {
			LoggingSystem.RedirectLog();
		}


		if(Var.programmarguments.indexOf("-nogui")==-1) {



			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.useGL30 = false;
			config.width = Var.w;
			config.height = Var.h;
			config.vSyncEnabled = false;



			 new LwjglApplication(new Programm(), config);

		}else{

			System.out.println("No GUI opend");

		}


	}

}
