package gameLogic;

import gameworld.Map;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

/**
 * @author Martin Kadlec
 * @version 0.0.1 , Project started 16.5. 2022
 */
public class Main {
    public static void main(String[] args) {
        Map.fillMap();
        Map.printMap();

        Scanner input = new Scanner(System.in);

        //TODO:fancy switch
        while (true) {
            String command = input.nextLine();
            try {
                switch (command) {
                    case "move":
                    case "cast":
                    case "lookaround":
                    case "loot":
                    case "interact":
                    default:
                        System.out.println("Neznámý příkaz");

                }
            }
            catch (Exception e) {
                System.out.println("Error");
            }

        }


    }
}