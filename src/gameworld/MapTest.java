package gameworld;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
class MapTest {

        @Test
        void checkMapInit () {
            Map.fillMap();
            Map.printMap();

            assertTrue(Map.getCurrentPosition(0, 2).isLocked);
            assertNotNull(Map.getCurrentPosition(0, 2).roomEnemy);
            assertNotNull(Map.getCurrentPosition(2, 0).roomEnemy);
            assertNotNull(Map.getCurrentPosition(2, 4).roomEnemy);
            assertNotNull(Map.getCurrentPosition(2, 2).roomLoot);
            assertNotNull(Map.getCurrentPosition(4, 2).roomLoot);

        }
}