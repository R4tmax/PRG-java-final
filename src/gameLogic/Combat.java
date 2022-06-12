package gameLogic;

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
        Monster enemyPresent = Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomEnemy;
        enemyPresent.initialMessage();

       while (true) {
           damageValue = damageRoll.nextInt(1,7);
           if (damageValue == 6) damageValue += damageValue;

           if (turnCounter % 2 == 0) {
               TheKnight.printKnightStatusCombat();
               System.out.println("How do you want to proceed?");
                command = input.nextLine();
                command = command.replaceAll("\\s","");
                switch (command.toLowerCase()) {
                    case "attack" -> {
                        damageValue += TheKnight.damage;
                        double rollForCrit = Math.random();
                        if (rollForCrit > 0.95) {
                            System.out.println("Critical hit!");
                            damageValue *= 2;
                        }
                        enemyPresent.health -= damageValue;
                        System.out.println("You hit " + enemyPresent.name + " for " + damageValue + " points of damage!");
                    }
                    case "useitem" -> TheKnight.useItem(input);
                    case "cast" -> Spells.castSpells(input);
                    default -> {
                        System.out.println("Wrong command!");
                        turnCounter--;
                    }
                }


           } else {
                enemyPresent.attackPattern(damageValue);
           }

           if (!validateValues(enemyPresent)) break;

           turnCounter++;

           try {
               sleep(1250);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

       }



    }

    public static boolean validateValues(Monster enemyPresent) {

        if (TheKnight.currentMana <= 0) {
            TheKnight.currentMana = 0;
        }

        if (enemyPresent.health <= 0) {
            enemyPresent.isDead = true;
            System.out.println("Combat won! Good job!");
            System.out.println("Looted gold: " + enemyPresent.goldDrop);
            TheKnight.goldHeld += enemyPresent.goldDrop;
            Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomEnemy = null;
            return false;
        } else if (TheKnight.currentHealth <= 0) {
            TheKnight.isDead = true;
            System.out.println("Combat lost, better luck next time!");
            return false;
        }

        return true;
    }



}
