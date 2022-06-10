package enemies;

public abstract class Monster implements HostileActions{
    public String name;
    public int Health;
    public int damage;

    public Monster(String name, int health, int damage) {
        this.name = name;
        Health = health;
        this.damage = damage;
    }


}
