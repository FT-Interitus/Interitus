package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.store.StorePluginsVar;
import de.ft.interitus.utils.DownloadFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class subitem13 {
    static ArrayList<VisImage> pluginimage = new ArrayList<>();
    public static void add(VisTable builder) {
        int counter;
        if(StorePluginsVar.pluginEntries.size()<=10) {
            counter = StorePluginsVar.pluginEntries.size();
        }else{
            counter = 10;
        }
        System.out.println(StorePluginsVar.pluginEntries.size() +" that the size");


        for(int i = 0;i<counter;i++) {

                pluginimage.add(new VisImage(AssetLoader.storeimages.get(i))); //Hier ist das new ok da hier wirklich ein neues Bild erzeugt werden MUSS

        }

        for(int i=0;i<pluginimage.size();i++) {
            builder.add(pluginimage.get(i)).expandX().fillY();
            builder.row();
        }
        builder.row();
       builder.add(new VisLabel("PluginStore")).expandX().fillY();
    }
}
