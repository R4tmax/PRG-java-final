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
public class TheBrute extends Monster implements HostileActions{


    /**
     * Constructor invoked from the supertype with no
     * additional modifications.
     *
     * @param name String representing the name of the monster
     * @param health Integer value representing functional health of the monster
     * @param damage Integer value representing base damage dealt by the monster (before modifiers are applied)
     * @param goldDrop Integer value representing the bounty player will be given after defeating the monster.
     *
     * @see Monster
     */
    public TheBrute(String name, int health, int damage,int goldDrop) {
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
                            Hideous and terrible creature stands in front of you, it is at least 3 meters tall!
                            It has terrible musculature, and it's skin is so white, you can almost see the sinew and
                            blood vessels underneath.
                            You get the feeling like you are in for a long haul.
                            """);
    }


    /**
     * Overridden interface method (hostileActions).
     * Accepts precalculated randomized values as initial input.
     * Then proceeds to add them to the expected monster base damage.
     * And accounts for armor roll.
     *
     * In case of TheBrute.
     * Any resultant damage is multiplied by a fixed modifier,
     * leading the higher than regular damage output even in scenarios with higher
     * knight armor.
     *
     * @param damageValue Integer value representing calculated
     *                    base damage modifiers passed by the combat Class
     */
    @Override
    public void attackPattern(int damageValue) {
        damageValue += this.damage - TheKnight.getArmor();
        damageValue = accountForArmor(damageValue);
        damageValue *= 1.25;
        TheKnight.setCurrentHealth(TheKnight.getCurrentHealth()-damageValue);
        System.out.println("You were hit by " + this.name + " for " + ConsoleColors.RED + damageValue + ConsoleColors.RESET + " Points of damage!");
    }
}
