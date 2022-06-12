package enemies;

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
        damageValue += this.damage - TheKnight.armor;
        damageValue = accountForArmor(damageValue);
        TheKnight.currentHealth -= damageValue;
        System.out.println("Your were hit by " + this.name + " for " + damageValue + " points of damage!");
        TheKnight.currentHealth -= damageValue;
        System.out.println("You were hit AGAIN by " + this.name + " for " + damageValue + " points of damage!");
        System.out.println("The Matriarch is a fierce enemy!!!");
    }
}
