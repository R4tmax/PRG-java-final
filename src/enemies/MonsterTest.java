package enemies;

import auxiliary.Auxiliary;
import knight.TheKnight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    @Test
    void testHagDamage () {
        TheKnight.setArmor(0);
        TheHag dummyHag = new TheHag("Dummyhag",1000,10,0);
        dummyHag.attackPattern(0);
        assertEquals(190,TheKnight.getCurrentHealth());
        assertEquals(40,TheKnight.getCurrentMana());

        Auxiliary.sanitizeStats();
    }

    @Test
    void testBruteDamage () {
        TheKnight.setArmor(0);
        TheBrute dummyBrute = new TheBrute("Dummybrute",1000,20,0);
        dummyBrute.attackPattern(0);
        assertEquals(175,TheKnight.getCurrentHealth());

        Auxiliary.sanitizeStats();
    }

    @Test
    void testMatriarchDamage () {
        TheKnight.setArmor(0);
        TheMatriarch dummyMatriarch = new TheMatriarch("Dummymatriarch",1000,10);
        dummyMatriarch.attackPattern(0);
        assertEquals(180,TheKnight.getCurrentHealth());

        Auxiliary.sanitizeStats();
    }

    @Test
    void testArmorProtection () {
        TheKnight.setArmor(1000);

        TheHag dummyHag = new TheHag("Dummyhag",1000,10,0);
        dummyHag.attackPattern(0);
        TheBrute dummyBrute = new TheBrute("Dummybrute",1000,20,0);
        dummyBrute.attackPattern(0);
        TheMatriarch dummyMatriarch = new TheMatriarch("Dummymatriarch",1000,10);
        dummyMatriarch.attackPattern(0);

        assertEquals(196,TheKnight.getCurrentHealth());
        assertEquals(49,TheKnight.getCurrentMana());

        Auxiliary.sanitizeStats();
    }



}