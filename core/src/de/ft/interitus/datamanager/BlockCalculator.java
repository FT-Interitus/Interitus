/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager;

import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.Block.Saving.SaveBlockV1;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.ProjectVar;
import de.ft.interitus.utils.ArrayList;

public class BlockCalculator {
    public static ArrayList<SaveBlockV1> save(final ProjectVar projectVar) {
        ArrayList<SaveBlockV1> saveBlocks = new ArrayList<>();

        for (int i = 0; i < projectVar.blocks.size(); i++) {
            saveBlocks.add(projectVar.blocks.get(i).getBlockToSaveGenerator().generate(projectVar.blocks.get(i), ProjectManager.getActProjectVar()));
        }
        return saveBlocks;
    }


    public static ArrayList<Block> extractV1(ArrayList<SaveBlockV1> saveBlocks) {
        ArrayList<Block> extractedBlocks = new ArrayList<>();
        boolean error = false;
        try {
            for (int i = 0; i < saveBlocks.size(); i++) {


                if (saveBlocks.get(i).getAddon().contentEquals("")) {
                    extractedBlocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(i, saveBlocks.get(i).getX(), saveBlocks.get(i).getY(), ProjectManager.getActProjectVar().projectType.getProjectblocks().get(saveBlocks.get(i).getPlatformspecificblockid()).getWidth(), UIVar.BlockHeight, ProjectManager.getActProjectVar().projectType.getProjectblocks().get(saveBlocks.get(i).getPlatformspecificblockid()), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator(), false));
                } else {

                    Addon tempaddon = null;
                    for (Addon addon : ProjectManager.getActProjectVar().enabledAddons) {
                        if (addon.getName().contentEquals(saveBlocks.get(i).getAddon())) {
                            tempaddon = addon;
                        }
                    }


                    if (tempaddon == null) {

                        Dialogs.showErrorDialog(UI.stage, "Addon Fehler");
                        ProjectManager.closeProject(Var.openprojectindex);


                    }

                    extractedBlocks.add(ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(i, saveBlocks.get(i).getX(), saveBlocks.get(i).getY(), tempaddon.getaddBlocks().get(saveBlocks.get(i).getPlatformspecificblockid()).getWidth(), UIVar.BlockHeight, tempaddon.getaddBlocks().get(saveBlocks.get(i).getPlatformspecificblockid()), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator(), false));

                }


            }

            for (int i = 0; i < saveBlocks.size(); i++) {
                try {


                    if (saveBlocks.get(i).getIndex_right() != -1) {
                        extractedBlocks.get(i).setRight(extractedBlocks.get(saveBlocks.get(i).getIndex_right())); //Set Nachbar right

                        new Wire(extractedBlocks.get(i), extractedBlocks.get(saveBlocks.get(i).getIndex_right()), saveBlocks.get(i).isIsspacebetweenrightblock());

                    }
                    if (saveBlocks.get(i).getIndex_left() != -1) {
                        extractedBlocks.get(i).setLeft(extractedBlocks.get(saveBlocks.get(i).getIndex_left())); //set Nachbar left
                    }


                    if (saveBlocks.get(i).getIndex_right() != -1) { //Wenn der Block einen Rechten Nachbar hat


                    }


                    extractedBlocks.get(i).getBlockType().changeBlockMode(saveBlocks.get(i).getBlockmodus(), extractedBlocks.get(i), true);
                } catch (Exception e) {
                    e.printStackTrace();
                    error = true;
                }

            }
            for (int i = 0; i < saveBlocks.size(); i++) {
                try {
                    //Load BlockSettings
                    try {
                        extractedBlocks.get(i).getBlockType().blockModis.get(extractedBlocks.get(i).getBlockType().actBlockModiIndex).getblocksettings().setSettings(saveBlocks.get(i).getBlocksettings());
                    } catch (NullPointerException e) {

                    }
                    //Load Parameters
                    if (extractedBlocks.get(i).getBlockType().getBlockParameter() != null) {
                        for (int j = 0; j < saveBlocks.get(i).getParameters().size(); j++) {
                            extractedBlocks.get(i).getBlockType().getBlockParameter().get(j).setParameter(saveBlocks.get(i).getParameters().get(j));

                            for (int k = 0; k < saveBlocks.get(i).getDatawires().get(j).size(); k++) {
                                if (saveBlocks.get(i).getDatawires().get(j).get(k) == -1) {
                                    continue;
                                }
                                if (saveBlocks.get(i).getDatawiresindex().get(j).get(k) == -1) {
                                    continue;
                                }
                                DataWire tempwire = new DataWire(extractedBlocks.get(i).getBlockType().getBlockParameter().get(j), extractedBlocks.get(saveBlocks.get(i).getDatawires().get(j).get(k)).getBlockType().getBlockParameter().get(saveBlocks.get(i).getDatawiresindex().get(j).get(k)));

                                try {
                                    tempwire.setVerschiebung_1_Horizontale(saveBlocks.get(i).getDatawiresmoveing().get(j).get(k).get(0));
                                    tempwire.setVerschiebung_2_HorizontaleInput(saveBlocks.get(i).getDatawiresmoveing().get(j).get(k).get(1));
                                    tempwire.setVerschiebung_3_HorizontaleOutput(saveBlocks.get(i).getDatawiresmoveing().get(j).get(k).get(2));
                                    tempwire.setVerschiebung_4_VertikaleInput(saveBlocks.get(i).getDatawiresmoveing().get(j).get(k).get(3));
                                    tempwire.setVerschiebung_5_VertikaleInput(saveBlocks.get(i).getDatawiresmoveing().get(j).get(k).get(4));
                                } catch (Exception e) {


                                }

                            }


                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    error = true;
                }

                if (saveBlocks.get(i).getIncludedBlocks() != null) {

                    extractedBlocks.get(i).setExtendedBlocks(extractV1(saveBlocks.get(i).getIncludedBlocks()));

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(UI.stage, "Fehler beim Laden des Projekts! Alles was wir laden konnten wurde geladen!");
        }

        if (error) {
            Dialogs.showErrorDialog(UI.stage, "Fehler beim Laden des Projekts! Alles was wir laden konnten wurde geladen!");
        }

        return extractedBlocks;

    }
}
