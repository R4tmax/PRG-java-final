package items;

import gameworld.Map;
import knight.TheKnight;

public abstract class Item implements PickupHandling{
    protected String name;
    protected int effectiveValue;

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

    public static void attemptPickup () {
        if (Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomLoot() == null) {
            System.out.println("There is nothing to pickup here!");
        }  else {
            Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).getRoomLoot().pickUpEffect();
        }
    }


}
