package items;

import auxiliary.ConsoleColors;
import gameworld.Map;
import knight.TheKnight;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022.
 *
 * <p>
 *     Subclass of the Item abstract.
 *     Represents item types which increase player
 *     gold reserve based upon one time 'loot' interaction.
 *
 *     By design, money-loot objects are 'picked up' once,
 *     and disappear from the game world entirely.
 *
 *     In case of moneyLoot, effective value is interpreted as an
 *     amount of gold, which player can use for trading.
 *     Simply added the the relevant TheKnight attribute.
 *
 *     Take note, that methods are inherited from PickupHandling interface.
 * </p>
 *
 * @see TheKnight
 * @see Item
 */
public class MoneyLoot extends Item implements PickupHandling{


    /**
     * Standard Item constructor inherited from the
     * superclass.
     * Take note of the effective value meaning in this use case.
     *
     * @param name String representing Name of the item as presented to the player
     * @param effectiveValue Integer value representing factual potency of the item, in this case, value to be added to the goldHeld attribute of TheKnight
     */
    public MoneyLoot(String name, int effectiveValue) {
        super(name, effectiveValue);
    }

    /**
     * Simple printing message called on
     * relevant interactions, in this case.
     * Displays and confirms the interaction, and
     * prints changes in player Status.
     */
    @Override
    public void pickUpMessage() {
        System.out.println("You found: " + this.name + ". This will make you a richer man.");
        System.out.println("Value of the find is: "+ ConsoleColors.YELLOW + this.effectiveValue +  ConsoleColors.RESET + " gold pieces.");
    }

    /**
     * Method responsible for executing the
     * item interaction with the broader game world.
     *
     * In this case, adds the Effective value attribute to the
     * TheKnight goldHeld attribute.
     *
     * Take note that the method clears the reference in the Room
     * Instance, preventing duplicity use of the same item several times.
     * @see TheKnight
     */
    @Override
    public void pickUpEffect() {
        TheKnight.setGoldHeld(TheKnight.getGoldHeld() + this.effectiveValue);
        this.pickUpMessage();
        System.out.println("You now have: " + ConsoleColors.YELLOW + TheKnight.getGoldHeld() + ConsoleColors.RESET +" gold pieces!");
        Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).setRoomLoot(null);
    }
}
