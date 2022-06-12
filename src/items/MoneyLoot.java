package items;

import gameworld.Map;
import knight.TheKnight;

public class MoneyLoot extends Item implements PickupHandling{


    public MoneyLoot(String name, int effectiveValue) {
        super(name, effectiveValue);
    }

    @Override
    public void pickUpMessage() {
        System.out.println("You found: " + this.name + ". This will make you a richer man.");
        System.out.println("Value of the find is: " + this.effectiveValue + " gold pieces.");
    }

    @Override
    public void pickUpEffect() {
        TheKnight.goldHeld += this.effectiveValue;
        this.pickUpMessage();
        System.out.println("You now have: " + TheKnight.goldHeld + " gold pieces!");
        Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomLoot = null;
    }
}
