/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements.TabBar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Program;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.UIElement;
import de.ft.interitus.UI.newproject.NewProjectWindow;
import de.ft.interitus.Var;
import de.ft.interitus.WindowManager;
import de.ft.interitus.datamanager.userdata.UserInteractDataManagerDialog;
import de.ft.interitus.datamanager.userdata.save.DataSaver;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalTabClickEvent;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.VCS;
import de.ft.interitus.utils.ArrayList;


public class TabBar extends UIElement {

    private int selectedTabindex = 0;
    private final ArrayList<Tab> tabbs = new ArrayList<>();
    private Vector2 mousemerkpos = new Vector2();
    private boolean doonce = false;
    private boolean ismoving = false;
    private Tab home;
    private Tab add;

    public TabBar(int x, int y, int w, int h) {
        super.y = y;
        super.w = w;
        super.x = x;
        super.h = h;
    }

    public TabBar() {
        home = new Tab().setCloseable(false);
        add = new Tab().setCloseable(false);
        home.getTabButton().setText("H");
        add.getTabButton().setText("+");
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
        home.setX(this.x + aktualxpluspos);
        home.setY(this.y);
        home.getTabButton().setW(20);
        home.setH(this.h);

        if (home.getTabButton().isjustPressednormal()) {
            WindowManager.switchto(WindowManager.Windows.welcome);

        }



            aktualxpluspos+=home.getW();
        home.draw(Var.openprojectindex==-1,this.y);


        for (int i = 0; i < this.tabbs.size(); i++) {

            tabbs.get(i).setX(this.x + aktualxpluspos);
            tabbs.get(i).setY(this.y);
            tabbs.get(i).getTabButton().setH(this.h);


            if (doonce == false) {
                //Program.logger.config("moving");
                tabbs.get(selectedTabindex).setX(tabbs.get(selectedTabindex).getX() + (int) (Gdx.input.getX() - mousemerkpos.x));
                //int half=(tabbs.get(selectedTabindex).getTabButton().getW()+tabbs.get(selectedTabindex).getCloseButton().getW()+7)/2;
                //if(tabbs.get(selectedTabindex+1)!=null){
                //if(tabbs.get(selectedTabindex+1).getTabButton().getX()+tabbs.get(selectedTabindex+1).getTabButton().getW()-half < tabbs.get(selectedTabindex).getTabButton().getX()+half){
                //   Program.logger.config("switch");
                // }
                // }
            }


            if (tabbs.get(i).getTabButton().isjustPressednormal()) {
                EventVar.globalEventManager.tabclicked(new GlobalTabClickEvent(this), tabbs.get(i));

                selectedTabindex = i;
                if (doonce = true) {
                    doonce = false;
                    Program.logger.config("doonce");
                    mousemerkpos.set(Gdx.input.getX(), Gdx.input.getY());
                }
            }
            if (!Gdx.input.isButtonPressed(0) && !doonce) {
                doonce = true;
            }


            tabbs.get(i).draw(selectedTabindex == i, this.y);


            if (tabbs.get(i).isCloseable()) {
                aktualxpluspos = aktualxpluspos + tabbs.get(i).getTabButton().getW() + tabbs.get(i).getCloseButton().getW() + 7;
            } else {
                aktualxpluspos = aktualxpluspos + tabbs.get(i).getTabButton().getW() + 7;

            }
            if (tabbs.get(i).getTabButton().isjustPressednormal()) {
                mousemerkpos.set(Gdx.input.getX(), Gdx.input.getY());

                EventVar.globalEventManager.tabclicked(new GlobalTabClickEvent(this), tabbs.get(i));
            }

            if (tabbs.get(i).getCloseButton().isjustPressednormal() && tabbs.get(i).isCloseable()) {
                if (Var.openprojects.get(i).changes) {

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

                                        ProjectManager.closeProject(finalI);
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


                } else {
                    ProjectManager.closeProject(i);

                }

                break;

            }
        }

        add.setX(this.x + aktualxpluspos);
        add.setY(this.y);
        add.getTabButton().setW(20);
        add.setH(this.h);

        if (add.getTabButton().isjustPressednormal()) {
            NewProjectWindow NPW = new NewProjectWindow();
            NPW.show();
        }
        add.draw(false,this.y);

    }



    public void setSelectedTabindex(int selectedTabindex) {
        this.selectedTabindex = selectedTabindex;
    }


    public int getSelectedTabindex() {
        return selectedTabindex;
    }

    public ArrayList<Tab> getTabbs() {
        return tabbs;
    }


}
