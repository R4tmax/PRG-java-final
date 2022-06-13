package enemies;

import auxiliary.ConsoleColors;
import knight.TheKnight;

public class TheMatriarch extends Monster implements HostileActions{
    public TheMatriarch(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void initialMessage() {
        System.out.println("""
                            As you scout the rocky hills at the base of the mountain range.
                            You cannot shake the feeling of being watched.
                            After a while, you start noticing dead bodies. Torn apart and left to rot.
                            You catch a sudden movement through the visor of your helmet...
                            DEFEND YOURSELF!!!
                            """);
    }


    @Override
    public void attackPattern(int damageValue) {
        damageValue += this.damage - TheKnight.getArmor();
        damageValue = accountForArmor(damageValue);
        TheKnight.setCurrentHealth(TheKnight.getCurrentHealth()-damageValue);
        System.out.println("Your were hit by " + this.name + " for " + ConsoleColors.RED + damageValue + ConsoleColors.RESET + " points of damage!");
        TheKnight.setCurrentHealth(TheKnight.getCurrentHealth()-damageValue);
        System.out.println("You were hit AGAIN by " + this.name + " for " + ConsoleColors.RED + damageValue + ConsoleColors.RESET + " points of damage!");
        System.out.println("The Matriarch is a fierce enemy!!!");
    }
}
