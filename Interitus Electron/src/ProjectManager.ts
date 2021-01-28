import Project from "./Project";
import * as electron from "electron";
import {mainWindow} from "./main";
import * as path from "path";

const projects: Project[] = [];

export function InitProjectManager() {
    (<any>global).projects = {projects: projects};
    (<any>global).ProjectsAdd = (...items:Project[]) => projects.push(...items);
    (<any>global).ProjectsDelete = (id:number) => projects.splice(id,1);
    (<any>global).newProjectWindowFile = path.join(__dirname, "../CreateNewProject.html");

}

export function addProject(name:string) :Project {


    const newProject = new Project(name);
    electron.remote.getGlobal('ProjectsAdd')(newProject)
    return newProject;
}

export function removeProject(id:number) {
    electron.remote.getGlobal('ProjectsDelete')(id);
}


export function getProjets():Project[]{
    console.log( electron.remote.getGlobal('projects').projects)
    return ( electron.remote.getGlobal('projects').projects);

}

