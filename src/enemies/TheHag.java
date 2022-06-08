package enemies;

public class TheHag extends Monster implements HostileActions{
    public TheHag(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void initialMessage() {

    }

    @Override
    public int damageModifier() {
        return 0;
    }
}
