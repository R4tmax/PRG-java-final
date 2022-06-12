package gameLogic;

import gameworld.Map;
import knight.TheKnight;

public class gameStateHandler {

    protected static boolean validateKnightState() {

        return !TheKnight.isDead;
    }

    protected static void updateRoomDescriptor () {
        //TODO: change descriptions of warnings near the boss rooms
    }

    protected static boolean unlockFinalBoss() {
        if (Map.getCurrentPosition(2,0).getRoomEnemy() == null && Map.getCurrentPosition(2,4).getRoomEnemy() == null)
        {
            Map.getCurrentPosition(0,2).setLocked(false);
            System.out.println("You feel like something has changed in the world.");
            System.out.println("Previously unreachable areas are now available.");
            return true;
        }
        return false;
    }

    protected static boolean gameWon(){
        return Map.getCurrentPosition(0, 2).getRoomEnemy() == null;
    }



}
