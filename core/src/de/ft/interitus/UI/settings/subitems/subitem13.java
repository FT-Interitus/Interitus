package de.ft.interitus.UI.settings.subitems;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.*;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.settings.SettingsUI;
import de.ft.interitus.Var;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.store.PluginPage;
import de.ft.interitus.plugin.store.StorePluginEntry;
import de.ft.interitus.plugin.store.StorePluginsVar;
import de.ft.interitus.plugin.store.search.findStorePluginEntry;
import de.ft.interitus.utils.DownloadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class subitem13 {
    private static final ArrayList<VisTable> visTables = new ArrayList<>();
    public static Pixmap saveme = null;
    public static VisTextField search = new VisTextField();
    public static ArrayList<VisImage> pluginimage = new ArrayList<>();
    public static VisTable scrollingbar = new VisTable();
    public static VisScrollPane scrollPane = null;
    static boolean issearching = false; //Um zu verhindern das waährend dem Suchen einträge geladen werden können

    public static void add(final VisTable builder, float percentscrolling) {
        if (!Var.disablePluginSubSystem) {

            visTables.clear();
            scrollingbar.clearChildren();


            if (pluginimage.size() == 0) {
                for (int i = 0; i < AssetLoader.storeimages.size(); i++) {

                    pluginimage.add(new VisImage(AssetLoader.storeimages.get(i))); //Hier ist das new ok da hier wirklich ein neues Bild erzeugt werden MUSS

                }
            }
            scrollingbar.padTop(0);


            for (int i = 0; i < pluginimage.size(); i++) {

                visTables.add(new VisTable());


                visTables.get(i).add(pluginimage.get(i)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);
                visTables.get(i).add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                visTables.get(i).add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);
                visTables.get(i).addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        PluginPage.add(builder, StorePluginsVar.pluginEntries.get(visTables.indexOf(event.getListenerActor())), scrollPane.getScrollPercentY());
                        SettingsUI.SelectedItem = -2;
                    }
                });
                visTables.get(i).row();

                scrollingbar.add(visTables.get(i)).expandX().fillY().expandY().fillX().maxWidth(400);

                scrollingbar.row();

                visTables.get(i).setTouchable(Touchable.enabled);


                visTables.get(i).addListener(new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {

                        System.out.println("So jetztz" + visTables.indexOf(event.getListenerActor()));

                    }
                });


            }
            scrollingbar.row();
            //   scrollingbar.add(new VisLabel("PluginStore")).expandX().fillY();

            scrollPane = new VisScrollPane(scrollingbar);

            scrollPane.setFlickScroll(false);
            scrollPane.setFadeScrollBars(false);

            if (Var.nointernetconnection) {
                scrollingbar.add(new VisLabel("Du hast keine Internet-Verbindung.\n Wenn du eine Verbindung hast bitte das Programm neustarten."));
            }

            final Timer time = new Timer(); //Timer um spätere einträge zu laden

            time.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    try {

                        if (scrollPane.getScrollPercentY() > 0.3f && AssetLoader.storeimages.size() < StorePluginsVar.pluginEntries.size() && !issearching) { //Wenn weiter als 50% gescrollt ist und es neue Plugins gibt


                            int old = pluginimage.size(); //Wird die Pluginanzeige abgespeichert


                            //Und es wird durch alle ungeladenen Store einträge durchgegangen und die einträge zu laden

                            for (int i = visTables.size(); i < StorePluginsVar.pluginEntries.size(); i++) {
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

                                visTables.add(new VisTable());


                                visTables.get(visTables.size() - 1).add(pluginimage.get(AssetLoader.storeimages.size() - 1)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);
                                visTables.get(visTables.size() - 1).add(new VisLabel(StorePluginsVar.pluginEntries.get(AssetLoader.storeimages.size() - 1).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                                visTables.get(visTables.size() - 1).add(new VisLabel(StorePluginsVar.pluginEntries.get(AssetLoader.storeimages.size() - 1).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);
                                visTables.get(visTables.size() - 1).row();
                                visTables.get(visTables.size() - 1).addListener(new ClickListener() {
                                    @Override
                                    public void clicked(InputEvent event, float x, float y) {
                                        PluginPage.add(builder, StorePluginsVar.pluginEntries.get(visTables.indexOf(event.getListenerActor())), scrollPane.getScrollPercentY());
                                        SettingsUI.SelectedItem = -2;
                                    }
                                });
                                scrollingbar.add(visTables.get(visTables.size() - 1)).expandX().fillY().expandY().fillX().maxWidth(400);

                                scrollingbar.row();


                                try {
                                    scrollingbar.pack(); //Die liste wird geupdated
                                } catch (NullPointerException e) {

                                }

                                if (i > StorePluginsVar.loadinglimit) {
                                    break;
                                }
                            }

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if (SettingsUI.SelectedItem != 12 || !SettingsUI.isopend()) { //Damit der Timer nicht unnötig weiterläuft
                        time.cancel();
                    }

                }
            }, 0, 500);

            VisTextButton loadfromfile = new VisTextButton("Aus Datei laden");

            final VisTextButton store = new VisTextButton("Plugin Store", "toggle");
            final VisTextButton installed = new VisTextButton("Installiert", "toggle");
            builder.add(store).padTop(-150).padLeft(-330);
            builder.add(installed).padTop(-150).padLeft(-700);
            store.setChecked(true);
            installed.setChecked(false);

            store.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (!store.isChecked() && !installed.isChecked()) {
                        store.setChecked(true);
                    } else if (installed.isChecked()) {
                        installed.setChecked(false);
                    }

                }
            });

            installed.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (!installed.isChecked() && !store.isChecked()) {
                        installed.setChecked(true);
                    } else if (store.isChecked()) {
                        store.setChecked(false);
                    }

                }
            });

            builder.add(loadfromfile).padBottom(-930).padLeft(-800);


            search.setMessageText(" Plugins suchen");
            search.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {


                    if (search.getText() != "" && search.getText() != " " && search.getText().length() > 0 && pluginimage.size() == StorePluginsVar.pluginEntries.size()) {

                        issearching = true;

                        visTables.clear();


                        //Lade Porzess wenn schon alle Plugins vorgeladen sind
                        ArrayList<StorePluginEntry> result = findStorePluginEntry.search(search.getText());

                        scrollingbar.clearChildren();
                        visTables.clear();

                        scrollingbar.padTop(0);

                        for (int i = 0; i < result.size(); i++) {


                            visTables.add(new VisTable());


                            visTables.get(i).add(pluginimage.get(StorePluginsVar.pluginEntries.indexOf(result.get(i)))).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);
                            visTables.get(i).add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                            visTables.get(i).add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);
                            visTables.get(i).addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    PluginPage.add(builder, StorePluginsVar.pluginEntries.get(visTables.indexOf(event.getListenerActor())), scrollPane.getScrollPercentY());
                                    SettingsUI.SelectedItem = -2;
                                }
                            });
                            visTables.get(i).row();

                            // scrollingbar.add(pluginimage.get(StorePluginsVar.pluginEntries.indexOf(result.get(i)))).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);

                            scrollingbar.add(visTables.get(i)).expandX().fillY().expandY().fillX().maxWidth(400);


                            scrollingbar.row();
                        }

                        if (result.size() == 0) {
                            scrollingbar.add(new VisLabel("Keine Suchergebnisse gefunden"));
                        }
                        scrollingbar.row();
                        scrollingbar.pack();

                    } else if (search.getText() != "" && search.getText() != " " && search.getText().length() > 0) { //Wenn noch nicht alle PLugins geladen worden sind

                        visTables.clear();
                        ArrayList<StorePluginEntry> result = findStorePluginEntry.search(search.getText());
                        issearching = true;
                        scrollingbar.clearChildren();
                        scrollingbar.padTop(0);

                        for (int i = 0; i < result.size(); i++) {
                            visTables.add(new VisTable());
                            if (StorePluginsVar.pluginEntries.indexOf(result.get(i)) >= pluginimage.size()) {


                                //TODO load images

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

                                visTables.get(i).add(new VisImage(AssetLoader.pluginwait)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);


                            } else {
                                visTables.get(i).add(pluginimage.get(StorePluginsVar.pluginEntries.indexOf(result.get(i)))).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);

                            }


                            visTables.get(i).add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);

                            visTables.get(i).add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);
                            visTables.get(i).addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    PluginPage.add(builder, StorePluginsVar.pluginEntries.get(visTables.indexOf(event.getListenerActor())), scrollPane.getScrollPercentY());
                                    SettingsUI.SelectedItem = -2;
                                }
                            });
                            visTables.get(i).row();

                            // scrollingbar.add(pluginimage.get(StorePluginsVar.pluginEntries.indexOf(result.get(i)))).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);


                            //  scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                            // scrollingbar.add(new VisLabel(StorePluginsVar.pluginEntries.get(StorePluginsVar.pluginEntries.indexOf(result.get(i))).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);


                            scrollingbar.add(visTables.get(i)).expandX().fillY().expandY().fillX().maxWidth(400);
                            scrollingbar.row();
                        }
                        if (result.size() == 0) {
                            scrollingbar.add(new VisLabel("Keine Suchergebnisse gefunden"));
                        }
                        scrollingbar.row();
                        scrollingbar.pack();


                    } else {

                        scrollingbar.clearChildren();
                        visTables.clear();
                        scrollingbar.padTop(0);

                        for (int i = 0; i < pluginimage.size(); i++) {

                            visTables.add(new VisTable());

                            visTables.get(i).add(pluginimage.get(i)).padBottom(15).align(Align.left).fillY().padLeft(-200).height(80).width(80);


                            visTables.get(i).add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getName())).padLeft(-100).padRight(0).padTop(-60).padBottom(20);
                            visTables.get(i).add(new VisLabel(StorePluginsVar.pluginEntries.get(i).getDescription())).padLeft(-100).padRight(-200).padTop(-15).align(Align.right);
                            visTables.get(i).row();
                            visTables.get(i).addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    PluginPage.add(builder, StorePluginsVar.pluginEntries.get(visTables.indexOf(event.getListenerActor())), scrollPane.getScrollPercentY());
                                    SettingsUI.SelectedItem = -2;
                                }
                            });

                            scrollingbar.add(visTables.get(i)).expandX().fillY().expandY().fillX().maxWidth(400);

                            scrollingbar.row();
                        }
                        scrollingbar.row();
                        scrollingbar.pack();
                        issearching = false;
                    }

                }
            });


            builder.add(search).padLeft(-200).padTop(-150);


            builder.row();


            UI.stage.setScrollFocus(scrollPane);
            UI.stage.setKeyboardFocus(scrollPane);


            builder.add(scrollPane).spaceTop(8).growX().fillX().width(525).height(500).padTop(-60).padBottom(-60).row();
            //  scrollPane.setScrollPercentY(0.5f); //TODO hier geht etwas nicht
        }else{
            builder.add(new VisLabel("Das Plugin Sub-System läuft nicht!")).expandX().fillY();
        }
    }
}
