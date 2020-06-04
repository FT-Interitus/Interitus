package de.ft.interitus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.*;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalFileDropedEvent;

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

		if(Var.programmarguments.indexOf("-dps")!=-1)  {
			Var.disablePluginSubSystem = true;
		}

		if(Var.programmarguments.indexOf("-nogui")==-1) {



			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();


config.setWindowedMode(Var.w,Var.h);

			config.useVsync(false);
			config.setBackBufferConfig(8,8,8,8,16,0,4);
			//glfwWindowHint(GLFW_SAMPLES, 4);
			config.setWindowSizeLimits(1000,500,-1,-1);
			config.setWindowListener(new Lwjgl3WindowAdapter() {
				@Override
				public void created(Lwjgl3Window window) {

				}

				@Override
				public void iconified(boolean isIconified) {

				}

				@Override
				public void maximized(boolean isMaximized) {

				}

				@Override
				public void focusLost() {

				}

				@Override
				public void focusGained() {

				}

				@Override
				public boolean closeRequested() {
					return true; //TODO add request as event
				}

				@Override
				public void filesDropped(String[] files) {
					EventVar.globalEventManager.filedroped(new GlobalFileDropedEvent(this),files);
				}

				@Override
				public void refreshRequested() {

				}
			});

			new Lwjgl3Application(new Programm(), config);

		}else{

			System.out.println("No GUI opend");

		}


	}

}
