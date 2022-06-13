package knight;

import auxiliary.Auxiliary;
import enemies.TheBrute;
import gameworld.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellsTest {


    @BeforeEach
    void prepareMap (){
        Map.fillMap();
        TheKnight.getPosition().horizontal = 4;
        TheKnight.getPosition().vertical = 2;
    }

    @Test
    void testBuffSpells () {
        Auxiliary.sanitizeStats();
        Spells.prayerOfResolve();
        assertEquals(4,TheKnight.getArmor());
        assertEquals(15,TheKnight.getCurrentMana());

        TheKnight.setCurrentMana(TheKnight.MAX_MANA);

        Spells.prayerOfStrength();
        assertEquals(20, TheKnight.getDamage());
        assertEquals(15,TheKnight.getCurrentMana());

        TheKnight.setCurrentMana(TheKnight.MAX_MANA);
        TheKnight.setCurrentHealth(TheKnight.MAX_MANA);

        Spells.heal();
        assertEquals(TheKnight.MAX_HEALTH,TheKnight.getCurrentHealth());
        assertEquals(35,TheKnight.getCurrentMana());

    }


    @Test
    void testManaConsumption ()
    {
        Auxiliary.sanitizeStats();
        Spells.prayerOfResolve();
        Spells.prayerOfResolve();
        assertEquals(4,TheKnight.getArmor());
        assertEquals(15,TheKnight.getCurrentMana());
        Spells.heal();
        assertEquals(200, TheKnight.getCurrentHealth());
        assertEquals(0,TheKnight.getCurrentMana());
        TheKnight.setCurrentHealth(25);
        Spells.heal();
        assertEquals(25, TheKnight.getCurrentHealth());
        assertEquals(0,TheKnight.getCurrentMana());

    }

    @Test
    void testConcentration () {
        Auxiliary.sanitizeStats();

        TheKnightTest.moveKnightTest('n');
        TheKnightTest.moveKnightTest('n');
        TheKnightTest.moveKnightTest('w');
        TheKnightTest.moveKnightTest('w');

        Spells.prayerOfResolve();
        assertEquals(2,TheKnight.getArmor());
        assertEquals(15,TheKnight.getCurrentMana());

        Auxiliary.sanitizeStats();

        Spells.prayerOfStrength();
        assertEquals(15,TheKnight.getDamage());
        assertEquals(15,TheKnight.getCurrentMana());

    }

    @Test
    void testOffensiveSpells () {
        TheBrute spellDummy = new TheBrute("Spelldummy",10000,0,0);
        Map.getCurrentPosition(TheKnight.getPosition().getHorizontal(),TheKnight.getPosition().getVertical()).setRoomEnemy(spellDummy);
        Spells.lightningTouch();
        assertEquals(9900,spellDummy.getHealth());
        assertEquals(45,TheKnight.getCurrentMana());

        Auxiliary.sanitizeStats();

        Spells.lightningStrike();
        assertEquals(9300,spellDummy.getHealth());
        assertEquals(0,TheKnight.getCurrentMana());

        Auxiliary.sanitizeStats();

        Spells.holySmite();
        assertEquals(9100,spellDummy.getHealth());
        assertEquals(25,TheKnight.getCurrentMana());
        assertEquals(TheKnight.MAX_HEALTH,TheKnight.getCurrentHealth());
    }


}