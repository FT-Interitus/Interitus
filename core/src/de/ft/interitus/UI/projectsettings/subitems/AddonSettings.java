/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.projectsettings.subitems;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import de.ft.interitus.Block.Block;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.projecttypes.BlockTypes.ProjectTypesVar;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.Tools;
import de.ft.interitus.utils.ArrayList;


public class AddonSettings {
    private static final ArrayList<VisCheckBox> checkBoxArrayList = new ArrayList<>();

    public static void add(VisTable builder) {

        Skin skin = VisUI.getSkin();

        VisTable table = new VisTable();
        checkBoxArrayList.clear();

        for (Addon addon : ProjectTypesVar.addons) {

            if (addon.getProjectTypebyName().contentEquals(ProjectManager.getActProjectVar().projectType.getName())) {
                checkBoxArrayList.add(new VisCheckBox(addon.getName()));
                if (ProjectManager.getActProjectVar().enabledAddons.contains(addon)) {
                    checkBoxArrayList.getLastObject().setChecked(true);
                }
                table.add(checkBoxArrayList.getLastObject()).expand().fill().row();

            }


        }

        ScrollPane scrollPane = new ScrollPane(table, skin, "list");
        scrollPane.setFlickScroll(false);
        scrollPane.setFadeScrollBars(false);
        builder.add(scrollPane).spaceTop(8).fillX().expandX().row();
        VisTextButton submit = new VisTextButton("Übernehmen");
        builder.add(submit).spaceTop(10).expandX().fillY().row();

        submit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {

                    ProjectManager.getActProjectVar().enabledAddons.clear();
                    for (VisCheckBox checkBox : checkBoxArrayList) {

                        if (checkBox.isChecked()) {
                            if (!ProjectManager.getActProjectVar().enabledAddons.contains(ProjectTypesVar.addons.get(checkBoxArrayList.indexOf(checkBox)))) {
                                ProjectManager.getActProjectVar().enabledAddons.add(ProjectTypesVar.addons.get(checkBoxArrayList.indexOf(checkBox)));
                            }
                        } else {

                            ArrayList<Block> deleteblocks = new ArrayList<>();
                            for (Block block : ProjectManager.getActProjectVar().blocks) {
                                if (ProjectTypesVar.addons.get(checkBoxArrayList.indexOf(checkBox)) == block.getBlocktype().getAdddon()) {
                                    deleteblocks.add(block);

                                }
                            }

                            while (deleteblocks.size() > 0) {
                                deleteblocks.get(0).delete(false);
                                deleteblocks.remove(deleteblocks.get(0));
                            }


                        }

                    }
                    BlockTappedBar.init();
                    Tools.update();

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
