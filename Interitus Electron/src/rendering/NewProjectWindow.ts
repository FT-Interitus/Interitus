import * as electron from "electron";


export function openNewProjectWindow() {

    const win = new electron.remote.BrowserWindow(
        { width: 800, height: 600, webPreferences: {

        nodeIntegration: true,
        enableRemoteModule: true

            },
            resizable: false
        }

    )
    console.log( electron.remote.getGlobal('newProjectWindowFile'))
    win.loadFile( electron.remote.getGlobal('newProjectWindowFile'))
    win.setMenu(null);
    win.show()

}