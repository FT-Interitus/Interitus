package de.ft.robocontrol.UI.setup.steps;

import com.kotcrab.vis.ui.building.TableBuilder;
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.kotcrab.vis.ui.building.utilities.Padding;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;
import de.ft.robocontrol.UI.setup.SetupWindow;

public class Step1 {
    public static VisTextField name=new VisTextField();
    public static CharSequence text="Name: ";
    public static VisLabel namelable=new VisLabel(text);
    public static CharSequence auftragtext="Bitte gebe hier einen Name für die neue Verbindung ein.";
    public static VisLabel auftrag=new VisLabel(auftragtext);
    public Step1(){

    }

    public static void step1(TableBuilder builder){

        builder.append(CellWidget.of(auftrag).expandX().wrap()).row();
        builder.append(CellWidget.of(namelable).expandX().wrap());
        builder.append(CellWidget.of(name).expandX().wrap()).row();

    }
}
