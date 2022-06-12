package gameLogic;

import auxiliary.Auxiliary;
import gameworld.Interactions;
import gameworld.Map;

import java.util.Date;

public class Setup extends Thread {

    private static final Date todayIs = new Date();
    public static void initializeData () {
        Map.fillMap();
        Interactions.initializeDialogues();
    }


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
