package knight;


import gameworld.Map;
import gameworld.Room;

import java.util.Scanner;

import static gameworld.Map.getCurrentPosition;

public class TheKnight {
    public static int health = 200;
    public static int mana = 50;
    public static int armor = 2;
    public static int damage = 15;
    public static int goldHeld = 100;
    public static KnightCoordinates position = new KnightCoordinates(4,2);
    public static boolean isDead = false;
    public static KnightCoordinates getPosition() {
        return position;
    }


    public static void moveKnight (Scanner input) {

        int tmpHorizontal = TheKnight.position.horizontal;
        int tmpVertical = TheKnight.position.vertical;
        System.out.println("In which compass direction do you want to go?");
        String direction = input.nextLine();

            try {
                switch (direction.toLowerCase()) {
                    case "north" -> TheKnight.position.horizontal -= 1;
                    case "west" -> TheKnight.position.vertical -= 1;
                    case "east" -> TheKnight.position.vertical += 1;
                    case "south" -> TheKnight.position.horizontal += 1;
                }
            } catch (Exception e) {
                System.out.println("Error");
            }

        validateMove(tmpHorizontal,tmpVertical);

    }

    private static void validateMove (int tmpX, int tmpY) {
        try {
            if (getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).isLocked) {
                System.out.println("Máš pocit, že bys tady ještě neměl být, zkus prozkoumat jinou část oblasti!");
                TheKnight.position.horizontal = tmpX;
                TheKnight.position.vertical = tmpY;
            }
        } catch (Exception e) {
            System.out.println("Opouštíš herní plochu, rozhlédni se a zkus jiný směr!");
            TheKnight.position.horizontal = tmpX;
            TheKnight.position.vertical = tmpY;
        }


    }


    public static void printKnightStatusExploration () {
        System.out.println("Momentální zdraví:" + TheKnight.health);
        if (TheKnight.mana > 0) System.out.println("Momentální mana: " + TheKnight.mana);
        else System.out.println("Nemáš žádnou manu!");
        System.out.println("Tvůj base damage je:" + TheKnight.damage);
        System.out.println("Tvůj armor je:" + TheKnight.armor);
        System.out.println("Neseš " + TheKnight.goldHeld + " zlaťáků.");
    }
    public static void printKnightStatusCombat () {
        System.out.println("Momentální zdraví:" + TheKnight.health);
        if (TheKnight.mana > 0) System.out.println("Momentální mana: " + TheKnight.mana);
        else System.out.println("Nemáš žádnou manu!");
        System.out.println("Tvůj base damage je:" + TheKnight.damage);
        System.out.println("Tvůj armor je:" + TheKnight.armor);
    }

}
