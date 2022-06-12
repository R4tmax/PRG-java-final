package enemies;

public abstract class Monster implements HostileActions{
    protected String name;
    protected int health;
    protected int damage;

    protected boolean isDead = false;

    protected int goldDrop;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getGoldDrop() {
        return goldDrop;
    }


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
