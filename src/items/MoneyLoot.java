package items;

import auxiliary.ConsoleColors;
import gameworld.Map;
import knight.TheKnight;

public class MoneyLoot extends Item implements PickupHandling{


    public MoneyLoot(String name, int effectiveValue) {
        super(name, effectiveValue);
    }

    @Override
    public void pickUpMessage() {
        System.out.println("You found: " + this.name + ". This will make you a richer man.");
        System.out.println("Value of the find is: "+ ConsoleColors.YELLOW + this.effectiveValue +  ConsoleColors.RESET + " gold pieces.");
    }

    @Override
    public void pickUpEffect() {
        TheKnight.setGoldHeld(TheKnight.getGoldHeld() + this.effectiveValue);
        this.pickUpMessage();
        System.out.println("You now have: " + ConsoleColors.YELLOW + TheKnight.getGoldHeld() + ConsoleColors.RESET +" gold pieces!");
        Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).setRoomLoot(null);
    }
}
