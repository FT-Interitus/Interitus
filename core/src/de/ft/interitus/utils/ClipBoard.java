package de.ft.interitus.utils;

import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;
import de.ft.interitus.projecttypes.ProjectManager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

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
    public static void CopyBlocktoClipboard(PlatformSpecificBlock psb){
        JSONObject jsonblock = new JSONObject();
        JSONArray blockParameter = new JSONArray();
        for(int i=0;i<psb.getBlockParameter().size();i++){
            JSONArray a=new JSONArray();
            a.put(new JSONObject().put("typ",psb.getBlockParameter().get(i).getParameterType().getTyp().getType()));
            a.put(new JSONObject().put("wert",psb.getBlockParameter().get(i).getParameter()));

            blockParameter.put(a);
        }
        jsonblock.put("Parameter", blockParameter);

        copyStringtoClipboard(jsonblock.toString());

    }
}

