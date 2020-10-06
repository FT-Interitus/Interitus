/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.Block.SaveBlock;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.utils.ArrayList;

public class BlockCalculator {
    public static ArrayList<SaveBlock> save() {
        ArrayList<SaveBlock> saveBlocks = new ArrayList<>();

        for (int i = 0; i < ProjectManager.getActProjectVar().blocks.size(); i++) {
            saveBlocks.add(ProjectManager.getActProjectVar().blocks.get(i).getBlocktoSaveGenerator().generate(ProjectManager.getActProjectVar().blocks.get(i)));
        }
        return saveBlocks;
    }

    public static void extract(ArrayList<SaveBlock> saveBlocks) {
        boolean error = false;
        try {
            for (int i = 0; i < saveBlocks.size(); i++) {



                    if (saveBlocks.get(i).getAddon().contentEquals("")) {
                        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(i, saveBlocks.get(i).getX(), saveBlocks.get(i).getY(), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(saveBlocks.get(i).getPlatformspecificblockid()).getWidth(), UIVar.BlockHeight, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(saveBlocks.get(i).getPlatformspecificblockid()), ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));
                    } else {

                        Addon tempaddon = null;
                        for (Addon addon : ProjectManager.getActProjectVar().enabledAddons) {
                            if (addon.getName().contentEquals(saveBlocks.get(i).getAddon())) {
                                tempaddon = addon;
                            }
                        }


                        if (tempaddon == null) {

                            Dialogs.showErrorDialog(UI.stage, "Addon Fehler");
                            ProjectManager.CloseProject(Var.openprojectindex);


                        }

                        ProjectManager.getActProjectVar().blocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(i, saveBlocks.get(i).getX(), saveBlocks.get(i).getY(), tempaddon.getaddBlocks().get(saveBlocks.get(i).getPlatformspecificblockid()).getWidth(), UIVar.BlockHeight, tempaddon.getaddBlocks().get(saveBlocks.get(i).getPlatformspecificblockid()), ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator()));

                    }



            }

            for (int i = 0; i < saveBlocks.size(); i++) {
                try {


                    if (saveBlocks.get(i).getIndex_rechts() != -1) {
                        ProjectManager.getActProjectVar().blocks.get(i).setRight(ProjectManager.getActProjectVar().blocks.get(saveBlocks.get(i).getIndex_rechts())); //Set Nachbar rechts
                    }
                    if (saveBlocks.get(i).getIndex_links() != -1) {
                        ProjectManager.getActProjectVar().blocks.get(i).setLeft(ProjectManager.getActProjectVar().blocks.get(saveBlocks.get(i).getIndex_links())); //set Nachbar links
                    }


                    if (saveBlocks.get(i).getIndex_rechts() != -1) { //Wenn der Block einen Rechten Nachbar hat

                        if (saveBlocks.get(i).getNodes() == null) {

                            ProjectManager.getActProjectVar().wires.add(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(ProjectManager.getActProjectVar().blocks.get(i), ProjectManager.getActProjectVar().blocks.get(i).getRight())); //Eine Wire mit den entsprechenen Blöcken wird erstellt
                            ProjectManager.getActProjectVar().blocks.get(i).setWire_right(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1)); // Der Rechte Block bekommt die Wire zugeteilt
                            ProjectManager.getActProjectVar().blocks.get(i).getRight().setWire_left(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));// Der Linke Block bekommt die Wire zugeteilt
                            ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1).setSpace_between_blocks(saveBlocks.get(i).isIsspacebetweenrightblock()); //Die Wire wird sichtbar gemacht

                        } else {
                            for (int j = 0; j < saveBlocks.get(i).getNodes().size(); j++) {
                                if (j == 0) {
                                    ProjectManager.getActProjectVar().wires.add(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(ProjectManager.getActProjectVar().blocks.get(i)));
                                    ProjectManager.getActProjectVar().blocks.get(i).setWire_right(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1)); // Der linke Block bekommt die Wire zugeteilt

                                } else {
                                    ProjectManager.getActProjectVar().wires.add(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1)));
                                    ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1).setWire_right(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));
                                }
                                ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1).setSpace_between_blocks(true);


                                ProjectManager.getActProjectVar().wireNodes.add(ProjectManager.getActProjectVar().projectType.getWireNodeGenerator().generate(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1), saveBlocks.get(i).getNodes().get(j).get(0), saveBlocks.get(i).getNodes().get(j).get(1), saveBlocks.get(i).getNodes().get(j).get(2), saveBlocks.get(i).getNodes().get(j).get(3)));

                                ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1).setRight_connection(ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1));

                                ProjectManager.getActProjectVar().visible_wires.add(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));


                            }
                            ProjectManager.getActProjectVar().wires.add(ProjectManager.getActProjectVar().projectType.getWireGenerator().generate(ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1), ProjectManager.getActProjectVar().blocks.get(saveBlocks.get(i).getIndex_rechts())));
                            ProjectManager.getActProjectVar().wireNodes.get(ProjectManager.getActProjectVar().wireNodes.size() - 1).setWire_right(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));
                            ProjectManager.getActProjectVar().blocks.get(i).getRight().setWire_left(ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1));// Der rechte Block bekommt die Wire zugeteilt
                            ProjectManager.getActProjectVar().wires.get(ProjectManager.getActProjectVar().wires.size() - 1).setSpace_between_blocks(true);


                        }
                    }


                    ProjectManager.getActProjectVar().blocks.get(i).getBlocktype().changeBlockModus(saveBlocks.get(i).getBlockmodus(), ProjectManager.getActProjectVar().blocks.get(i), true);
                }catch (Exception e) {
                    error = true;
                }

            }
            for (int i = 0; i < saveBlocks.size(); i++) {
                try {
                    //Load BlockSettings
                    try {
                        ProjectManager.getActProjectVar().blocks.get(i).getBlocktype().blockModis.get(ProjectManager.getActProjectVar().blocks.get(i).getBlocktype().actBlockModiIndex).getblocksettings().setSettings(saveBlocks.get(i).getBlocksettings());
                    } catch (NullPointerException e) {

                    }
                    //Load Parameters
                    if (ProjectManager.getActProjectVar().blocks.get(i).getBlocktype().getBlockParameter() != null) {
                        for (int j = 0; j < saveBlocks.get(i).getParameters().size(); j++) {
                            ProjectManager.getActProjectVar().blocks.get(i).getBlocktype().getBlockParameter().get(j).setParameter(saveBlocks.get(i).getParameters().get(j));

                            for (int k = 0; k < saveBlocks.get(i).getDatawires().get(j).size(); k++) {
                                if (saveBlocks.get(i).getDatawires().get(j).get(k) == -1) {
                                    continue;
                                }
                                if (saveBlocks.get(i).getDatawiresindex().get(j).get(k) == -1) {
                                    continue;
                                }
                                new DataWire(ProjectManager.getActProjectVar().blocks.get(i).getBlocktype().getBlockParameter().get(j), ProjectManager.getActProjectVar().blocks.get(saveBlocks.get(i).getDatawires().get(j).get(k)).getBlocktype().getBlockParameter().get(saveBlocks.get(i).getDatawiresindex().get(j).get(k)));
                            }

                        }


                    }

                }catch (Exception e) {
                    error = true;
                }
            }


        }catch (Exception e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(UI.stage,"Fehler beim Laden des Projekts! Alles was wir laden konnten wurde geladen!");
        }

        if(error) {
            Dialogs.showErrorDialog(UI.stage,"Fehler beim Laden des Projekts! Alles was wir laden konnten wurde geladen!");
        }
    }
}
