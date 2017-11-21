package by.derovi.questbroadcast;

/**
 * Created by derovi on 21.11.2017.
 */
public class InfoManager {
    public static void exception(String str) {
        System.out.println("§4[QUEB] " + str);
    }

    public static void warning(String str) {
        System.out.println("§6[QUEB] " + str);
    }

    public static void log(String str) {
        System.out.println("§a[QUEB] " + str);
    }

    public static void serverRE(String text) {
        exception("§cUnable to reach server: " + text);
    }

    public static void taskSE(String text) {
        exception("§cError while " + text);
    }
}
