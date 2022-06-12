package enemies;

import knight.TheKnight;

public class TheHag extends Monster implements HostileActions{
    public TheHag(String name, int health, int damage, int goldDrop) {
        super(name, health, damage, goldDrop);
    }

    @Override
    public void initialMessage() {
        System.out.println("""
                            The feeling you had around here. The dense air and shivers?
                            It is so much stronger here, you almost feel dizzy.
                            Suddenly, you notice movement in nearby reeds.
                            A small, but very much so disgusting womanlike creature stands in front of you.
                            Your instinct tells you, if you feel the need to cast a spell, you should do it NOW.
                            """);
    }


    @Override
    public void attackPattern(int damageValue) {
        damageValue += this.damage - TheKnight.armor;
        TheKnight.currentHealth -= damageValue;
        TheKnight.currentMana -= damageValue;
        System.out.println("You were hit by " + this.name + " for " + damageValue + " points of damage!");
        System.out.println("The same damage was taken by your mana pool!");
    }
}
