package gameLogic;

import gameworld.Map;

public class Setup {

    public static void initializeData () {
        Map.fillMap();
        Map.printMap();
    }

    public static void printPrologue () {
        System.out.println("Welcome to Revachol!");
    }

}
