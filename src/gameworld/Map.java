package gameworld;

public class Map {
    public static Room [][] gameMap = new Room[5][5];

    public static Room[][] getGameMap() {
        return gameMap;
    }

    public static void printMap () {
        for (int i = 0; i < Map.gameMap.length; i++) {
            for (int j = 0; j < Map.gameMap.length; j++) {
                System.out.println("Current coordinates: X-" + i + "|" + "Y-" + j + " " + Map.gameMap[i][j]);
            }
            System.out.println();
        }
    }

    public static void fillMap () {
       Map.gameMap[4][2] = new Room("Nádvoří vesnice", "placeholder", false , null, null, RoomType.TALKABLE );
    }
}
