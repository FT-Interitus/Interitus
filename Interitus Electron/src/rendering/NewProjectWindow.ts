import * as electron from "electron";
import {mainWindow} from "../main";
import {dialog} from "electron";

export let win: Electron.BrowserWindow;

export function openNewProjectWindow() {

    win = new electron.remote.BrowserWindow(
        {
            width: 800, height: 600, webPreferences: {

                nodeIntegration: true,
                enableRemoteModule: true

            },
            resizable: false,
            parent: electron.remote.getGlobal('mainWindow'),
            modal: true
        }
    );
    win.loadFile( electron.remote.getGlobal('newProjectWindowFile'))
    win.setMenu(null);



}