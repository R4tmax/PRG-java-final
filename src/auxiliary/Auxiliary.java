package auxiliary;

import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022
 *
 * <p>
 *      This class holds methods not logically or
 *      thematically coherent with other packages of the
 *      programme.
 * </p>
 *
 *
 */
public class Auxiliary {

    /**
     * Invokes the thread Sleep functions to slow
     * down printing of the intro texts to highlight text printing
     * during the setup.
     *
     * @see gameLogic.Setup
     */
    public static void slowDownText () {
        try {
            sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Invokes the thread sleep functions
     * during the Combat. encounter runtime to
     * space out turns.
     *
     * @see gameLogic.Combat#encounter(Scanner)
     */
    public static void slowDownCombat() {
        try {
            sleep(1250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
