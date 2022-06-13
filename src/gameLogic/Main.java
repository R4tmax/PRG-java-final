package gameLogic;


import gameworld.Interactions;
import gameworld.Map;
import items.Item;
import knight.*;

import java.util.Scanner;

/**
 * @author Martin Kadlec
 * @version Project started 16.5. 2022, last refactored on 12.6.2022
 *
 * Main function operates as primary programme executor.
 * It uses Scanner object to process stdin and parses it into switch cases.
 * Said Scanner object is passed if further nesting is required for user input.
 * Elementary regex and String operations are used to sanitize the input.
 * Before the switch loop is reached, Setup class methods are invoked to prepare initial data.
 * By design, the class operates with other members of the gameLogic package and TheKnight class,
 * which represents the main character.
 * More data will is available at the relevant methods or the manual.
 *
 * @see Setup
 * @see TheKnight
 * @see gameStateHandler
 * @see Combat
 */
public class Main {
    public static void main(String[] args) {
        //TODO: Docs
        //TODO: Tests
        //TODO: Text colouring
        //TODO: Loggers on errors.
        Setup.initializeData();
        Setup.printPrologue();
        Scanner input = new Scanner(System.in);
        boolean endgameReady = false;


        while (true) {


            if ((Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(), TheKnight.getPosition().getVertical()).getRoomEnemy() != null)){
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

            try {
                switch (command.toLowerCase()) {
                    case "help" -> TheKnight.printCommandListExploration();
                    case "move" -> TheKnight.moveKnight(input);
                    case "lookaround" -> System.out.println(Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getName() + "\n" +Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getDescription());
                    case "status" -> TheKnight.printKnightStatusExploration();
                    case "loot" -> Item.attemptPickup();
                    case "showinventory" -> TheKnight.printInventoryContent();
                    case "useitem" -> TheKnight.useItem(input);
                    case "spelllist" -> Spells.printSpelllist();
                    case "cast" -> Spells.castSpells(input);
                    case "interact" -> Interactions.attemptInteraction(input);
                    case "quitgame" -> {
                        System.out.println("Thanks for playing!");
                        return;
                    }
                    case "testposition" -> System.out.println(Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()));
                    default -> System.out.println("Unknown command, use HELP command if you are lost.");
                }
            }
            catch (Exception e) {
                System.out.println("Input processing error!");
            }

        }


    }
}