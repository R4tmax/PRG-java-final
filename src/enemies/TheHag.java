package enemies;

import knight.TheKnight;

public class TheHag extends Monster implements HostileActions{
    public TheHag(String name, int health, int damage, int goldDrop) {
        super(name, health, damage, goldDrop);
    }

    @Override
    public void initialMessage() {
        System.out.println("""
                            Jak zkoumáš zákoutí bažiny, náhle cítíš podivnou auru ve vzduchu,
                            a všechny tvé chlupy na těle se zježí.
                            Náhle spatříš shrbenou a ohyzdnou figuru vycházet z vysoké trávy a roští.
                            Tvůj instinkt ti říká, že jestli potřebuješ použít svá kouzla, měl bys tak učinit hned.
                            """);
    }

    //TODO: Attacks damage mana pools as well
    @Override
    public void attackPattern(int damageValue) {
        damageValue += this.damage - TheKnight.armor;
        TheKnight.health -= damageValue;
        TheKnight.mana -= damageValue;
        System.out.println("Byl jsi zasažen od " + this.name + " za " + damageValue + " bodů poškození!");
        System.out.println("Stejné poškození obdržel i tvůj mana pool!");
    }
}
