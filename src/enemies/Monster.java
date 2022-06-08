package enemies;

public abstract class Monster {
    public String name;
    public int Health;
    public int damage;

    public Monster(String name, int health, int damage) {
        this.name = name;
        Health = health;
        this.damage = damage;
    }
}
