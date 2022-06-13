package enemies;

import auxiliary.ConsoleColors;
import knight.TheKnight;

public class TheBrute extends Monster implements HostileActions{


    public TheBrute(String name, int health, int damage,int goldDrop) {
        super(name, health, damage, goldDrop);
    }


    @Override
    public void initialMessage() {
        System.out.println("""
                            Hideous and terrible creature stands in front of you, it is at least 3 meters tall!
                            It has terrible musculature, and it's skin is so white, you can almost see the sinew and
                            blood vessels underneath.
                            You get the feeling like you are in for a long haul.
                            """);
    }


    @Override
    public void attackPattern(int damageValue) {
        damageValue += this.damage - TheKnight.getArmor();
        damageValue = accountForArmor(damageValue);
        damageValue *= 1.25;
        TheKnight.setCurrentHealth(TheKnight.getCurrentHealth()-damageValue);
        System.out.println("You were hit by " + this.name + " for " + ConsoleColors.RED + damageValue + ConsoleColors.RESET + " Points of damage!");
    }
}
