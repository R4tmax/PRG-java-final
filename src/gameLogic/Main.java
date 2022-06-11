package gameLogic;


import gameworld.Interactions;
import gameworld.Map;
import items.Item;
import knight.*;

import java.util.Scanner;

/**
 * @author Martin Kadlec
 * @version 0.0.1 , Project started 16.5. 2022
 */
public class Main {
    public static void main(String[] args) {
        //TODO: Improve encapsulation in general
        //TODO: Docs
        //TODO: Tests
        Setup.initializeData();
        Setup.printPrologue();
        Interactions.initializeDialogues();
        Scanner input = new Scanner(System.in);
        boolean endgameReady = false;


        while (true) {


            if ((Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).roomEnemy != null)){
                Combat.encounter(input);
            }

            if(!gameStateHandler.validateKnightState()) {
                System.out.println("Game over!");
                return;
            } else if (!endgameReady) {
                endgameReady = gameStateHandler.unlockFinalBoss();
            } else {
                if (gameStateHandler.gameWon()) {
                    System.out.println("Your task is done!");
                    System.out.println("Thanks for playing!");
                    return;
                }
            }




            System.out.println(">");
            String command = input.nextLine();
            command = command.replaceAll("\\s","");
            //TODO: extend whitespace strip functionality

            try {
                switch (command.toLowerCase()) {
                    case "move" -> TheKnight.moveKnight(input);
                    case "lookaround" -> System.out.println(Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).name + "\n" +Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).description);
                    case "status" -> TheKnight.printKnightStatusExploration();
                    case "loot" -> Item.attemptPickup();
                    case "showinventory" -> TheKnight.printInventoryContent();
                    case "useitem" -> TheKnight.useItem(input);
                    case "cast" -> Spells.castSpells(input);
                    case "interact" -> Interactions.attemptInteraction(input);
                    case "quitgame" -> {
                        System.out.println("Thanks for playing!");
                        return;
                    }
                    case "testposition" -> System.out.println(Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical));
                    default -> System.out.println("Unknown command, use HELP command if you are lost.");
                }
            }
            catch (Exception e) {
                System.out.println("Input processing error!");
            }

        }


    }
}