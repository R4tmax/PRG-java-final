package gameworld;

import enemies.Monster;
import items.Item;

import java.util.Scanner;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022.
 *
 * <p>
 *      Class representing different rooms, or segments,
 *      of the game world.
 *
 *      Class instances are put into 2D array via the Map class
 *      and then accessed via getter from the Map class.
 *
 *      Rooms as such hold different instances of different objects, which are
 *      interacted by in different manners by the player.
 * </p>
 *
 * @see Item
 * @see Monster
 * @see RoomType
 */
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

    public void setDescription(String description) {
        this.description = description;
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

    /**
     * Standard Room constructor.
     * Used by Map factory method.
     *
     * @param name String representing the titular name of the room, printed to the player
     * @param description String representing the description of the room, printed to the player as guidance and flavor text
     * @param isLocked boolean representing the accessibility of the room, if false, room cannot be entered.
     * @param roomLoot Reference to the Item class tied to the room, null if no item present.
     * @param roomEnemy Reference to the Monster class tied to the room, null if no enemy present.
     * @param roomBehavior ENUM representing the available interaction in the room.
     *
     * @see Map#fillMap()
     * @see Monster
     * @see Item
     * @see knight.TheKnight#moveKnight(Scanner)
     */
    public Room(String name, String description, boolean isLocked, Item roomLoot, Monster roomEnemy, RoomType roomBehavior) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.roomLoot = roomLoot;
        this.roomEnemy = roomEnemy;
        this.roomBehavior = roomBehavior;
    }

    /**
     * toString override used for testing purposes.
     *
     * @return String representation of all the different room attributes.
     */
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


