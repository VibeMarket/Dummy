package xyx.dashnetwork.dummy.status.utils;

public class ChatColor {

    public static String translate(String string) {
        char[] array = string.toCharArray();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(array[i + 1]) > -1) {
                array[i] = 167;
                array[i + 1] = Character.toLowerCase(array[i + 1]);
            }
        }

        return new String(array);
    }

}
