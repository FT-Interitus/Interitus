/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.datamanager.programmdata.experience;

public class Level {

    protected static void checkLevel() { //Internal Level System NOT visible to rhe user


        //Level 0 to 1
        if (ExperienceVar.userlevel == 0 && ExperienceVar.programmtimeinhoures > 0.70f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if (ExperienceVar.userlevel == 1 && ExperienceVar.programmtimeinhoures > 1.5f && ExperienceVar.settingstimeinhoures > 0.03f && ExperienceVar.starttimes > 15) {
            ExperienceVar.userlevel = 2;
        }


        //Level 2 to 3
        if (ExperienceVar.userlevel == 2 && ExperienceVar.programmtimeinhoures > 2.5f && ExperienceVar.setuptimeinhoures > 0.09f) {
            ExperienceVar.userlevel = 3;
        }


        //Level 3 to 4
        if (ExperienceVar.userlevel == 3 && ExperienceVar.programmtimeinhoures > 10.0f) {
            ExperienceVar.userlevel = 4;
        }
/*
        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }

        //Level 1 to 2
        if(ExperienceVar.userlevel==0&&ExperienceVar.programmtimeinhoures>1.0f) {
            ExperienceVar.userlevel = 1;
        }


 */

    }
}
