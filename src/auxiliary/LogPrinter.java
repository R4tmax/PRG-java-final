package auxiliary;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Martin Kadlec
 * @version Last refactored on 14.06.2022
 *
 * <p>
 *      Simple logging component used to output
 *      detected exceptions into provided text file.
 * </p>
 *
 */
public class LogPrinter {

    public static Logger logger = Logger.getLogger("ExceptionsDetected");

    /**
     * Prepares the logger for use.
     */
    public static void initializeLogger () {
        FileHandler filehandler;
        try {
            filehandler = new FileHandler("./Logs/errLogs.txt");
            logger.addHandler(filehandler);
            SimpleFormatter formatter = new SimpleFormatter();
            filehandler.setFormatter(formatter);

            logger.info("Init successful!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
