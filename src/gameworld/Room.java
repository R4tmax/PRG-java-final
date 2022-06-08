package gameworld;

import enemies.Monster;
import items.Item;

public class Room {
    public String name;
    public String description;
    public boolean isLocked;
    public Item roomLoot;
    public Monster roomEnemy;
    public RoomType roomBehavior;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room(String name, String description, boolean isLocked, Item roomLoot, Monster roomEnemy, RoomType roomBehavior) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.roomLoot = roomLoot;
        this.roomEnemy = roomEnemy;
        this.roomBehavior = roomBehavior;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isLocked=" + isLocked +
                ", roomLoot=" + roomLoot +
                ", roomEnemy=" + roomEnemy +
                ", roomBehavior=" + roomBehavior +
                '}';
    }
}


