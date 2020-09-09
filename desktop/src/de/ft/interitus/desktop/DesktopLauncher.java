/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import de.ft.interitus.Programm;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.editor.Editor;
import de.ft.interitus.Var;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalCloseEvent;
import de.ft.interitus.events.global.GlobalFileDropedEvent;
import de.ft.interitus.events.global.GlobalFocusLostEvent;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class DesktopLauncher {
	DesktopLauncher SESSION = this;

	/***
	 *
	 * @param arg -nogui open no window
	 *            -d Debug Mode
	 *            -dps disable Plugin-Sub-System
	 *            -a SaveMode (Settings wont be saved)
	 *            -ud [PATH] Another Place to store User Data
	 *            -inc Ignore not correctly closed Programm
	 *            -ni Do not use Internet Connection (Offline Mode)
	 *
	 */

	public static void main (String[] arg) throws FileNotFoundException {



		Var.programmarguments.addAll(Arrays.asList(arg));

		CheckArguments.check();

		if(!Var.programmarguments.contains("-nogui")) {



			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();


			config.setWindowedMode(Var.w,Var.h);

			config.setWindowIcon(Files.FileType.Internal, "Icon/interitus.png");
			config.setBackBufferConfig(8,8,8,8,16,0,4);
			config.setWindowSizeLimits(1000,500,-1,-1);
			config.disableAudio(true);
			config.setInitialVisible(false);


			config.setWindowListener(new Lwjgl3WindowAdapter() {
				@Override
				public void created(Lwjgl3Window window) {

					Var.window = window;

				}

				@Override
				public void iconified(boolean isIconified) {

				}

				@Override
				public void maximized(boolean isMaximized) {



				}

				@Override
				public void focusLost() {

					EventVar.globalEventManager.focuslost(new GlobalFocusLostEvent(this));
				}

				@Override
				public void focusGained() {

				}

				@Override
				public boolean closeRequested() {
					boolean close = EventVar.globalEventManager.closeprogramm(new GlobalCloseEvent(this));
					return close;

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
