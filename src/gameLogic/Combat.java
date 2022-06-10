package gameLogic;

import enemies.*;
import gameworld.Map;
import knight.TheKnight;

public class Combat {

    public static void encounter () {
       Monster enemyPresent = Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical).roomEnemy;

       enemyPresent.initialMessage();

       //if (enemyPresent instanceof TheBrute) ((TheBrute) enemyPresent).initialMessage();

    }

}
