/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus;

import de.ft.interitus.Block.BlockDrawer;
import de.ft.interitus.UI.CheckShortcuts;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.PressedKeys;
import de.ft.interitus.UI.UIElements.UIElementBar;
import de.ft.interitus.UI.UIElements.UIElements.Button;
import de.ft.interitus.UI.UIElements.UIElements.table.Column;
import de.ft.interitus.UI.UIElements.UIElements.table.Row;
import de.ft.interitus.UI.UIElements.UIElements.table.Table;
import de.ft.interitus.UI.popup.PopupHandler;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.datamanager.programmdata.Updater;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.plugin.*;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ProgrammArea.ProgrammAreaManager;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.BlockCamera;
import de.ft.interitus.utils.Input;
import de.ft.interitus.utils.PositionSaver;


import java.awt.*;
import java.awt.event.KeyEvent;


public class ProgrammingSpace {

    public static BlockCamera blockCamera;
    public static Component saver;


    public static long renderstarttime = 0;
    public static long rendertimediff = 0;
    public static long rendersleeptime = 0;


    public static PressedKeys pressedKeys;
    public static float delta;
    public boolean loadimagesfromplugin = true;
    public static Plugin nativ = new Native();
    public static Table testtable=new Table(100,200,1,1);


    public ProgrammingSpace() {
        testtable.addColumn(new Column(100));
        testtable.addColumn(new Column(50));
        testtable.addColumn(new Column(100));
        testtable.addRow(new Row(100));
        testtable.getColumn(0).setWeight(10);




        pressedKeys = new PressedKeys();




//TODO Debug hier wird immer ein Arduino Project erstellt
        ProjectManager.addProject(ProjectTypesVar.projectTypes.get(0).init());
        ProjectManager.change(0);

        //TODO L UI.updatedragui(shapeRenderer, true, batch);
        ProjectManager.getActProjectVar().projectType.initProject();


        BlockTappedBar.init();


        de.ft.interitus.UI.Viewport.init();

        //TODO L  Gdx.graphics.setTitle("New File");
        ProjectManager.getActProjectVar().setFilename("New File");


        ThreadManager.init();


        System.gc(); //Clean RAM after Loading

    Updater.initprogress();




    }




    public void render(float delta) {

if(ProjectManager.getActProjectVar().markedblock!=null) {
    ProgrammAreaManager.getProgrammArea(ProjectManager.getActProjectVar().markedblock.getIndex());
}
        ProgrammingSpace.delta = delta;


        renderstarttime = System.currentTimeMillis();

        if (Var.openprojects.size() != 0 && ProjectManager.getActProjectVar().projectType == null) {
            //TODO L  Programm.INSTANCE.setScreen(new Welcome());
        }

        //RechtsKlick.Rechtsklickupdate();

        try {

            //logger.finest("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());

            PositionSaver.save();





            //TODO L    UI.updatedragui(shapeRenderer, true, batch);

            testtable.draw();
            BlockDrawer.Draw();


            de.ft.interitus.UI.Viewport.update(delta);


        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();
        }


        if(!loadimagesfromplugin) {
            PluginDrawer.draw();
        }


        NotificationManager.draw();
        try {
            UI.update();

        } catch (Exception e) {
            //Falls die UI nicht richtig initialisiert werden konnte
            DisplayErrors.customErrorstring = "Fehler in der UI";
            DisplayErrors.error = e;

        }




        if (Input.isKeyPressed(KeyEvent.VK_C)) {
          Notification notification =  new Notification(AssetLoader.information, "Wichtige Information", "\nEs steht kein Update bereit!");
          notification.setButtonBar(new UIElementBar().addButton(new Button().setText("Test")));
            NotificationManager.sendNotification(notification);
        }

        PopupHandler.drawPopUp();




        try {
            DisplayErrors.checkerror(); //Check if there are undisplayed Errors
        } catch (IllegalStateException e) {
            //Bei eienem VisUI absturz
        }

        loader(); //Load Images in OpenGL context


        de.ft.interitus.UI.Viewport.limitfps();


    }


    public void resize(int width, int height) {


        //TODO weite setzen
        try {
            UI.updateView(width, height);
        } catch (NullPointerException e) { //Falls die UI nicht initialisiert werden konnte

        }
        //TODO L   viewport.update(width, height);
        //TODO L   UI.UIviewport.update(width, height);


    }



    public void loader() {



        //Um alle shortcuts für das Programm zu überprüfen
        CheckShortcuts.check();


        //Import all Plugin Images
        if (loadimagesfromplugin) { //Import all

            PluginDrawer.loadimages();

            loadimagesfromplugin = false; //

            PluginManagerHandler.init();

        }
    }


}
