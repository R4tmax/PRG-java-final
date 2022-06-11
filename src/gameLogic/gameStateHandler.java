package gameLogic;

import gameworld.Map;
import knight.TheKnight;

public class gameStateHandler {

    protected static boolean validateKnightState() {

        return !TheKnight.isDead;
    }

    protected static void updateRoomDescriptor () {
        //TODO: change descriptions of hostile rooms
    }

    protected static boolean unlockFinalBoss() {
        if (Map.getCurrentPosition(2,0).roomEnemy == null && Map.getCurrentPosition(2,4).roomEnemy == null)
        {
            Map.getCurrentPosition(0,2).isLocked = false;
            System.out.println("Cítíš, že něco je v tvém okolí jinak, dříve nepřístupné oblasti jsou nyní na dosah");
            return true;
        }
        return false;
    }

    protected static boolean gameWon(){
        return Map.getCurrentPosition(0, 2).roomEnemy == null;
    }



}
