import {app, BrowserWindow, nativeImage, Notification} from "electron";
import * as path from "path";
import {initMenuBar} from "./MenuBar/MenuBarManager";
import {InitProjectManager} from "./ProjectManager";
import {win} from "./rendering/NewProjectWindow";
export let mainWindow: BrowserWindow;


function createWindow() {
  // Create the browser window.
  mainWindow = new BrowserWindow({
    height: 1000,
    width: 1300,
    hasShadow: true,
    icon: path.join(__dirname,"../interitus.png"),
    webPreferences: {
      enableRemoteModule: true,
      nodeIntegration: true
    },

  });
  // and load the index.html of the app.
  mainWindow.maximize()
  mainWindow.loadFile(path.join(__dirname, "../index.html"));

  (<any>global).mainWindow = mainWindow;

}




// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on("ready", () => {
  InitProjectManager();
  createWindow();
  initMenuBar();




  app.on("activate", function () {
    // On macOS it's common to re-create a window in the app when the
    // dock icon is clicked and there are no other windows open.
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

// Quit when all windows are closed, except on macOS. There, it's common
// for applications and their menu bar to stay active until the user quits
// explicitly with Cmd + Q.
app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});

// In this file you can include the rest of your app"s specific main process
// code. You can also put them in separate files and require them here.
