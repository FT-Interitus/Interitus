// This file is required by the index.html file and will
// be executed in the renderer process for that window.
// No Node.js APIs are available in this process unless
// nodeIntegration is set to true in webPreferences.
// Use preload.js to selectively enable features
import * as $ from "jquery"
function initTabBarController() {

    var projectcounter =0;
  const project_tabs =  document.getElementById("project-tabs");



        $(document).on('click', '.projectTabContainer a',function(e) {
            const currentAttrValue = $(this).attr('href');
            if(!$(this).hasClass("project-close")) {

            // Show/Hide Tabs
            if(!$(this).hasClass("cnp-button")) {
                $('.tabs ' + currentAttrValue).show().siblings().hide();

                $('li.active').removeClass('active');

                // Change/remove current tab to active
                $(this).parent().parent('li').addClass('active');

            }else {
                projectcounter++;
                const newtab = document.createElement("li")
                const projectTabContainer = document.createElement("div");
                projectTabContainer.className = "projectTabContainer";
                const atag = document.createElement("a");
                atag.href = "Projekt " + projectcounter
                atag.innerHTML = "Neues Projekt " + projectcounter;
                newtab.insertAdjacentElement('beforeend', atag)
                const closetag = document.createElement("a");
                closetag.href = "#";
                closetag.innerText = "✖";
                closetag.className = "project-close";
                projectTabContainer.insertAdjacentElement('beforeend', atag);
                projectTabContainer.insertAdjacentElement('beforeend', closetag);
                newtab.insertAdjacentElement('beforeend', projectTabContainer);
                $('#project-tabs').append(newtab);


            }
            }else{

                $(this).parent().parent().remove();
            }
            e.preventDefault();
        });


}

// needed in the renderer process.
console.log("I am here")
initTabBarController();