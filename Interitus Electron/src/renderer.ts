// This file is required by the index.html file and will
// be executed in the renderer process for that window.
// No Node.js APIs are available in this process unless
// nodeIntegration is set to true in webPreferences.
// Use preload.js to selectively enable features
import * as $ from "jquery"


function initTabBarController() {
    var projectcounter = 0;
    const project_tabs = document.getElementById("project-tabs");
    const project_tab_container = document.querySelector(".tab-container");

    function handleDragStart(e:any) {
        this.style.cursor = "move";
        this.style.opacity = '0.4';
    }

    function handleDragEnd(e:any) {
        this.style.cursor = "pointer";
        this.style.opacity = '1';
    }

    $(document).on('click', '.projectTabContainer', function (e) {
        const currentAttrValue = $(this).children("a,p").attr('href');
        if (!$(this).hasClass("project-close")) {

            // Show/Hide Tabs
            if (!$(this).children("a,p").hasClass("cnp-button")) {
                $('.tabs ' + currentAttrValue).show().siblings().hide();

                $('li.active').removeClass('active');

                // Change/remove current tab to active
                $(this).parent('li').addClass('active');

            } else {
                projectcounter++;
                const newtab = document.createElement("li")
                const projectTabContainer = document.createElement("div");
                projectTabContainer.className = "projectTabContainer";
                projectTabContainer.draggable = true;
                projectTabContainer.addEventListener('dragstart', handleDragStart, false);
                projectTabContainer.addEventListener('dragend', handleDragEnd, false);
                const ptag = document.createElement("p");
                ptag.innerHTML = "Neues Projekt " + projectcounter;
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
        } else {

            if ($(this).parent().hasClass("active")) {
                console.log("active state")
                $('#project-home-link').addClass("active");
            }

            $(this).parent().remove();
        }
        e.preventDefault();
    });
        




}

// needed in the renderer process.
console.log("I am here")
initTabBarController();