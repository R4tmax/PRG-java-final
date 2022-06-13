package items;

import gameworld.Map;
import knight.TheKnight;

import java.util.Scanner;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022.
 *
 * <p>
 *     Class representing the active items to be used
 *     by the player. Using the Item as its supper class.
 *
 *     Use case of the items is more complicated than in case
 *     of moneyLoot.
 *
 *     Upon first interaction, the Consumable is 'moved' from
 *     the game world into the players inventory, represented as
 *     ArrayList attribute of TheKnight.
 *
 *     Instance is removed from the Room, and awaits activation
 *     by the player.
 *     Please not that for logical coherency reasons this is handled by TheKnight class
 *     rather than classes of the items package.
 * </p>
 *
 * @see TheKnight
 * @see TheKnight#useItem(Scanner)
 * @see TheKnight#inventory
 * @see Item
 */
public class Consumable extends Item implements PickupHandling{
    protected ConsumableType itemType;

    /**
     * Constructor combining the supertype invocation
     * with ConsumableType ENUM reference for logic branching.
     *
     * @param name String representing the name of the consumable item, take note that this name is used for referencing the user input.
     * @param effectiveValue Integer value representing potency of the effect, please confer to the docs of ConsumableType for better clarification
     * @param itemType ENUM representing desired effects of the item activation
     *
     * @see ConsumableType
     * @see Item
     */
    public Consumable(String name, int effectiveValue, ConsumableType itemType) {
        super(name, effectiveValue);
        this.itemType = itemType;
    }


    public ConsumableType getItemType() {
        return itemType;
    }

    /**
     * Standard IntelliJ toString override for testing purposes.
     *
     * @return String representation of the Consumable data.
     */
    @Override
    public String toString() {
        return "Consumable{" +
                "itemType=" + itemType +
                ", name='" + name + '\'' +
                ", effectiveValue=" + effectiveValue +
                '}';
    }

    /**
     * Simple print message invoked on pickups.
     * Take note that the player is not given
     * the effects of the item and needs to figure them out
     * from their name, by design.
     */
    @Override
    public void pickUpMessage() {
        System.out.println("You picked up " + this.name +".");
    }

    /**
     * Method responsible for executing the
     * item interaction with the broader game world.
     *
     * In this case, method checks, whether there is still place in the
     * inventory of the player, and in such case prevents the item pickup
     * with message print.
     *
     * If the inventory has space, however, item is added to the inventory
     * and removed from the game world.
     *
     * Take note that picking up consumables does not have any immediate effect.
     *
     * @see TheKnight
     * @see gameworld.Room
     */
    @Override
    public void pickUpEffect() {
        if (TheKnight.inventory.size() < TheKnight.INVENTORY_CAP){
            TheKnight.inventory.add((Consumable) Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).getRoomLoot());
            this.pickUpMessage();
            Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).setRoomLoot(null);
        } else {
            System.out.println("Inventory full!");
        }
    }

    /**
     * This method is called by user input from the
     * TheKnight class when user wishes to use items from his inventory.
     *
     * Take note that before the call is made, TheKnight class checks
     * whether desired item (searched by Name) exists within the TheKnight
     * inventory attribute.
     *
     * If needed, method makes calls to TheKnight auxiliary methods
     * which prevent Health and Mana values to exceed their maximal values.
     *
     * @param type ENUM of the item typed grabbed by the getter on call time.
     * @param value Integer of the effect potency grabbed by the getter on call time.
     *
     *
     * @see TheKnight
     * @see TheKnight#useItem(Scanner)
     * @see ConsumableType
     * @see TheKnight#preventOverheal()
     * @see TheKnight#preventOvercast()
     */
    public void executeConsumableEffect(ConsumableType type, int value) {
        switch (type) {
            case HEALTH_FILL -> {
                TheKnight.setCurrentHealth(TheKnight.getCurrentHealth() + value);
                TheKnight.preventOverheal();
            }
            case MANA_FILL -> {
                TheKnight.setCurrentMana(TheKnight.getCurrentMana() + value);
                TheKnight.preventOvercast();
            }
            case DAMAGE_BOOST -> TheKnight.setDamage(TheKnight.getDamage()+value);
            case ARMOR_BOOST -> TheKnight.setArmor(TheKnight.getArmor()+value);
        }

    }

}
