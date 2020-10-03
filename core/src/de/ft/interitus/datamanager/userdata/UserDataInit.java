/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager.userdata;


import com.badlogic.gdx.Gdx;
import de.ft.interitus.datamanager.userdata.load.DataLoader;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalFileDropedEvent;

import java.io.File;


public class UserDataInit {

    public static void init() {
        EventVar.globalEventManager.addListener(new GlobalEventAdapter() {
            @Override
            public boolean filedroped(GlobalFileDropedEvent e, String filepaths) {

                String extension = "";

                int i = filepaths.lastIndexOf('.');
                int p = Math.max(filepaths.lastIndexOf('/'), filepaths.lastIndexOf('\\'));

                if (i > p) {
                    extension = filepaths.substring(i + 1);
                }
            File file =  new File(filepaths);

                if (extension != "") {
                    if (extension.contains("itp")) {
                        DataLoader.load(Gdx.files.internal(filepaths), file.getName(), filepaths);
                        return true;
                    }
                }
                return false;

            }
        });
    }
}
