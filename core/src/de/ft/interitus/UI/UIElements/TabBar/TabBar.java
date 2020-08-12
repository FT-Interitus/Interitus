/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.TabBar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.CheckMouse;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.userdata.UserInteractDataManagerDialog;
import de.ft.interitus.datamanager.userdata.save.DataSaver;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalFileDropedEvent;
import de.ft.interitus.events.global.GlobalTabClickEvent;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.VCS;
import de.ft.interitus.utils.ArrayList;


public class TabBar {
   private int x = 0;
    private int y = 0;
    private int w = 50;
    private int h = 25;
    private final ArrayList<Tab> tabbs = new ArrayList<>();


    public TabBar(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public TabBar() {

    }


    public void setBounds(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }

    public void setTabs(Tab... tabbs) {
        this.tabbs.clear();
        for (int i = 0; i < tabbs.length; i++) {
            this.tabbs.add(tabbs[i]);
        }
    }

    public void addTab(Tab tabtoadd) {
        this.tabbs.add(tabtoadd);
    }

    public void draw() {
        int aktualxpluspos = 0;
        for (int i = 0; i < this.tabbs.size(); i++) {

            tabbs.get(i).getTabButton().setX(this.x + aktualxpluspos);
            tabbs.get(i).getTabButton().setY(this.y);
            tabbs.get(i).getTabButton().setH(this.h);
            tabbs.get(i).getCloseButton().setX((int) (this.x+aktualxpluspos+tabbs.get(i).getTabButton().getW()+1f));
            tabbs.get(i).getCloseButton().setY((int) (this.y+5f));
            tabbs.get(i).getCloseButton().setH(10);
            tabbs.get(i).getCloseButton().setW(10);
            UI.UIbatch.begin();
            if(CheckMouse.isMouseover(this.x+aktualxpluspos,this.y,tabbs.get(i).getTabButton().getW(),this.h)) {
                UI.UIbatch.setColor(1,1,1,0.7f);
            }else{
                UI.UIbatch.setColor(1,1,1,1);

            }
            UI.UIbatch.draw(AssetLoader.img_Tab,this.x+aktualxpluspos,this.y,tabbs.get(i).getTabButton().getW()+tabbs.get(i).getCloseButton().getW()+7,this.h);

            UI.UIbatch.setColor(1,1,1,1);
            UI.UIbatch.end();
            tabbs.get(i).getTabButton().draw();
            tabbs.get(i).getCloseButton().draw();
            aktualxpluspos = aktualxpluspos + tabbs.get(i).getTabButton().getW()+tabbs.get(i).getCloseButton().getW();
            if (tabbs.get(i).getTabButton().isjustPressednormal()) {
                EventVar.globalEventManager.tabclicked(new GlobalTabClickEvent(this),tabbs.get(i));
            }

            if(tabbs.get(i).getCloseButton().isjustPressednormal()) {

                if(Var.openprojects.get(i).changes) {

                    String[] möglichkeiten = {"Verwerfen", "Speichern", "Abbrechen"};


                    final int nothing = 1;
                    final int everything = 2;
                    final int something = 3;

                    //confirmdialog may return result of any type, here we are just using ints
                    int finalI = i;
                    Dialogs.showConfirmDialog(UI.stage, "Ungespeicherte Änderungen", "\nWenn du das Projekt schliest werden womögich Änderungen verworfen.\n",
                            möglichkeiten, new Integer[]{nothing, everything, something},
                            new ConfirmDialogListener<Integer>() {
                                @Override
                                public void result(Integer result) {
                                    if (result == nothing) {

                                        ProjectManager.CloseProject(finalI);
                                    }

                                    if (result == everything) {
                                        if (ProjectManager.getActProjectVar().vcs == VCS.NONE) {
                                            if (ProjectManager.getActProjectVar().path != "") {
                                                FileHandle handle = Gdx.files.external(ProjectManager.getActProjectVar().path);
                                                DataSaver.save(handle);
                                                //DataManager.saved();
                                            } else {
                                                UserInteractDataManagerDialog.saveas();
                                            }
                                        } else if (ProjectManager.getActProjectVar().vcs == VCS.ITEV) {

                                        }
                                    }

                                    if (result == something) {


                                    }
                                }
                            });





                }else{
                    ProjectManager.CloseProject(i);

                }

                break;

            }
        }

    }

    public ArrayList<Tab> getTabbs() {
        return tabbs;
    }





}
