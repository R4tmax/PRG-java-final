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
        System.out.println("You picked up " + this.name +".");
    }

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
