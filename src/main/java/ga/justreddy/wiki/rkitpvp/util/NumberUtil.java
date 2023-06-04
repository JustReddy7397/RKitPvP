package ga.justreddy.wiki.rkitpvp.util;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.text.DecimalFormat;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NumberUtil {

    static DecimalFormat df = new DecimalFormat("#,###"), df2 = new DecimalFormat("###.#");

    public static String formatNumber(int amount) {
        return df.format(amount);
    }

    public static String formatNumber(double amount) {
        return df.format(amount);
    }

    private static final String[] suffix = new String[]{"","k", "m", "b", "t"};
    private static final int MAX_LENGTH = 4;

    public static String formatPerMil(double number) {
        String r = new DecimalFormat("##0E0").format(number);
        r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
        while(r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")){
            r = r.substring(0, r.length()-2) + r.substring(r.length() - 1);
        }
        return r;
    }

    public static String formatOneDecimal(double amount) {
        return df2.format(amount);
    }

    public static String toFormat(int seconds) {
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        return twoDigitString(minutes) + ":" + twoDigitString(seconds);
    }

    public static boolean isBetween(int toCheck, int from, int to){
        return from <= toCheck && toCheck <= to;
    }

    private static String twoDigitString(int number) {
        if (number == 0) {
            return "00";
        }
        if (number / 10 == 0) {
            return "0" + number;
        }
        return String.valueOf(number);
    }

}
