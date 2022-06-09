package enemies;

public class TheMatriarch extends Monster implements HostileActions{
    public TheMatriarch(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void initialMessage() {

    }

    //TODO:Attacks twice in a row
    @Override
    public int damageModifier() {
        return 0;
    }
}
