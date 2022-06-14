package enemies;

import auxiliary.ConsoleColors;
import knight.TheKnight;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022.
 *
 *  <p>
 *      Subtype of the Monster class.
 *      Please see the super type for more info
 *      on attributes of the class.
 *  </p>
 *
 * @see Monster
 * @see HostileActions
 */
public class TheMatriarch extends Monster implements HostileActions{
    /**
     * Simplified monster constructor invoked from the superclass
     * with no additional modifications.
     *
     * @param name String representing the name of the monster
     * @param health Integer value representing functional health of the monster
     * @param damage Integer value representing base damage dealt by the monster (before modifiers are applied)
     */
    public TheMatriarch(String name, int health, int damage) {
        super(name, health, damage);
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
                            As you scout the rocky hills at the base of the mountain range.
                            You cannot shake the feeling of being watched.
                            After a while, you start noticing dead bodies. Torn apart and left to rot.
                            You catch a sudden movement through the visor of your helmet...
                            DEFEND YOURSELF!!!
                            """);
    }


    /**
     * Overridden interface method (hostileActions).
     * Accepts precalculated randomized values as initial input.
     * Then proceeds to add them to the expected monster base damage.
     * And accounts for armor roll.
     *
     * In case of TheMatriarch.
     * Any resultant damage is applied twice the TheKnight health pool.
     * As such, Matriarch achieves higher damage per turn than other subtypes.
     * But can be mitigated by armor upgrades more easily.
     *
     * @param damageValue Integer value representing calculated
     *                    base damage modifiers passed by the combat Class
     */
    @Override
    public void attackPattern(int damageValue) {
        damageValue += this.damage - TheKnight.getArmor();
        damageValue = accountForArmor(damageValue);
        TheKnight.setCurrentHealth(TheKnight.getCurrentHealth()-damageValue);
        System.out.println("Your were hit by " + this.name + " for " + ConsoleColors.RED + damageValue + ConsoleColors.RESET + " points of damage!");
        TheKnight.setCurrentHealth(TheKnight.getCurrentHealth()-damageValue);
        System.out.println("You were hit AGAIN by " + this.name + " for " + ConsoleColors.RED + damageValue + ConsoleColors.RESET + " points of damage!");
        System.out.println("The Matriarch is a fierce enemy!!!");
    }
}
