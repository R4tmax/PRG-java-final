package enemies;

import knight.TheKnight;

public class TheBrute extends Monster implements HostileActions{


    public TheBrute(String name, int health, int damage,int goldDrop) {
        super(name, health, damage, goldDrop);
    }


    @Override
    public void initialMessage() {
        System.out.println("""
                            Stojí proti tobě obrovské stvoření, minimálně tři metry vysoké!
                            Není jiného zbytí, tohle bude maraton!
                            """);
    }

    //TODO: Attacks once, but with higher damage than others
    @Override
    public void attackPattern(int damageValue) {
        damageValue += this.damage - TheKnight.armor;
        damageValue *= 1.25;
        TheKnight.currentHealth -= damageValue;
        System.out.println("Byl jsi zasažen od " + this.name + " za " + damageValue + " bodů poškození!");
    }
}
