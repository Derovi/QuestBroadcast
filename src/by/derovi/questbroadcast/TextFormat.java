package by.derovi.questbroadcast;

/**
 * Created by derovi on 21.11.2017.
 */
public class TextFormat {
    public static String format(String string) {
        string = string.replace("&", "§");
        return string;
    }
}
