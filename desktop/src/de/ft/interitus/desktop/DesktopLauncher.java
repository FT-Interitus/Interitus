package de.ft.interitus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.glfwWindowHint;

public class DesktopLauncher {
	DesktopLauncher SESSION = this;

	/***
	 *
	 * @param arg -nogui open no window
	 *            -v Verbose output
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



			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();


config.setWindowedMode(Var.w,Var.h);

			config.useVsync(false);
			//glfwWindowHint(GLFW_SAMPLES, 4);

			 new Lwjgl3Application(new Programm(), config);

		}else{

			System.out.println("No GUI opend");

		}


	}

}
