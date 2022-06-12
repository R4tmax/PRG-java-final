package gameworld;

import enemies.Monster;
import items.Item;

public class Room {
    protected String name;
    protected String description;
    protected boolean isLocked;
    protected Item roomLoot;
    protected Monster roomEnemy;
    protected RoomType roomBehavior;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean getLockedStatus() {
        return isLocked;
    }

    public Item getRoomLoot() {
        return roomLoot;
    }

    public void setRoomLoot(Item roomLoot) {
        this.roomLoot = roomLoot;
    }

    public Monster getRoomEnemy() {
        return roomEnemy;
    }

    public void setRoomEnemy(Monster roomEnemy) {
        this.roomEnemy = roomEnemy;
    }

    public RoomType getRoomBehavior() {
        return roomBehavior;
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


