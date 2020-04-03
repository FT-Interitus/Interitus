package de.ft.robocontrol.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ft.robocontrol.MainGame;
import org.lwjgl.opengl.Display;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1300;
		config.height=800;
		config.vSyncEnabled=false;
		new LwjglApplication(new MainGame(), config);
	}
}
