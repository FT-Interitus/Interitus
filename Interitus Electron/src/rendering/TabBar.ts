import * as $ from "jquery";
import Project from "../Project";
import {addProject, getProjets, removeProject} from "../ProjectManager";
import { openNewProjectWindow } from "./NewProjectWindow";

export function InitTabBar() {
    const project_tabs = document.getElementById("project-tabs");
    const project_tab_container = document.querySelector(".project-list");

    for(const project of getProjets()) {
        generateProjectTab(project.name);

    }




    $(document).on('click','.project-tab-container a.project-close', function (e) {



        if ($(this).parent().parent().hasClass("project-active")) {
            $('#project-home-link').addClass("project-active");
        }


        console.log($(this).parent().parent().index())
        removeProject($(this).parent().parent().index());
        $(this).parent().parent().remove();




    });

    $(document).on('click', 'div.project-tab-container', function (e) {
        const currentAttrValue = $(this).children("a,p").attr('href');


            // Show/Hide Tabs
            if (!$(this).children("a,p").hasClass("project-cnp-button")) {
                if(e.target.nodeName!="A") {
                    $('.tabs ' + currentAttrValue).show().siblings().hide();

                    $('li.project-active').removeClass('project-active');

                    // Change/remove current tab to active
                    $(this).parent('li').addClass('project-active');
                }

            } else {

               // generateProjectTab( addProject("Neues Projekt "+(getProjets().length+1)).name);
                openNewProjectWindow();
            }

        e.preventDefault();
    });


}


function handleDragStart(e:any) {
    this.style.cursor = "move";
    this.style.opacity = '0.4';
}

function handleDragEnd(e:any) {
    this.style.cursor = "pointer";
    this.style.opacity = '1';
}

export function generateProjectTab(label:string) {
    const newtab = document.createElement("li")
    const projectTabContainer = document.createElement("div");
    projectTabContainer.className = "project-tab-container";
    projectTabContainer.draggable = true;
    projectTabContainer.addEventListener('dragstart', handleDragStart, false);
    projectTabContainer.addEventListener('dragend', handleDragEnd, false);
    const ptag = document.createElement("p");
    ptag.innerHTML = label;
    ptag.style.display = "inline";
    newtab.insertAdjacentElement('beforeend', ptag)
    const closetag = document.createElement("a");
    closetag.innerText = "x";
    closetag.className = "project-close";
    closetag.style.display = "inline";
    projectTabContainer.insertAdjacentElement('beforeend', ptag);
    projectTabContainer.insertAdjacentElement('beforeend', closetag);
    newtab.insertAdjacentElement('beforeend', projectTabContainer);
    $('#project-tabs').append(newtab);
}