package knight;

import gameworld.Map;
import gameworld.RoomType;

import java.util.Scanner;

public class Spells {

    public static void castSpells(Scanner input) {
        System.out.println("Which spell do you want to cast?");
        String spellName = input.nextLine();
        spellName = spellName.replaceAll("\\s","");
        switch (spellName.toLowerCase()) {
            case "lightningtouch" -> Spells.lightningTouch();
            case "heal" -> Spells.heal();
            case "smite" -> Spells.holySmite();
            case "greaterlightningstrike" -> Spells.lightningSpear();
            case "prayerofstrength" -> Spells.prayerOfStrength();
            case "prayerofresolve" -> Spells.prayerOfResolve();
            default -> System.out.println("You don't know such a spell!");
        }

    }



    public static void printSpelllist () {
        System.out.println("""
                You have following spells at your disposal:
                => LIGHTNING TOUCH - Deals somewhat minor damage, but is very cheap to cast.
                => HEAL - heals you for a moderate amount for a modest mana cost
                => SMITE - both heals you and damages your opponent, slightly more expensive than the heal
                => GREATER LIGHTNING STRIKE - Deals enormous damage to your opponent, but drains your mana completely!!
                => PRAYER OF STRENGTH - Improves your damage for the rest of the game, but it is rather mana taxing. Requires concentration!
                => PRAYER OF RESOLVE - Improves your armor for the rest of the game, but it is rather mana taxing. Requires concentration!
                
                Take heed, knight, some spells should not be attempted during combat!
                """);
    }

    private static void lightningTouch() {
        int manaCost = 5;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomBehavior() != RoomType.HOSTILE){
            System.out.println("No target!");
            return;
        }


        TheKnight.currentMana -= manaCost;
        Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomEnemy().setHealth(Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomEnemy().getHealth()-100);
        System.out.println("Your enemy took a nice hit!");
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

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomBehavior() != RoomType.HOSTILE){
            System.out.println("No target!");
            return;
        }

        TheKnight.currentMana -= manaCost;
        TheKnight.currentHealth += 50;
        TheKnight.preventOverheal();

        Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomEnemy().setHealth(Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomEnemy().getHealth()-200);
        System.out.println("You feel slightly better and your enemy took a hit!");
    }

    public static void lightningSpear () {
        int manaCost = 50;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomBehavior() != RoomType.HOSTILE){
            System.out.println("No target!");
            return;
        }


        TheKnight.currentMana -= manaCost;
        Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomEnemy().setHealth(Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomEnemy().getHealth()-600);
        System.out.println("Your enemy took a massive hit!");
    }

    public static void prayerOfResolve () {
        int manaCost = 35;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomBehavior() == RoomType.HOSTILE){
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

        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomBehavior() == RoomType.HOSTILE){
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
