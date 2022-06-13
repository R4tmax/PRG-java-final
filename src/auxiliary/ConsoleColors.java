package auxiliary;

/**
 * @author Martin Kadlec
 * @version Last refactored on 12.06.2022.
 *
 * Holds constants with
 * ANSI escape characters used to
 * modify standard 8bit color terminal output.
 */
public class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String SIMPLE_UNDERLINE = "\u001b[4m"; //UNDERLINE
    public static final String SIMPLE_BOLD = "\u001b[1m"; //BOLD


    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW

    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN

}