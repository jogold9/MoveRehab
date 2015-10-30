package com.joshbgold.moveRehab.content;

/**
 * Created by JoshG on 7/8/2015.
 */
public class Moves {

    //actions to do
    public String defaultMovementsArray = "walk, squats";  //these are used in the reminder if user does not make any selections in settings

    public String rehabMovements = ""; //these is where the user's selected rehab movements will go;

    public String getMoves(){

        //if rehabMovementsArray is null
        String movements = defaultMovementsArray;

        //else String movements = rehabMovements;
        return movements;
    }
}
