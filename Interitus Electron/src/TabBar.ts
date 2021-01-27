import * as $ from "jquery";

export function InitTabBar() {
    var projectcounter = 0;
    const project_tabs = document.getElementById("project-tabs");
    const project_tab_container = document.querySelector(".project-list");

    function handleDragStart(e:any) {
        this.style.cursor = "move";
        this.style.opacity = '0.4';
    }

    function handleDragEnd(e:any) {
        this.style.cursor = "pointer";
        this.style.opacity = '1';
    }

    $(document).on('click','.project-tab-container a.project-close', function (e) {



        if ($(this).parent().parent().hasClass("project-active")) {
            $('#project-home-link').addClass("project-active");
        }

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
                projectcounter++;
                const newtab = document.createElement("li")
                const projectTabContainer = document.createElement("div");
                projectTabContainer.className = "project-tab-container";
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

        e.preventDefault();
    });






}