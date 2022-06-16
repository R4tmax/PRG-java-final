package knight;

import gameworld.Map;
import gameworld.RoomType;

import java.util.Scanner;


/**
 * @author Martin Kadlec
 * @version Last refactored on 13.06.2022.
 *
 * <p>
 * Class representing abstraction of the
 * different spells available to the player.
 * <p>
 * Each spell behaves as a self contained method, although most spells
 * share certain similarities.
 * <p>
 * In general, spells are either buffs or methods of attack.
 * Casts can be attempted both during map exploration or
 * combat, but may yield different results depending on the context.
 * <p>
 * Take note that the entire class is created using
 * static modifiers.
 * Known issue - class for some reason incorrectly propagates
 * within IntelliJ, preventing ease of testing and documentation.
 * </p>
 * @see TheKnight
 * @see gameLogic.Combat
 * @see gameLogic.Main
 */
public class Spells {

    /**
     * Public intermediary for the Spells class.
     * Method is called by user input and asks for further
     * input. Mistyping a spell name or typing
     * nonexistent spell is considered a miscast for purposes of combat
     * and turn is forfeited for the player.
     *
     * @param input Scanner object passed from the main.
     */
    public static void castSpells(Scanner input) {
        System.out.println("Which spell do you want to cast?");
        String spellName = input.nextLine();
        spellName = spellName.replaceAll("\\s", "");
        switch (spellName.toLowerCase()) {
            case "lightningtouch" -> knight.Spells.lightningTouch();
            case "heal" -> knight.Spells.heal();
            case "smite" -> knight.Spells.holySmite();
            case "lightningstrike" -> knight.Spells.lightningStrike();
            case "prayerofstrength" -> knight.Spells.prayerOfStrength();
            case "prayerofresolve" -> knight.Spells.prayerOfResolve();
            default -> System.out.println("You don't know such a spell!");
        }

    }


    /**
     * Prints available spells and their effects
     * to the user.
     */
    public static void printSpelllist() {
        System.out.println("""
                You have following spells at your disposal:
                => LIGHTNING TOUCH - Deals somewhat minor damage, but is very cheap to cast.
                => HEAL - heals you for a moderate amount for a modest mana cost
                => SMITE - both heals you and damages your opponent, slightly more expensive than the heal
                => LIGHTNING STRIKE - Deals enormous damage to your opponent, but drains your mana completely!!
                => PRAYER OF STRENGTH - Improves your damage for the rest of the game, but it is rather mana taxing. Requires concentration!
                => PRAYER OF RESOLVE - Improves your armor for the rest of the game, but it is rather mana taxing. Requires concentration!
                                
                Take heed, knight, some spells should not be attempted during combat!
                """);
    }

    /**
     * This spell deals minor damage for a
     * small mana cost.
     * <p>
     * To prevent casting from empty rooms, breaking if conditional is added
     * before actual execution of the cast.
     * <p>
     * From technical standpoint the method directly subtracts from the
     * monster health pool, values are grabbed via getter Map methods.
     */
    protected static void lightningTouch() {
        int manaCost = 5;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy() == null) {
            System.out.println("No target!");
            return;
        }


        TheKnight.currentMana -= manaCost;
        Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy().setHealth(Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy().getHealth() - 100);
        System.out.println("Your enemy took a nice hit!");
    }


    /**
     * This spell heals for significant amount of knight total HP.
     * <p>
     * From technical standpoint the method directly adds to the
     * Knight health pool, values are approached directly thanks to
     * the static TheKnight modifiers.
     * <p>
     * Take note that auxiliary functions are called to
     * prevent undesirable effects.
     */
    protected static void heal() {
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

    /**
     * Combines functionalities of lightning touch and heal.
     * With modified values.
     * <p>
     * <p>
     * Take note that from technical standpoint this
     * is considered to be offensive spell, and as such cannot be used
     * outside of combat.
     *
     * @see knight.Spells#lightningTouch()
     * @see knight.Spells#heal()
     */
    protected static void holySmite() {
        int manaCost = 25;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy() == null) {
            System.out.println("No target!");
            return;
        }

        TheKnight.currentMana -= manaCost;
        TheKnight.currentHealth += 50;
        TheKnight.preventOverheal();

        Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy().setHealth(Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy().getHealth() - 200);
        System.out.println("You feel slightly better and your enemy took a hit!");
    }

    /**
     * Supercharged version of lightning touch.
     *
     * @see knight.Spells#lightningTouch()
     */
    protected static void lightningStrike() {
        int manaCost = 50;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy() == null) {
            System.out.println("No target!");
            return;
        }


        TheKnight.currentMana -= manaCost;
        Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy().setHealth(Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomEnemy().getHealth() - 600);
        System.out.println("Your enemy took a massive hit!");
    }

    /**
     * Permanently increases armor value of
     * The Knight upon use.
     * <p>
     * Take note that trying to cast this in
     * 'hostile' rooms will break cause miscast.
     */
    protected static void prayerOfResolve() {
        int manaCost = 35;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomBehavior() == RoomType.HOSTILE) {
            System.out.println("Concentration broken!");
            TheKnight.currentMana -= manaCost;
            System.out.println("Cast failed!");
            return;
        }

        TheKnight.currentMana -= manaCost;
        TheKnight.armor += 2;
        System.out.println("You feel better suited to deal with the task at hand");
    }

    /**
     * Permanently increases damage value of
     * The Knight upon use.
     * <p>
     * Take note that trying to cast this in
     * 'hostile' rooms will break cause miscast.
     */
    protected static void prayerOfStrength() {
        int manaCost = 35;
        if (manaCost > TheKnight.currentMana) {
            System.out.println("Not enough mana to cast!");
            return;
        }

        if (Map.getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getRoomBehavior() == RoomType.HOSTILE) {
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


