package de.hauke_stieler.rednimer.Common.DomainValue;

/**
 * Created by hauke on 11.06.17.
 */

public class TimeUnit {
    // All numbers are milliseconds
    public static final TimeUnit MINUTE = new TimeUnit(60000);
    public static final TimeUnit HOUR = new TimeUnit(3600000);
    public static final TimeUnit DAY = new TimeUnit(86400000);
    public static final TimeUnit WEEK = new TimeUnit(604800000);

    public final int Milliseconds;

    private TimeUnit(int millis) {
        Milliseconds = millis;
    }

    public static TimeUnit get(String timeUnitString) throws IllegalArgumentException {

        switch (timeUnitString.toLowerCase()) {
            case "minute":
                return MINUTE;
            case "hour":
                return HOUR;
            case "day":
                return DAY;
            case "week":
                return WEEK;
        }

        throw new IllegalArgumentException("The time unit " + timeUnitString + " is not known.");
    }
}
