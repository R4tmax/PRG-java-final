package knight;

import gameworld.Map;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TheKnightTest {


    @Test
    void knightPositionTracking () {
        Map.fillMap();
        System.out.println(Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical));
        TheKnight.position.horizontal -=1;
        System.out.println(Map.getCurrentPosition(TheKnight.position.horizontal,TheKnight.position.vertical));

    }


}