package enemies;

public class TheBrute extends Monster implements HostileActions{

    public TheBrute(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void initialMessage() {

    }

    //TODO: Attacks once, but with higher damage than others
    @Override
    public int damageModifier() {
        return 0;
    }
}
