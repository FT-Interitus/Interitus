/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.ThreadManager;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.BlockCalculator;
import de.ft.interitus.loading.AssetLoader;

import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class RefreshProgramm {
    public static void refresh() {
        UIVar.isdialogeopend = true;

ThreadManager.stopall();


        ArrayList<SaveBlock> saveBlocks = BlockCalculator.save();


        ClearActOpenProgramm.clear(Var.openprojectindex);

        Notification notification = new Notification(AssetLoader.information,"Projekt Update","Das Projekt wird neu erstellt.").setCloseable(false);

        NotificationManager.sendNotification(notification);
        int progress = 0;

        while(Var.isclearing) {
            if(progress==100) {
                progress=0;
            }
            notification.setProgressbarvalue(progress);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress++;
        }




        BlockCalculator.extract(saveBlocks);
        notification.close();
        UIVar.isdialogeopend = false;

    }
}
