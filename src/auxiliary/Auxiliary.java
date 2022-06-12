package auxiliary;

import static java.lang.Thread.sleep;

public class Auxiliary {

    public static void slowDownText () {
        try {
            sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void slowDownCombat() {
        try {
            sleep(1250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
