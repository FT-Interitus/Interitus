/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.utils;

import com.google.gson.Gson;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Generators.BlockToSaveGenerator;
import de.ft.interitus.Block.Interitus.save.DefaultSaveBlockGenerator;
import de.ft.interitus.datamanager.BlockCalculator;
import de.ft.interitus.datamanager.userdata.save.DataSaver;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipBoard {
    public static StringSelection stringSelection;
    public static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    /**
     * Copyes a String to Clipboard
     * @param s
     */
    public static void copyStringtoClipboard(String s){
        stringSelection=new StringSelection(s);

        clipboard.setContents(stringSelection, null);

    }
    public static void CopyBlocktoClipboard(ArrayList<Block> blocks){
        JSONObject jsonblock = new JSONObject();
        JSONArray jsonblocks = new JSONArray();

        assert ProjectManager.getActProjectVar()!=null;

        Gson gson =new Gson();


        for(Block block:blocks) {


            jsonblocks.put(gson.toJson(ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator().generate(block,ProjectManager.getActProjectVar()).setIndex(blocks.indexOf(block))));
        }

        jsonblock.put("blocks",jsonblocks);

        copyStringtoClipboard(jsonblock.toString());

    }

    public static void PasteBlocks() throws Exception {

        clipboard.getData(DataFlavor.stringFlavor).toString();

    }
}

