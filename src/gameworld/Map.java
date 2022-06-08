package gameworld;

public class Map {
    public static Room [][] gameMap = new Room[5][5];

    public static void printMap () {
        for (int i = 0; i < Map.gameMap.length; i++) {
            for (int j = 0; j < Map.gameMap.length; j++) {
                System.out.println(Map.gameMap[i][j]);
            }
            System.out.println();
        }
    }
}
