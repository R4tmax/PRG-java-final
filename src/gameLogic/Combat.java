package gameLogic;

import enemies.*;
import gameworld.Map;
import knight.*;

import java.util.Random;

public class Combat extends Thread {

    public static void encounter () {
        int turnCounter = 0;
        int damageValue;
        Random damageRoll = new Random();
       Monster enemyPresent = Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomEnemy;
        //if (enemyPresent instanceof TheBrute) ((TheBrute) enemyPresent).initialMessage();
       enemyPresent.initialMessage();

       while (true) {
           damageValue = damageRoll.nextInt(1,7);
           if (damageValue == 6) damageValue += damageValue;

           if (turnCounter % 2 == 0) {
               damageValue += TheKnight.damage;
               enemyPresent.health -= damageValue;
               System.out.println("Zasáhl jsi " + enemyPresent.name + " za " + damageValue + " bodů poškození!");

           } else {
                enemyPresent.attackPattern(damageValue);
           }

           if (enemyPresent.health <= 0) {
               enemyPresent.isDead = true;
               System.out.println("Good job!");
               break;
           } else if (TheKnight.health <= 0) {
               TheKnight.isDead = true;
               System.out.println("Game over!");
               break;
           }

           turnCounter++;

           try {
               sleep(2000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

       }



    }

}
