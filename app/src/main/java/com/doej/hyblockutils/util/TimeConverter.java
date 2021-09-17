package com.doej.hyblockutils.util;

/**
 *
 * @author doej1367
 */
public class TimeConverter {

    private static final long birthday = 1559790900;
    private static final long dayDuration = 20 * 60;

    public static HyBlockTime getIGTime() {
        long hyblockAge = (System.currentTimeMillis() / 1000) - birthday;
        long years = hyblockAge / (dayDuration * 31 * 12);
        long months = (hyblockAge / (dayDuration * 31)) - years * 12;
        long days = hyblockAge / dayDuration - (months * 31 + years * 12 * 31);
        long hours = hyblockAge / 50 - (days * 24 + months * 31 * 24 + years * 12 * 31 * 24);
        return new HyBlockTime(years, months, days, hours);
    }

    @SuppressWarnings("unused")
    public static HyBlockTime addIGTime(HyBlockTime time1, HyBlockTime time2) {
        return validate(new HyBlockTime(time1.getYear() + time2.getYear(), time1.getMonth() + time2.getMonth(), time1.getDay() + time2.getDay(), time1.getHour() + time2.getHour()));
    }

    public static HyBlockTime validate(HyBlockTime time) {
        long overflowDays = time.getHour() / 24;
        long hours = time.getHour() - overflowDays * 24;
        long overflowMonths = (time.getDay() + overflowDays) / 31;
        long days = (time.getDay() + overflowDays) - overflowMonths * 31;
        long overflowYears = (time.getMonth() + overflowMonths) / 12;
        long months = (time.getMonth() + overflowMonths) - overflowYears * 12;
        long years = time.getYear() + overflowYears;
        return new HyBlockTime(years, months, days, hours);
    }

    public static long getIGYear() {
        return (System.currentTimeMillis() / 1000 - birthday) / (dayDuration * 31 * 12);
    }

    public static long getTimestampInSeconds(HyBlockTime t) {
        return birthday + t.getHour() * 50 + t.getDay() * dayDuration + t.getMonth() * dayDuration * 31 + t.getYear() * dayDuration * 31 * 12;
    }

    public static long getBirthday() {
        return birthday;
    }

}
