package knight;


import gameworld.Map;
import gameworld.Room;

public class TheKnight {
    public static int health = 200;
    public static int mana = 50;
    public static int armor = 0;
    protected static Room currentRoom = Map.gameMap[4][2];
    public static int goldHeld = 10;

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Room currentRoom) {
        TheKnight.currentRoom = currentRoom;
    }

    //todo: room occupancy?
    //TODO: One instance vs. static declaration?



}
