package knight;

import enemies.TheBrute;
import gameworld.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static gameworld.Map.getCurrentPosition;
import static org.junit.jupiter.api.Assertions.*;


class TheKnightTest {
    @BeforeEach
     void prepareMap (){
        Map.fillMap();
        TheKnight.getPosition().horizontal = 4;
        TheKnight.getPosition().vertical = 2;
    }


    @Test
    void checkCorrectBaseSetting () {
        assertAll(("Check if base stats are correctly set on game load up"),
            () -> assertEquals(200, TheKnight.getCurrentHealth()),
            () -> assertEquals(50,TheKnight.getCurrentMana()),
            () -> assertEquals(15,TheKnight.getDamage()),
            () -> assertEquals(2,TheKnight.getArmor()),
            () -> assertEquals(250,TheKnight.getGoldHeld())
        );
    }

    @Test
    void testMatriarchPathLock () {

        assertEquals(4, TheKnight.getPosition().horizontal);
        assertEquals(2,TheKnight.getPosition().vertical);

        moveKnightTest('n');

        assertEquals(3, TheKnight.getPosition().horizontal);
        assertEquals(2,TheKnight.getPosition().vertical);

        moveKnightTest('n');
        moveKnightTest('n');
        moveKnightTest('n');
        moveKnightTest('n');

        assertEquals(1, TheKnight.getPosition().horizontal);
        assertEquals(2,TheKnight.getPosition().vertical);



    }

    @Test
    void testFinalPath () {
        Map.getCurrentPosition(0,2).setLocked(false);

        assertEquals(4, TheKnight.getPosition().horizontal);
        assertEquals(2,TheKnight.getPosition().vertical);

        moveKnightTest('n');
        moveKnightTest('n');
        moveKnightTest('n');
        moveKnightTest('n');

        assertEquals(0, TheKnight.getPosition().horizontal);
        assertEquals(2,TheKnight.getPosition().vertical);
    }

    @Test
    void testBrutePath () {
        assertEquals(4, TheKnight.getPosition().horizontal);
        assertEquals(2,TheKnight.getPosition().vertical);

        moveKnightTest('n');
        moveKnightTest('n');
        moveKnightTest('w');
        moveKnightTest('w');

        assertEquals(2, TheKnight.getPosition().horizontal);
        assertEquals(0,TheKnight.getPosition().vertical);
    }

    @Test
    void testHagPath () {
        assertEquals(4, TheKnight.getPosition().horizontal);
        assertEquals(2,TheKnight.getPosition().vertical);

        moveKnightTest('n');
        moveKnightTest('n');
        moveKnightTest('e');
        moveKnightTest('e');

        assertEquals(2, TheKnight.getPosition().horizontal);
        assertEquals(4,TheKnight.getPosition().vertical);
    }

    @Test
    void testOutOfBounds () {
        assertEquals(4, TheKnight.getPosition().horizontal);
        assertEquals(2,TheKnight.getPosition().vertical);

        moveKnightTest('w');
        moveKnightTest('w');
        moveKnightTest('w');
        moveKnightTest('w');

        assertEquals(4, TheKnight.getPosition().horizontal);
        assertEquals(0,TheKnight.getPosition().vertical);
    }

    @Test
    void testDamageProjection () {

        TheBrute targetDummy = new TheBrute("Target dummy",500,0,0);
        TheKnight.resolveAttack(0,targetDummy);
        assertTrue(targetDummy.getHealth() <= 485 && targetDummy.getHealth()>=470);

    }


    protected static void moveKnightTest(char x) {

        int tmpX = TheKnight.getPosition().horizontal;
        int tmpY = TheKnight.getPosition().vertical;

        switch (x) {
            case 'n' -> TheKnight.position.horizontal -= 1;
            case 'w' -> TheKnight.position.vertical -= 1;
            case 'e' -> TheKnight.position.vertical += 1;
            case 's' -> TheKnight.position.horizontal += 1;
        }

        testValidator(tmpX,tmpY);

    }


    private static void testValidator (int tmpX, int tmpY) {
        try {
            if (getCurrentPosition(TheKnight.position.horizontal, TheKnight.position.vertical).getLockedStatus()) {
                TheKnight.position.horizontal = tmpX;
                TheKnight.position.vertical = tmpY;
            }
        } catch (Exception e) {
            TheKnight.position.horizontal = tmpX;
            TheKnight.position.vertical = tmpY;
        }
    }

}