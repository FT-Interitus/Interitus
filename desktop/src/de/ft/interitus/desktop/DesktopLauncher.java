package de.ft.interitus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ft.interitus.Programm;
import de.ft.interitus.Var;

public class DesktopLauncher {
	DesktopLauncher SESSION = this;
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Var.w;
		config.height=Var.h;
		config.vSyncEnabled=false;
		new LwjglApplication(new Programm(), config);



	}


}
