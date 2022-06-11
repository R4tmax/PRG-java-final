package gameLogic;

import gameworld.Interactions;
import gameworld.Map;

public class Setup extends Thread {

    public static void initializeData () {
        Map.fillMap();
        Map.printMap();
    }


    public static void printPrologue () {
        System.out.println("""
                            Vítejte v mé adventuře!
                            Made by Martin Kadlec
                            ©2022
                            """);

        slowDownText();

        System.out.println("""
                            Jste rytíř zdejšího království,
                            byl jste vyslán s drobnou družinou zvědů,
                            aby jste vyčistili okolí jedné vesnice od zrůd
                            podivného původu.
                            """);

        slowDownText();

        System.out.println("""
                            Pokud se dostaneš do úzkých, použij příkaz HELP!
                            Hodně štěstí!
                            """);

        slowDownText();

        Map.printPosition();
    }

    private static void slowDownText () {
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
