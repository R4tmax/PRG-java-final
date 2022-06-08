package knight;


import gameworld.Map;
import gameworld.Room;

import java.util.Scanner;

public class TheKnight {
    public static int health = 200;
    public static int mana = 50;
    public static int armor = 0;
    public static int goldHeld = 10;
    public static KnightCoordinates position = new KnightCoordinates(4,2);


    public static KnightCoordinates getPosition() {
        return position;
    }


    public static void moveKnight (Scanner input) {

        System.out.println("In which compass direction do you want to go?");
        String direction = input.nextLine();


//TODO: should somehow immediately validate if the room I am trying to enter is legit
            try {
                switch (direction.toLowerCase()) {
                    case "north": TheKnight.position.horizontal -=1;
                    break;
                    case "west": TheKnight.position.vertical -=1;
                        break;
                    case "east": TheKnight.position.vertical +=1;
                        break;
                    case "south": TheKnight.position.horizontal +=1;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error");
            }


    }



}
