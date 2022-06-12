package gameLogic;

import auxiliary.Auxiliary;
import enemies.*;
import gameworld.Map;
import knight.*;

import java.util.Random;
import java.util.Scanner;

public class Combat extends Thread {

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
