package gameLogic;

import auxiliary.Auxiliary;
import gameworld.Interactions;
import gameworld.Map;

import java.util.Date;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022.
 *
 * <p>
 *     Setup class facilitates the initial 'factory' methods.
 *     and intro of the game. See respective methods for more info.
 * </p>
 *
 * @see Main
 */
public class Setup extends Thread {

    private static final Date todayIs = new Date();

    /**
     * Method calls the factory methods of Map and
     * Interactions classes which fills the datastructures
     * with necessary data. Constructors of gameObjects are invoked
     * during the process.
     *
     * @see Map
     * @see Interactions
     * @see gameworld.Room
     */
    public static void initializeData () {
        Map.fillMap();
        Interactions.initializeDialogues();
    }

    /**
     * Method prints initial messages for the user
     * on the game start up, giving info about the creator
     * and the lore.
     * Take note that JavaDate is called here as per assignment.
     * (accessed as static variable)
     * The function uses auxiliary sleep function to slow down printing to stdout.
     * (readability reasons), finally, it shows the information about the first room of the game.
     *
     * @see Map#printPosition()
     */
    public static void printPrologue () {
        System.out.println("""
                            Welcome to my adventure!
                            Made by Martin Kadlec
                            ©2022 @CoE In Prague
                            As final project for subject 4IT101
                            """);
        System.out.println("Today is " + todayIs + "\n");

        Auxiliary.slowDownText();

        System.out.println("""
                            You are Knight of prestigious monster slaying order,
                            devoted to the King of the realm.
                            You had arrived couple of days ago, and the scouts were hard at work.
                            The threat was detected up north, now it is up to you.
                            Slay the scourge, reclaim the area!
                            """);

        Auxiliary.slowDownText();

        System.out.println("""
                            Use the HELP command if you get stuck!
                            Good luck!
                            """);

        Auxiliary.slowDownText();

        Map.printPosition();
    }



}
