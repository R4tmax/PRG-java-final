package items;

import gameworld.Map;
import knight.TheKnight;

public class Consumable extends Item implements PickupHandling{
    protected ConsumableType itemType;

    public Consumable(String name, int effectiveValue, ConsumableType itemType) {
        super(name, effectiveValue);
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Consumable{" +
                "itemType=" + itemType +
                ", name='" + name + '\'' +
                ", effectiveValue=" + effectiveValue +
                '}';
    }

    public ConsumableType getItemType() {
        return itemType;
    }

    @Override
    public void pickUpMessage() {
        System.out.println("You picked up " + this.name);
    }

    @Override
    public void pickUpEffect() {
        if (TheKnight.inventory.size() < TheKnight.inventoryCap){
            TheKnight.inventory.add((Consumable) Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomLoot);
            this.pickUpMessage();
            Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomLoot = null;
        } else {
            System.out.println("Inventory full!");
        }
    }

    public void executeConsumableEffect(ConsumableType type, int value) {
        switch (type) {
            case HEALTH_FILL -> {
                TheKnight.currentHealth += value;
                TheKnight.preventOverheal();
            }
            case MANA_FILL -> {
                TheKnight.currentMana += value;
                TheKnight.preventOvercast();
            }
            case DAMAGE_BOOST -> TheKnight.damage += value;
            case ARMOR_BOOST -> TheKnight.armor += value;
        }

    }

}
