package knight;


import enemies.Monster;
import gameworld.Map;
import items.Consumable;


import java.util.ArrayList;
import java.util.Scanner;

import static gameworld.Map.getCurrentPosition;

public class TheKnight {
    public static final int maxHealth = 200;
    public static int currentHealth = 200;
    public static final int maxMana = 50;
    public static int currentMana = 50;
    public static int armor = 2;
    public static int damage = 15;
    public static int goldHeld = 250;
    public static final ArrayList<Consumable> inventory = new ArrayList<>();
    public static final int inventoryCap = 5;
    public static KnightCoordinates position = new KnightCoordinates(4,2);
    public static boolean isDead = false;


    public static void moveKnight (Scanner input) {

        int tmpHorizontal = TheKnight.position.horizontal;
        int tmpVertical = TheKnight.position.vertical;
        System.out.println("In which compass direction do you want to go?");
        String direction = input.nextLine();
        direction = direction.replaceAll("\\s","");
            try {
                switch (direction.toLowerCase()) {
                    case "north" -> TheKnight.position.horizontal -= 1;
                    case "west" -> TheKnight.position.vertical -= 1;
                    case "east" -> TheKnight.position.vertical += 1;
                    case "south" -> TheKnight.position.horizontal += 1;
                    default -> System.out.println("Unknown direction");
                }
            } catch (Exception e) {
                System.out.println("Error");
            }

        validateMove(tmpHorizontal,tmpVertical);
        if (getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomEnemy() == null) Map.printPosition();

    }

    private static void validateMove (int tmpX, int tmpY) {
        try {
            if (getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getLockedStatus()) {
                System.out.println("You get a sinking feeling, like acid in your stomach!");
                System.out.println("Perhaps you should not be here yet? Explore elsewhere!");
                TheKnight.position.horizontal = tmpX;
                TheKnight.position.vertical = tmpY;
            }
        } catch (Exception e) {
            System.out.println("You are leaving the game area, try a different direction!");
            TheKnight.position.horizontal = tmpX;
            TheKnight.position.vertical = tmpY;
        }


    }

    public static void resolveAttack(int damageValue, Monster enemyPresent) {
        damageValue += TheKnight.damage;
        double rollForCrit = Math.random();
        if (rollForCrit > 0.95) {
            System.out.println("Critical hit!");
            damageValue *= 2;
        }
        enemyPresent.setHealth(-damageValue);
        System.out.println("You hit " + enemyPresent.getName() + " for " + damageValue + " points of damage!");
    }


    public static void preventOverheal () {
        if (TheKnight.currentHealth > TheKnight.maxHealth) TheKnight.currentHealth = TheKnight.maxHealth;
    }

    public static void preventOvercast() {
        if (TheKnight.currentMana > TheKnight.maxMana) TheKnight.currentMana = TheKnight.maxMana;
    }

    public static void printKnightStatusExploration () {
        System.out.println("Current health: " + TheKnight.currentHealth);
        if (TheKnight.currentMana > 0) System.out.println("Current mana: " + TheKnight.currentMana);
        else System.out.println("You are out of mana!");
        System.out.println("Your current damage is: " + TheKnight.damage);
        System.out.println("Your armor value is: " + TheKnight.armor);
        System.out.println("You have " + TheKnight.goldHeld + " gold coins at your disposal.");
    }
    public static void printKnightStatusCombat () {
        System.out.println("Current health: " + TheKnight.currentHealth);
        if (TheKnight.currentMana > 0) System.out.println("Current mana: " + TheKnight.currentMana);
        else System.out.println("You are out of mana!");
        System.out.println("Your current damage is: " + TheKnight.damage);
        System.out.println("Your armor value is: " + TheKnight.armor);
    }

    public static void printInventoryContent () {
        for (Consumable item : inventory ) {
            System.out.println(item.getName());
        }
    }

    public static void useItem(Scanner input) {
        System.out.println("Please enter the name of item you want to use.");
        String toUse = input.nextLine();
        toUse = toUse.toLowerCase();
        toUse = toUse.replaceAll("\\s","");

        for (Consumable consumable : inventory) {
            if (toUse.equals(consumable.getName().toLowerCase().replaceAll("\\s",""))) {
                consumable.executeConsumableEffect(consumable.getItemType(), consumable.getEffectiveValue());
                inventory.remove(consumable);
                return;
            }
        }
        System.out.println("No such item was found");
    }

    public static void printCommandListExploration () {
        System.out.println("""
                Following commands are available to you at the moment:
                => MOVE - Allows you to change rooms, expects direction in terms of: NORTH, WEST,EAST,SOUTH after prompt
                => LOOK AROUND - Prints you the description of your surroundings.
                => STATUS - Prints stats of the Knight, including gold carried.
                => INTERACT - Allows you to trigger special effects in certain rooms, use this to talk to people.
                => LOOT - Allows you to grab certain items from the world.
                => SHOW INVENTORY - Prints the contents of your inventory.
                => USE ITEM - allows you to use items held in your inventory
                => SPELL LIST - prints available spells and basic info about them
                => CAST - Allows you the cast spells, expects name of the spell as input after prompt
                => QUIT GAME - ends the game, take not that this wont work in combat.
                """);
    }

    public static void printCommandListCombat() {
        System.out.println(""" 
                Following commands are available to you at the moment:
                => ATTACK - Deal direct damage to your enemy, if you are lucky, you can deal critical damage for twice the usual amount.
                => SHOW INVENTORY - Prints the contents of your inventory. (this won't cost you your turn)
                => USE ITEM - allows you to use items held in your inventory
                => SPELL LIST - prints available spells and basic info about them (this won't cost you your turn)
                => CAST - Allows you the cast spells, expects name of the spell as input after prompt
                
                Running away from monsters is not an option, good luck!
                """);
    }


}
