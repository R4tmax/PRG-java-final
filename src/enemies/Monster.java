package enemies;

public abstract class Monster implements HostileActions{
    public String name;
    public int health;
    public int damage;

    public boolean isDead = false;

    public int goldDrop;

    public Monster(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public Monster(String name, int health, int damage, int goldDrop) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.goldDrop = goldDrop;
    }

    protected int accountForArmor (int damageValue) {
        return Math.max(damageValue, 1);
    }


}
