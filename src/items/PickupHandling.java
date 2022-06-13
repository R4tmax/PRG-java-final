package items;

/**
 * @author Martin Kadlec
 * @version Last refactored on 11.6.2022.
 *
 * <p>
 *     Public interface designed for use with Item superclass.
 *     Contains predefined void methods for further item interactions
 *     dependant on the item types, please see implementations
 *     in desired subclasses for more info
 * </p>
 *
 * @see MoneyLoot
 * @see Consumable
 *
 */
public interface PickupHandling {
    /**
     * Predefined void method intended for
     * printing relevant info to the user,
     */
    void pickUpMessage();

    /**
     * Predefined void method intended for
     * handling factual 'picking up'
     * of items in the game world.
     */
    void pickUpEffect();
}
