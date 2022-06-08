package knight;

import gameworld.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TheKnightTest {


    @Test
    void knightPositionTracking () {
        Map.fillMap();
        System.out.println(TheKnight.getCurrentRoom());
        TheKnight.setCurrentRoom(Map.gameMap[3][2]);
        System.out.println(TheKnight.getCurrentRoom());
    }


}