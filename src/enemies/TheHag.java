package enemies;

public class TheHag extends Monster implements HostileActions{
    public TheHag(String name, int health, int damage) {
        super(name, health, damage);
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
    public void attackPattern() {
    }
}
