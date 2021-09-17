package com.doej.hyblockutils.util;

import android.graphics.Color;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.doej.hyblockutils.util.TimeConverter.validate;

/**
 *
 * @author doej1367
 */
public class HyBlockEvent implements Comparable<HyBlockEvent> {
    private final String name;
    private final int color;
    private final HyBlockTime eventOffsetInSeconds;
    private final int durationInDays;

    private static final HyBlockEvent BANK_INTEREST_1 = new HyBlockEvent("BANK INTEREST", Color.rgb(255, 217, 0), new HyBlockTime(0, 1, 1, 0), 0);
    private static final HyBlockEvent BANK_INTEREST_2 = new HyBlockEvent("BANK INTEREST", Color.rgb(255, 217, 0), new HyBlockTime(0, 4, 1, 0), 0);
    private static final HyBlockEvent BANK_INTEREST_3 = new HyBlockEvent("BANK INTEREST", Color.rgb(255, 217, 0), new HyBlockTime(0, 7, 1, 0), 0);
    private static final HyBlockEvent BANK_INTEREST_4 = new HyBlockEvent("BANK INTEREST", Color.rgb(255, 217, 0), new HyBlockTime(0, 10, 1, 0), 0);
    private static final HyBlockEvent TRAVELING_ZOO_1 = new HyBlockEvent("TRAVELING ZOO", Color.rgb(86, 199, 86), new HyBlockTime(0, 4, 1, 0), 3);
    private static final HyBlockEvent TRAVELING_ZOO_2 = new HyBlockEvent("TRAVELING ZOO", Color.rgb(86, 199, 86), new HyBlockTime(0, 10, 1, 0), 3);
    private static final HyBlockEvent NEW_MAYOR = new HyBlockEvent("NEW MAYOR", Color.DKGRAY, new HyBlockTime(0, 3, 27, 0), 0);
    private static final HyBlockEvent ELECTION_STARTS = new HyBlockEvent("ELECTION STARTS", Color.BLACK, new HyBlockTime(0, 6, 27, 0), 0);
    private static final HyBlockEvent SPOOKY_FISHING = new HyBlockEvent("SPOOKY FISHING", Color.rgb(107, 166, 255), new HyBlockTime(0, 8, 26, 0), 9);
    private static final HyBlockEvent SPOOKY_FESTIVAL = new HyBlockEvent("SPOOKY FESTIVAL", Color.rgb(193, 122, 255), new HyBlockTime(0, 8, 29, 0), 3);
    private static final HyBlockEvent WINTER_ISLAND = new HyBlockEvent("WINTER ISLAND", Color.LTGRAY, new HyBlockTime(0, 12, 1, 0), 31);
    private static final HyBlockEvent SEASON_OF_JERRY = new HyBlockEvent("SEASON OF JERRY", Color.rgb(250, 80, 80), new HyBlockTime(0, 12, 24, 0), 3);
    private static final HyBlockEvent NEW_YEAR = new HyBlockEvent("NEW YEAR", Color.rgb(168, 168, 168), new HyBlockTime(0, 12, 29, 0), 3);
    public static final HyBlockEvent[] EVENTS_ALL = {BANK_INTEREST_1, NEW_MAYOR, BANK_INTEREST_2, TRAVELING_ZOO_1, ELECTION_STARTS, BANK_INTEREST_3,
            SPOOKY_FISHING, SPOOKY_FESTIVAL, BANK_INTEREST_4, TRAVELING_ZOO_2, WINTER_ISLAND, SEASON_OF_JERRY, NEW_YEAR};
    public static final HyBlockEvent[] SINGLE_NEXT_EVENTS = {getNextBankInterestEvent(), NEW_MAYOR, getNextTravelingZooEvent(), ELECTION_STARTS,
            SPOOKY_FISHING, SPOOKY_FESTIVAL, WINTER_ISLAND, SEASON_OF_JERRY, NEW_YEAR};

    private static HyBlockEvent getNextBankInterestEvent() {
        List<HyBlockEvent> tmp = Arrays.asList(BANK_INTEREST_1, BANK_INTEREST_2, BANK_INTEREST_3, BANK_INTEREST_4);
        Collections.sort(tmp, HyBlockEvent::compareTo);
        return tmp.get(0);
    }

    private static HyBlockEvent getNextTravelingZooEvent() {
        return TRAVELING_ZOO_1.getSecondsToNextOccurrence() < TRAVELING_ZOO_2.getSecondsToNextOccurrence() ? TRAVELING_ZOO_1 : TRAVELING_ZOO_2;
    }


    public HyBlockEvent(String name, int color, HyBlockTime eventOffsetInSeconds, int durationInDays) {
        this.name = name;
        this.eventOffsetInSeconds = eventOffsetInSeconds;
        this.durationInDays = durationInDays;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return getName().replaceAll("[0-9]", "").hashCode();
    }

    public int getColor() {
        return color;
    }

    public HyBlockTime getEventOffset() {
        return eventOffsetInSeconds;
    }

    public HyBlockTime getNextEventOccurrence() {
        return validate(new HyBlockTime(0, 0, 0, (TimeConverter.getIGTime().getAgeInSeconds() + getSecondsToNextOccurrence()) / 50));
    }

    public int getDurationInIgDays() {
        return durationInDays;
    }

    public int getIgDaysToNextOccurrence() {
        return getSecondsToNextOccurrence() / (60 * 20);
    }

    public int getSecondsToNextOccurrence() {
        if (getName().equalsIgnoreCase("DARK AUCTION")) {
            int DATime = 53; // alarm always in the 53th minute
            int secondsSinceLastFullHour = (int) ((System.currentTimeMillis() / 1000) % 3600);
            int tmp_res = (DATime * 60 - secondsSinceLastFullHour);
            return (tmp_res <= 0) ? tmp_res + 3600 : tmp_res;
        }
        long eventTimeInSeconds = TimeConverter.getTimestampInSeconds(getEventOffset())
                + (getEventOffset().getYear() <= 0 ? ((TimeConverter.getIGYear() - 1) * 12 * 31 * 60 * 20) : 0);
        while (eventTimeInSeconds + (durationInDays * 20 * 60) <= (System.currentTimeMillis() / 1000))
            eventTimeInSeconds += 12 * 31 * 60 * 20;
        return (int) ((eventTimeInSeconds - System.currentTimeMillis() / 1000));
    }

    @Override
    public int compareTo(HyBlockEvent o) {
        return getSecondsToNextOccurrence() - o.getSecondsToNextOccurrence();
    }
}
