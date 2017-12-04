package shared.util;

public class Log {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    //info
    public static void info(String className, String message) {
        System.out.println(ANSI_BLUE + className + " : " + message + ANSI_RESET);
    }

    //error
    public static void error(String className, String message) {
        System.out.println(ANSI_RED + className + " : " + message + ANSI_RESET);
    }

    //debug
    public static void debug(String className, String message) {
        System.out.println(ANSI_CYAN + className + " : " + message + ANSI_RESET);
    }

    //warning
    public static void warning(String className, String message) {
        System.out.println(ANSI_YELLOW + className + " : " + message + ANSI_RESET);
    }

    public static void success(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

}
