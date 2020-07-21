package de.ft.interitus.datamanager.userdata;


import com.badlogic.gdx.Gdx;
import de.ft.interitus.datamanager.userdata.load.DataLoader;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalFileDropedEvent;

public class UserDataInit {

    public static void init() {
        EventVar.globalEventManager.addListener(new GlobalEventAdapter() {
            @Override
            public boolean filedroped(GlobalFileDropedEvent e, String[] filepaths) {

                String extension = "";

                int i = filepaths[0].lastIndexOf('.');
                int p = Math.max(filepaths[0].lastIndexOf('/'), filepaths[0].lastIndexOf('\\'));

                if (i > p) {
                    extension = filepaths[0].substring(i + 1);
                }

                if (extension != "") {
                    if (extension.contains("itp")) {
                        DataLoader.load(Gdx.files.internal(filepaths[0]), filepaths[0], filepaths[0]); //TODO correct Name
                        return true;
                    }
                }
                return false;

            }
        });
    }
}
