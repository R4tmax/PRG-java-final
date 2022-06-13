package items;

import gameworld.Map;
import knight.TheKnight;

/**
 * @author Martin Kadlec
 * @version Last refactored on 13.06.2022.
 * <p>
 *      Item is an abstract class, behaving as super class for Consumable
 *      and MoneyLoot classes, it uses PickupHandling interface to facilitate
 *      primary user interaction -> pickups.
 *
 *      Class behavior then branches dependently on the use case,
 *      which differs significantly for the subtypes, in general terms.
 *      MoneyLoot is interacted with only once,
 *      while Consumables are more persistent and dependent on user
 *      approach.
 * </p>
 *
 * @see MoneyLoot
 * @see Consumable
 * @see items.PickupHandling
 */
public abstract class Item implements PickupHandling{
    protected String name;
    protected int effectiveValue;

    /**
     * Standard constructor for the Item superclass
     * Take note that effective value is interpreted in slightly different
     * meanings depending on the subclass
     *
     * @param name String representing the Name of the item
     * @param effectiveValue Integer value with numerical value/potency of the item effect (depends on further context)
     */
    public Item(String name, int effectiveValue) {
        this.name = name;
        this.effectiveValue = effectiveValue;
    }

    public String getName() {
        return name;
    }

    public int getEffectiveValue() {
        return effectiveValue;
    }

    /**
     * This method operates as an intermediary
     * between user and the game data.
     * It checks whether players current position
     * coincides with active item, if yes, it
     * calls the interfaced pickup method.
     * If not, prints message to the user.
     *
     * @see MoneyLoot#pickUpEffect()
     * @see Consumable#pickUpEffect()
     * @see PickupHandling
     */
    public static void attemptPickup () {
        if (Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getRoomLoot() == null) {
            System.out.println("There is nothing to pickup here!");
        }  else {
            Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getRoomLoot().pickUpEffect();
        }
    }


}
