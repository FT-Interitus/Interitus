import * as electron from "electron";
import {generateProjectTab} from "./TabBar";
import {addProject} from "../ProjectManager";
import {mainWindow} from "../main";

const create_button = document.getElementById("newproject-create");
const newProject_name = document.getElementById("newproject-name");



create_button.onclick = function (e) {
    e.preventDefault();
    electron.remote.getGlobal('newprojectwindow').generateProject((<any>newProject_name).value);
    (<electron.BrowserWindow>electron.remote.getGlobal('newprojectwindow').window).close();
}