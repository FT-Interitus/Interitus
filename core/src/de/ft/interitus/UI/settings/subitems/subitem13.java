package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Settings;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.Var;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.store.StorePluginEntry;
import de.ft.interitus.plugin.store.StorePluginsVar;
import de.ft.interitus.plugin.store.search.findStorePluginEntry;
import de.ft.interitus.utils.DownloadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class subitem13 {
    public static Pixmap saveme = null;

    public static VisTextField search = new VisTextField();
    static ArrayList<VisImage> pluginimage = new ArrayList<>();
   public static VisTable scrollingbar = new VisTable();
    static VisTextButton searchbutton = new VisTextButton("?");
   public static VisScrollPane scrollPane =null;

    public static void add(VisTable builder) {


        scrollingbar.clearChildren();


        if (pluginimage.size() == 0) {
            for (int i = 0; i < AssetLoader.storeimages.size(); i++) {

                pluginimage.add(new VisImage(AssetLoader.storeimages.get(i))); //Hier ist das new ok da hier wirklich ein neues Bild erzeugt werden MUSS

            }
        }
        scrollingbar.padTop(0);

        for (int i = 0; i < pluginimage.size(); i++) {




            scrollingbar.add(pluginimage.get(i)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);





            scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
            scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);
            scrollingbar.row();

                //scrollingbar.getCells().get(i).getActor().setDebug(true);
            /*
            for(int z=0;z<  scrollingbar.getCells().get(0).getTable().getChildren().size;z++) {
                scrollingbar.getCells().get(0).getTable().getChildren().get(z).setDebug(true);
            }

             */

            //TODO hier tests angefangen



        }
        scrollingbar.row();
        //   scrollingbar.add(new VisLabel("PluginStore")).expandX().fillY();

       scrollPane = new VisScrollPane(scrollingbar);
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);

        if(Var.nointernetconnection) {
            scrollingbar.add(new VisLabel("Du hast keine Internet-Verbindung.\n Wenn du eine Verbindung hast bitte das Programm neustarten."));
        }

        final Timer time = new Timer(); //Timer um spätere einträge zu laden

        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                try {

                    if (scrollPane.getScrollPercentY() > 0.3f && AssetLoader.storeimages.size() < StorePluginsVar.pluginEntries.size()) { //Wenn weiter als 50% gescrollt ist und es neue Plugins gibt


                        int old = pluginimage.size(); //Wird die Pluginanzeige abgespeichert


                        //Und es wird durch alle ungeladenen Store einträge durchgegangen und die einträge zu laden

                        for (int i=0; AssetLoader.storeimages.size() != StorePluginsVar.pluginEntries.size();i++ ) {
                            byte[] download = null;
                            try {
                                download = DownloadFile.downloadBytes(StorePluginsVar.pluginEntries.get(AssetLoader.storeimages.size() - 1).getImage());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Pixmap pixmap = new Pixmap(download, 0, download.length);

                            saveme = pixmap; //Pixmap wird abgelegt um Von ProgrammingSpace bearbeitet zu werden


                            Thread.sleep(30); //Warten bis das image dem Array hinzugefügt wurde


                            pluginimage.add(new VisImage(AssetLoader.storeimages.get(AssetLoader.storeimages.size() - 1))); //Hier ist das new ok da hier wirklich ein neues Bild erzeugt werden MUSS


                            //Es wird durch alle durchgegangen und die fehlenden hinzugefügt

                            scrollingbar.add(pluginimage.get(AssetLoader.storeimages.size() - 1)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);
                            scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(AssetLoader.storeimages.size() - 1).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                            scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(AssetLoader.storeimages.size() - 1).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);
                            scrollingbar.row();


                            try {
                                scrollingbar.pack(); //Die liste wird geupdated
                            }catch (NullPointerException e) {

                            }

                            if(i>5) {
                                break;
                            }
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }








                if(SettingsUI.SelectedItem!=12||!SettingsUI.isopend()) { //Damit der Timer nicht unnötig weiterläuft
                    time.cancel();
                }

            }
        }, 0, 500);


        builder.add(new VisLabel("Plugin Store")).padTop(-150).padLeft(-330);

        search.setMessageText(" Plugins suchen");
        search.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {



                if (search.getText() != "" && search.getText() != " " && search.getText().length() > 0&&pluginimage.size()==StorePluginsVar.pluginEntries.size()) {




                    //Lade Porzess wenn schon alle Plugins vorgeladen sind
                    ArrayList<StorePluginEntry> result = findStorePluginEntry.search(search.getText());

                    scrollingbar.clearChildren();

                    scrollingbar.padTop(0);

                    for (int i = 0; i < result.size(); i++) {
                        scrollingbar.add(pluginimage.get(StorePluginsVar.pluginEntries.indexOf(result.get(i)))).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);




                        scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                        scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);

                        scrollingbar.row();
                    }

                    if(result.size()==0) {
                        scrollingbar.add(new VisLabel("Keine Suchergebnisse gefunden"));
                    }
                    scrollingbar.row();
                    scrollingbar.pack();

                }else

                    if(search.getText() != "" && search.getText() != " " && search.getText().length() > 0) {

                    ArrayList<StorePluginEntry> result = findStorePluginEntry.search(search.getText());

                    scrollingbar.clearChildren();
                    scrollingbar.padTop(0);

                    for (int i = 0; i < result.size(); i++) {

                        if(StorePluginsVar.pluginEntries.indexOf(result.get(i))>=pluginimage.size()) {


                           /*
                            byte[] download = null;
                            try {
                                download = DownloadFile.downloadBytes(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getImage());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Pixmap pixmap = new Pixmap(download, 0, download.length);

                            scrollingbar.add(new VisImage( new Texture(pixmap))).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);

                            */

                            scrollingbar.add(new VisImage(AssetLoader.pluginwait)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);


                        }else {
                            scrollingbar.add(pluginimage.get(StorePluginsVar.pluginEntries.indexOf(result.get(i)))).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);
                        }

                        scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                        scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);

                        scrollingbar.row();
                    }
                        if(result.size()==0) {
                            scrollingbar.add(new VisLabel("Keine Suchergebnisse gefunden"));
                        }
                    scrollingbar.row();
                    scrollingbar.pack();
                    scrollPane.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {

                            System.out.println("Hallo hör mich doch");
                        }
                    });

                } else {

                    scrollingbar.clearChildren();
                    scrollingbar.padTop(0);

                    for (int i = 0; i < pluginimage.size(); i++) {
                        scrollingbar.add(pluginimage.get(i)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);


                        scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                        scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);
                        scrollingbar.row();
                    }
                    scrollingbar.row();
                    scrollingbar.pack();
                }

            }
        });


        builder.add(search).padLeft(-200).padTop(-150);





        builder.row();
        builder.add(scrollPane).spaceTop(8).growX().fillX().width(525).height(500).padTop(-60).padBottom(-60).row();

    }

}
