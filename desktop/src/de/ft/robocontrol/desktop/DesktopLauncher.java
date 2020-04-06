package de.ft.robocontrol.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Programm;
import de.ft.robocontrol.Var;
import org.lwjgl.opengl.Display;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Var.w;
		config.height=Var.h;
		config.vSyncEnabled=false;
		new LwjglApplication(new Programm(), config);
	}
}
