package de.ft.robocontrol.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ft.robocontrol.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height=800;
		config.width=1300;
		config.vSyncEnabled=false;
		new LwjglApplication(new MainGame(), config);
	}
}
