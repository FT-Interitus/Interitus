package de.ft.robocontrol.desktop;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplet;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.audio.OpenALAudio;
import de.ft.robocontrol.MainGame;
import de.ft.robocontrol.Programm;
import de.ft.robocontrol.Var;
import de.ft.robocontrol.data.user.LoadSave;
import de.ft.robocontrol.loading.AssetLoader;
import de.ft.robocontrol.loading.Loading;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;



public class DesktopLauncher {

	public static void main (String[] arg) {



		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Var.w;
		config.height=Var.h;
		config.vSyncEnabled=false;
		new LwjglApplication(new Programm(), config);



	}

}
