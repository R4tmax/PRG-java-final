package gameLogic;

import auxiliary.Auxiliary;
import enemies.*;
import gameworld.Map;
import knight.*;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022
 * <p>
 *     Combat class facilitates the interactions caused
 *     by Main Character and some enemy monster occupying the same
 *     room on the gameMap.
 * </p>
 *
 * <p>
 *     In general terms. Scanner object is passed from the main
 *     class to process input, and further actions are invoked
 *     based on the input
 *  </p>
 * @see Main
 * @see gameworld.Room
 * @see Map
 * @see TheKnight
 */
public class Combat extends Thread {

    /**
     * Encounter method provides the framework for the turn based combat of the game.
     * On even turns, player is asked to provide input based on selected set of commands.
     * Generally, this will invoke some sort of method from knight package
     * On odd turns, monster attackPattern method will be invoked.
     * Encounter also uses Random object to generate "dice rolls"
     * to make combat more dynamic.
     * @param input Scanner object passed from the main class.
     *
     * @see TheKnight
     * @see TheKnight#resolveAttack(int, Monster)
     * @see HostileActions
     *
     */
    public static void encounter (Scanner input) {
        int turnCounter = 0;
        int damageValue;
        String command;
        Random damageRoll = new Random();
        Monster enemyPresent = Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomEnemy();
        enemyPresent.initialMessage();

       while (true) {
           damageValue = damageRoll.nextInt(1,7);
           if (damageValue == 6) damageValue += damageValue;

           if (turnCounter % 2 == 0) {
               TheKnight.printKnightStatusCombat();
               System.out.println(">");
                command = input.nextLine();
                command = command.replaceAll("\\s","");
                switch (command.toLowerCase()) {
                    case "help" -> {
                        TheKnight.printCommandListCombat();
                        turnCounter--;
                    }
                    case "attack" -> TheKnight.resolveAttack(damageValue,enemyPresent);
                    case "showinventory" -> {
                        TheKnight.printInventoryContent();
                        turnCounter--;
                    }
                    case "useitem" -> TheKnight.useItem(input);
                    case "spelllist" -> {
                        Spells.printSpelllist();
                        turnCounter--;
                    }
                    case "cast" -> Spells.castSpells(input);
                    default -> {
                        System.out.println("Wrong command! Use HELP command if you need to.");
                        turnCounter--;
                    }
                }


           } else {
                enemyPresent.attackPattern(damageValue);
               System.out.println();
           }

           if (!validateValues(enemyPresent)) break;

           turnCounter++;

           Auxiliary.slowDownCombat();

       }



    }

    /**
     *
     * Method is invoked from the encounter method.
     * @see Combat#encounter(Scanner)
     *
     * Checks the health stats on both static reference the Knight and
     * currently fought monster. Returns boolean flag for use in
     * branching logic.
     *
     * @param enemyPresent reference to the monster instance which is currently engaged in combat
     * @return true if the fight should continue, false if the fight should be over
     *
     * @see Monster
     * @see TheKnight
     */
    public static boolean validateValues(Monster enemyPresent) {

        if (TheKnight.currentMana <= 0) {
            TheKnight.currentMana = 0;
        }

        if (enemyPresent.getHealth() <= 0) {
            enemyPresent.setDead(true);
            System.out.println("Combat won! Good job!");
            System.out.println("Looted gold: " + enemyPresent.getGoldDrop());
            TheKnight.goldHeld += enemyPresent.getGoldDrop();
            Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).setRoomEnemy(null);
            gameStateHandler.updateRoomDescriptor();
            return false;
        } else if (TheKnight.currentHealth <= 0) {
            TheKnight.isDead = true;
            System.out.println("Combat lost, better luck next time!");
            return false;
        }

        return true;
    }



}
