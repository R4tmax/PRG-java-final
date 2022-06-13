package knight;


import auxiliary.ConsoleColors;
import enemies.Monster;
import gameworld.Map;
import items.Consumable;


import java.util.ArrayList;
import java.util.Scanner;

import static gameworld.Map.getCurrentPosition;

/**
 * @author Martin Kadlec
 * @version Last refactored on 13.06.2022.
 *
 * <p>
 *     The one of the key classes of the game.
 *     Represents the main character and is implemented
 *     with usage of static modifiers
 *
 *     As such, TheKnight Class operates as self contained
 *     instance of itself.
 *
 *     Knight interactions are usually facilitated through
 *     the room getter in the Map class. See moveKnight for more info
 *     on knight movements.
 *
 *     Knight holds most of the Main Character (directly)
 *     related actions, with notable exception of Spells class.
 * </p>
 *
 * @see KnightCoordinates
 * @see Map
 * @see items.Item
 */
public class TheKnight {
    public static final int MAX_HEALTH = 200;
    protected static int currentHealth = 200;
    public static final int MAX_MANA = 50;
    protected static int currentMana = 50;
    protected static int armor = 2;
    protected static int damage = 15;
    protected static int goldHeld = 250;
    public static final ArrayList<Consumable> inventory = new ArrayList<>();
    public static final int INVENTORY_CAP = 5;
    protected static KnightCoordinates position = new KnightCoordinates(4,2);
    protected static boolean isDead = false;

    public static int getCurrentHealth() {
        return currentHealth;
    }

    public static void setCurrentHealth(int currentHealth) {
        TheKnight.currentHealth = currentHealth;
    }

    public static int getCurrentMana() {
        return currentMana;
    }

    public static void setCurrentMana(int currentMana) {
        TheKnight.currentMana = currentMana;
    }

    public static int getArmor() {
        return armor;
    }

    public static void setArmor(int armor) {
        TheKnight.armor = armor;
    }

    public static int getDamage() {
        return damage;
    }

    public static void setDamage(int damage) {
        TheKnight.damage = damage;
    }

    public static int getGoldHeld() {
        return goldHeld;
    }

    public static void setGoldHeld(int goldHeld) {
        TheKnight.goldHeld = goldHeld;
    }

    public static KnightCoordinates getPosition() {
        return position;
    }

    public static boolean getIsDead() {
        return isDead;
    }

    public static void setIsDead(boolean isDead) {
        TheKnight.isDead = isDead;
    }

    /**
     * Accepts the user input as per given parameters
     * and changes the numerical coordinates assigned to the knight.
     * Temporary copies are made BEFORE the change.
     * And are subsequently checked via try-catch block
     * for outOfBoundsException.
     * See more in validateMove method.
     * @param input Passed Scanner object from the Main class
     * @see TheKnight#validateMove(int, int)
     */
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

    /**
     * Auxiliary function for the moveKnight method.
     * Try-catch block encapsulates if, which checks for
     * isLocked property of Room class and outOfBoundsExceptions
     * If either option is detected, KnightCoordinates are reset
     * to temporary saved values. Thus preventing
     * breaking the game by leaving the game area.
     *
     * @throws ArrayIndexOutOfBoundsException upon attempting to get non-existent coordinate pair.
     * @param tmpX Temporary horizontal coordinate.
     * @param tmpY Temporary vertical coordinate.
     */
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

    /**
     * This method handles the damage dealt by the
     * Knight to the other entities in combat.
     *
     * It works by simply taking already calculated randomized values
     * which are added the TheKnight damage attribute.
     * Math function then determines whether the hit is critical hit or not
     * (double damage) and substracts the value of the enemy instance
     *
     * @param damageValue Integer - Precalculated value passed from Combat class with randomized damage modifier
     * @param enemyPresent Reference to the instance of currently fought monster
     */
    public static void resolveAttack(int damageValue, Monster enemyPresent) {
        damageValue += TheKnight.damage;
        double rollForCrit = Math.random();
        if (rollForCrit > 0.95) {
            System.out.println(ConsoleColors.RED + "Critical hit!" + ConsoleColors.RESET);
            damageValue *= 2;
        }
        enemyPresent.setHealth(enemyPresent.getHealth()-damageValue);
        System.out.println("You hit " + enemyPresent.getName() + " for " + ConsoleColors.RED + damageValue + ConsoleColors.RESET +" points of damage!");
    }


    /**
     * This function simply assures that currentHealth
     * can never exceed MAX_HEALTH, take note that
     * below zero checks are handled separately.
     */
    public static void preventOverheal () {
        if (TheKnight.currentHealth > TheKnight.MAX_HEALTH) TheKnight.currentHealth = TheKnight.MAX_HEALTH;
    }


    /**
     * This function simply assures that currentMana
     * can never exceed MAX_MANA, take note that
     * below zero checks are handled separately.
     */
    public static void preventOvercast() {
        if (TheKnight.currentMana > TheKnight.MAX_MANA) TheKnight.currentMana = TheKnight.MAX_MANA;
    }

    /**
     * This method prints the stats of the main character on demand.
     */
    public static void printKnightStatusExploration () {
        System.out.println("Current health: " + ConsoleColors.RED + TheKnight.currentHealth + ConsoleColors.RESET);
        if (TheKnight.currentMana > 0) System.out.println("Current mana: " + ConsoleColors.CYAN + TheKnight.currentMana + ConsoleColors.RESET);
        else System.out.println(ConsoleColors.CYAN + "You are out of mana!" + ConsoleColors.RESET);
        System.out.println("Your current damage is: " + ConsoleColors.PURPLE + TheKnight.damage + ConsoleColors.RESET);
        System.out.println("Your armor value is: " + ConsoleColors.GREEN +TheKnight.armor + ConsoleColors.RESET);
        System.out.println("You have " + ConsoleColors.YELLOW + TheKnight.goldHeld + ConsoleColors.RESET + " gold coins at your disposal.");
    }

    /**
     * This method prints stats relevant
     * to the combat encounters each combat turn.
     */
    public static void printKnightStatusCombat () {
        System.out.println("Current health: " + ConsoleColors.RED + TheKnight.currentHealth + ConsoleColors.RESET);
        if (TheKnight.currentMana > 0) System.out.println("Current mana: " + ConsoleColors.CYAN + TheKnight.currentMana + ConsoleColors.RESET);
        else System.out.println(ConsoleColors.CYAN + "You are out of mana!" + ConsoleColors.RESET);
        System.out.println("Your current damage is: " + ConsoleColors.PURPLE + TheKnight.damage + ConsoleColors.RESET);
        System.out.println("Your armor value is: " + ConsoleColors.GREEN +TheKnight.armor + ConsoleColors.RESET);
    }

    /**
     * This method uses for-each loop to iterate over
     * entire inventory, prints name of every consumable item.
     * Take note that nothing is outputted for empty inventory
     */
    public static void printInventoryContent () {
        for (Consumable item : inventory ) {
            System.out.println(item.getName());
        }
    }

    /**
     * This method allows using items currently held in the inventory.
     * Take note that this method can be called from combat as well as
     * exploration.
     * Prints warning message for user when attempting to use nonexistent
     * item.
     *
     * @param input Scanner object passed from the Main
     */
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

    /**
     * Prints the list of commands invoked
     * during exploration with 'help' command.
     */
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

    /**
     * Prints the list of commands invoked
     * during combat with 'help' command.
     */
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
