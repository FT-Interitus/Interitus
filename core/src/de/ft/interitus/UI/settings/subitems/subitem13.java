package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
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
    static VisTable scrollingbar = new VisTable();
    public static void add(VisTable builder) {


        scrollingbar.clearChildren();



       if(pluginimage.size()==0) {
            for (int i = 0; i < StorePluginsVar.pluginEntries.size(); i++) {

                pluginimage.add(new VisImage(AssetLoader.storeimages.get(i))); //Hier ist das new ok da hier wirklich ein neues Bild erzeugt werden MUSS

            }
       }
       scrollingbar.padTop(0);

        for(int i=0;i<pluginimage.size();i++) {
            scrollingbar.add(pluginimage.get(i)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);
            scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getDescription())).padLeft(-100).padRight(-200).padTop(-15);
            scrollingbar.row();
        }
        scrollingbar.row();
    //   scrollingbar.add(new VisLabel("PluginStore")).expandX().fillY();

        VisScrollPane scrollPane = new VisScrollPane(scrollingbar);
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);
        builder.add(scrollPane).spaceTop(8).growX().fillX().width(525).height(550).padTop(-60).padBottom(-60).row();

    }
}
