import {dialog, Menu, MenuItem, BrowserWindow, nativeImage, remote} from "electron";
import * as electron from "electron";
import * as path from "path";
import {mainWindow} from "../main";

export function initMenuBar() {

//TODO Shortcuts!!!

    const menu = new Menu();

    const file_submenu = new Menu();
    const file = new MenuItem({label: 'Datei', submenu: file_submenu});

    const new_project_submenu = new Menu();
    const new_project = new MenuItem({label: 'Neu',submenu: new_project_submenu});
    const new_project_scratch = new MenuItem({label: 'Neues Projekt'});
    const new_project_import = new MenuItem({label: 'Import Projekt'});

    file_submenu.append(new_project);

    new_project_submenu.append(new_project_scratch);
    new_project_submenu.append(new_project_import);

    const recent_sub_menu = new Menu();
    const recent = new MenuItem({label: "Letzte Öffnen",submenu: recent_sub_menu});
    file_submenu.append(recent);

    const open = new MenuItem({label: "Öffnen", accelerator: 'CommandOrControl+O'});
    file_submenu.append(open);

    const save = new MenuItem({label: "Speichern",accelerator: 'CommandOrControl+S'});
    file_submenu.append(save);

    const saveas = new MenuItem({label: "Speichern unter"});
    file_submenu.append(saveas);

    file_submenu.append(new MenuItem({type: "separator"}));

    const settings = new MenuItem({label: "Einstellungen"});
    file_submenu.append(settings);

    const exit = new MenuItem({label: "Beenden"});
    file_submenu.append(exit);



    const edit_submenu = new Menu();
    const edit = new MenuItem({label: 'Bearbeiten',submenu: edit_submenu});

    const undo = new MenuItem({label: 'Rückgänig',accelerator: 'CommandOrControl+Z'});
    edit_submenu.append(undo);

    const redo = new MenuItem({label: 'Wiederherstellen',accelerator: 'CommandOrControl+Y'});
    edit_submenu.append(redo);

    edit_submenu.append(new MenuItem({type: "separator"}));

    const copy =  new MenuItem({label: 'Kopieren'});
    edit_submenu.append(copy);

    const cut = new MenuItem({label: 'Ausschneiden'});
    edit_submenu.append(cut);

    const insert = new MenuItem({label: 'Einfügen'});
    edit_submenu.append(insert);



    const view_submenu = new Menu();
    const view = new MenuItem({label: 'Ansicht',submenu: view_submenu});

    const fullscreen = new MenuItem({label: 'Vollbild',  accelerator: 'F11'});
    view_submenu.append(fullscreen);

    const help_submenu = new Menu();
    const help = new MenuItem({label: 'Hilfe',submenu: help_submenu});

    const check_update = new MenuItem({label: 'Updates..'});
    help_submenu.append(check_update);

    const about = new MenuItem({label: 'Über',click: () => {


            electron.dialog.showMessageBox({
                title: 'Über Interitus',
                 message: 'Interitus V0.2',
                detail: "Interitus von Tim und Felix",
                 buttons: ["OK"],
                icon: nativeImage.createFromPath(path.join(__dirname,"../../interitus.png"))
                });


        }});

    help_submenu.append(about);

    const dev_tools = new MenuItem({label: 'Dev Tools',click: ()=>{mainWindow.webContents.toggleDevTools()}});
    help_submenu.append(dev_tools);


    menu.append(file)
    menu.append(edit)
    menu.append(view)
    menu.append(help)

    Menu.setApplicationMenu(menu)

}