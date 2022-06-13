package gameLogic;

import gameworld.Map;
import knight.TheKnight;

/**
 *
 * This class is responsible for maintaining
 * Top level game states up to date.
 * See respective methods for more information.
 *
 *   @author Martin Kadlec
 *   @version Last refactored on 12.06.2022
 *
 * @see Main
 */
public class gameStateHandler {

    protected static boolean validateKnightState() {

        return !TheKnight.getIsDead();
    }

    protected static void updateRoomDescriptor () {
        if (Map.getCurrentPosition(2,0).getRoomEnemy() == null) {
            Map.getCurrentPosition(2,1).setDescription("""
                It feels, like someone lives nearby. You notice that a lot of the bushes and small trees
                in the area have damaged bark and branches, as if something regularly smashed through here.
                As you head west, the damage gets more and more pronounced.
                You already figured that out, of course, and put an end to it.
                Dead gem merchant is still lying dead in one of the bushes,
                You wonder, if you already checked his pockets.
                """);

            Map.getCurrentPosition(1,0).setDescription("""
                You walk amongst the thinly space trees and admire the view to the west.
                You cannot quite get there, as a narrow cliff block your way. But you see distant cities
                and castles on the horizon. Places where you come from and for which you fight.
                Memory of bloody encounter runs across your cortex, as you look south.
                Up north, hills rise up, while eastern view is blocked by the trees.
                Blue herbs are growing here.
                """);

            Map.getCurrentPosition(3,0).setDescription("""
                Same as along the Kings Road, which lies to your south now.
                You rest easy looking north, knowing you slain the beast.
                However desolate area might look.
                You can still make out village buildings to the east.
                """);
        }

        if (Map.getCurrentPosition(2,4).getRoomEnemy() == null) {
            Map.getCurrentPosition(2,3).setDescription("""
                You made your way to what seems to be some sort of a bog.
                Short colorful flowers are scattered everywhere, but soil gives way under your weight.
                After some deliberation you find a path through all of it. But you are not exactly comfortable.
                You already know, that a dead hag lies in the march to the east.
                Fortunately the bog gives way in all other directions.
                 """);

            Map.getCurrentPosition(1,4).setDescription("""
                This place looks almost surreal. You see a huge marsh opening to east and south.
                While mountain range looms to the north. Western direction is saddled by trees and bushes.
                Flies and mosquitoes constantly annoy you. Looking south fills your with dread,
                as memory of fight with The Hag returns to your visual cortex.
                 """);

            Map.getCurrentPosition(3,4).setDescription("""
                As southern fields disappear under the horizon, you start walking up north, with the river to your right.
                Even though the hag lies dead, the air is still heavy, weird.
                You notice now more clearly, that the path west is steadily declining.
                 """);
        }
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
