package com.joshbgold.moveRehab.content;

/**
 * Created by JoshG on 7/8/2015.
 */
public class Moves {

    //actions to do
    public String [] defaultMovementsArray = {  //these are used in the reminder if user does not make any selections in settings
            "walk, squats"
    };

    public String getMoves(){

        //Randomly select a movement from the array of movements
   /*     Random randomGenerator = new Random();  //Construct a new random number generator
        int randomNumber = randomGenerator.nextInt(defaultMovementsArray.length);*/

        String movements = defaultMovementsArray[0];

        return movements;
    }
}
