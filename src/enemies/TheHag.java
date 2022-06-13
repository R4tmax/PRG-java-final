package enemies;

import auxiliary.ConsoleColors;
import knight.TheKnight;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022.
 *
 * Subtype of the Monster class.
 * Please see the super type for more info
 * on attributes of the class.
 *
 * @see Monster
 * @see HostileActions
 */
public class TheHag extends Monster implements HostileActions{
    /**
     * Constructor invoked from the supertype with no
     * additional modifications.
     *
     * @param name String representing the name of the monster
     * @param health Integer value representing functional health of the monster
     * @param damage Integer value representing base damage dealt by the monster (before modifiers are applied)
     * @param goldDrop Integer value representing the bounty player will be given after defeating the monster.
     */
    public TheHag(String name, int health, int damage, int goldDrop) {
        super(name, health, damage, goldDrop);
    }

    /**
     * Prints the message when encountering the enemy for the first time.
     * Provides lore and flavor text to the player.
     *
     * @see HostileActions
     */
    @Override
    public void initialMessage() {
        System.out.println("""
                            The feeling you had around here. The dense air and shivers?
                            It is so much stronger here, you almost feel dizzy.
                            Suddenly, you notice movement in nearby reeds.
                            A small, but very much so disgusting womanlike creature stands in front of you.
                            Your instinct tells you, if you feel the need to cast a spell, you should do it NOW.
                            """);
    }


    /**
     * Overridden interface method (hostileActions).
     * Accepts precalculated randomized values as initial input.
     * Then proceeds to add them to the expected monster base damage.
     * And accounts for armor roll.
     *
     * In case of TheHag.
     * Any resultant damage is applied twice.
     * First to the Knight health pool, then to the knight mana pool.
     * As such, Hag interferes with the ability of the player to cast
     * spells during combat.
     *
     * @param damageValue Integer value representing calculated
     *                    base damage modifiers passed by the combat Class
     */
    @Override
    public void attackPattern(int damageValue) {
        damageValue += this.damage - TheKnight.getArmor();
        damageValue = accountForArmor(damageValue);
        TheKnight.setCurrentHealth(TheKnight.getCurrentHealth()-damageValue);
        TheKnight.setCurrentMana(TheKnight.getCurrentMana()-damageValue);
        System.out.println("You were hit by " + this.name + " for " + ConsoleColors.RED + damageValue + ConsoleColors.RESET + " points of damage!");
        System.out.println(ConsoleColors.CYAN + "The same damage was taken by your mana pool!" + ConsoleColors.RESET);
    }
}
