package knight;

import gameworld.Map;
import gameworld.RoomType;

import java.util.Scanner;

public class Spells {

    public static void castSpells(Scanner input) {
        System.out.println("Which spell do you want to cast?");
        String spellName = input.nextLine();
        switch (spellName) {
            case "heal" -> Spells.heal();
            case "smite" -> Spells.holySmite();
            case "lightning strike" -> Spells.lightningSpear();
            case "prayer of strength" -> Spells.prayerOfStrength();
            case "prayer of resolve" -> Spells.prayerOfResolve();
            default -> System.out.println("You don't know such a spell!");
        }

    }

    public static void heal() {
        int manaCost = 15;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        TheKnight.currentMana -= manaCost;
        TheKnight.currentHealth += 150;
        TheKnight.preventOverheal();
        System.out.println("You feel better!");
    }

    public static void holySmite() {
        int manaCost = 20;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomBehavior != RoomType.HOSTILE){
            System.out.println("No target!");
            return;
        }

        TheKnight.currentMana -= manaCost;
        TheKnight.currentHealth += 50;
        TheKnight.preventOverheal();

        Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomEnemy.health -= 100;
        System.out.println("You feel slightly better and your enemy took a hit!");
    }

    public static void lightningSpear () {
        int manaCost = 50;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomBehavior != RoomType.HOSTILE){
            System.out.println("No target!");
            return;
        }


        TheKnight.currentMana -= manaCost;
        Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomEnemy.health -= 500;
        System.out.println("Your enemy took a massive hit!");
    }

    public static void prayerOfResolve () {
        int manaCost = 35;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomBehavior == RoomType.HOSTILE){
            System.out.println("Concentration broken!");
            TheKnight.currentMana -= manaCost;
            System.out.println("Cast failed!");
            return;
        }

        TheKnight.currentMana -= manaCost;
        TheKnight.armor += 1;
        System.out.println("You feel better suited to deal with the task at hand");
    }

    public static void prayerOfStrength () {
        int manaCost = 35;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomBehavior == RoomType.HOSTILE){
            System.out.println("Concentration broken!");
            TheKnight.currentMana -= manaCost;
            System.out.println("Cast failed!");
            return;
        }

        TheKnight.currentMana -= manaCost;
        TheKnight.damage += 5;
        System.out.println("You feel better suited to deal with the task at hand");
    }

}
