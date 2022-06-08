package gameLogic;


import gameworld.Map;
import knight.TheKnight;

import java.util.Scanner;

/**
 * @author Martin Kadlec
 * @version 0.0.1 , Project started 16.5. 2022
 */
public class Main {
    public static void main(String[] args) {

        Setup.initializeData();
        Setup.printPrologue();
        Scanner input = new Scanner(System.in);

        //TODO:fancy switch
        while (true) {
            System.out.println("Zadej příkaz");
            String command = input.nextLine();

            try {
                switch (command.toLowerCase()) {
                    case "move" -> TheKnight.moveKnight(input);
                    case "cast" -> System.out.println("Yet to implement");
                    case "lookaround" -> System.out.println("Yet to implement");
                    case "loot" -> System.out.println("Yet to implement");
                    case "interact" -> System.out.println("Yet to implement");
                    case "testposition" -> System.out.println(Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical));
                    default -> System.out.println("Neznámý příkaz");
                }
            }
            catch (Exception e) {
                System.out.println("Error");
            }

        }


    }
}