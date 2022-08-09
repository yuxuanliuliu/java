package com.cultivation.javaBasic;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeTest {
    @SuppressWarnings({"UnusedAssignment", "ConstantConditions"})
    @Test
    void should_be_able_to_use_absolute_time_to_measure_duration() throws Exception {
        Duration elapsed = null;

        // TODO: please measure the execution time of `delayOneSecond` using `Instant` and `Duration`
        // <--start
        Instant before = Instant.now();
        System.out.println(before.getEpochSecond());
        System.out.println(before.toEpochMilli());


        // --end-->

        delayOneSecond();

        // TODO: please measure the execution time of `delayOneSecond` using `Instant` and `Duration`
        // <--start
        Instant after = Instant.now();
        elapsed = Duration.between(before, after);

        assertEquals(1, elapsed.getSeconds());
    }

    @Test
    void should_be_careful_when_adding_by_month() {
        LocalDate endOfJan = LocalDate.of(2016, 1, 31);
        LocalDate localDate = endOfJan.plusMonths(1);

        // TODO: please modify the following code to pass the test
        // <--start
        final LocalDate expected = LocalDate.of(2016, 2, 29);
        // --end-->
        Instant now1 = Instant.now();
        System.out.println(now1);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        assertEquals(expected, localDate);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_get_the_next_or_the_same_tuesday() {
        LocalDate date = LocalDate.of(2016, 1, 1);

        // TODO: please get the next Tuesday or the same day if today is Tuesday
        // <--start
        LocalDate nextTuesday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        // --end-->
        final LocalDate expected = LocalDate.of(2016, 1, 5);

        assertEquals(expected, nextTuesday);
    }

    @Test
    void should_turn_around_if_exceeds_24_hours_for_local_time() {
        LocalTime bedTime = LocalTime.of(22, 30);
        LocalTime wakeUpTime = bedTime.plusHours(8);

        // TODO: please give expected local time directly.
        // <--start
        final LocalTime expected = LocalTime.of(6, 30);
        System.out.println(bedTime);
        System.out.println(expected);
        // --end-->

        assertEquals(expected, wakeUpTime);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    void should_correctly_calculate_daylight_saving_time() {
        ZonedDateTime meeting = ZonedDateTime.of(
            LocalDateTime.of(2013, 10, 24, 8, 0, 0),
            ZoneId.of("Europe/Berlin"));

        // TODO: please arrange the meeting to 7 days later.
        // <--start
        ZonedDateTime actual = meeting.plusDays(7);
        // --end-->

        final ZonedDateTime expected = ZonedDateTime.of(
            LocalDateTime.of(2013, 10, 31, 8, 0, 0),
            ZoneId.of("Europe/Berlin"));

        ZoneOffset offset = expected.getOffset();
        System.out.println(offset);

        final ZonedDateTime expected2 = ZonedDateTime.of(
            LocalDateTime.of(2013, 10, 31, 8, 0, 0),
            ZoneOffset.of("+01:00"));

        ZoneId zoneId = expected2.getZone();
        assertEquals(expected, expected2);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_format_to_iso_date_time() {
        ZonedDateTime beijingTime = ZonedDateTime.of(
            LocalDateTime.of(2018, 8, 3, 0, 0, 0),
            ZoneId.of("Asia/Shanghai"));

        // TODO: please format date time to ISO 8601 Date Time with Offset Information
        // <--start
        LocalDateTime localDateTime = beijingTime.toLocalDateTime();
        OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.ofHours(8));
        //UTC时差
        String format = DateTimeFormatter.ISO_OFFSET_TIME.format(beijingTime);

        String formatted = offsetDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        String format2 = offsetDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);

        System.out.println(formatted);
        System.out.println(format2);

        // --end-->

        final String expect = "2018-08-03T00:00:00+08:00";

        TemporalAccessor parse = DateTimeFormatter.ISO_DATE_TIME.parse(expect);


        assertEquals(expect, formatted);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_be_able_to_parse_date_time() {
        final String expect = "2018-08-03T00:00:00+08:00";

        // TODO: please parse the date time string (ISO Offset format).
        // <--start
        DateTimeFormatter isoOffsetTime = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        DateTimeFormatter isoOffsetTime2 = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor parse1 = isoOffsetTime.parse(expect);
        TemporalAccessor parse = isoOffsetTime2.parse(expect);
        ZonedDateTime parsed1 = ZonedDateTime.from(parse1);
        ZonedDateTime parsed = ZonedDateTime.from(parse);

        System.out.println(parse1);
        System.out.println(parse1);
        System.out.println(parsed1);
        System.out.println(parsed);

        // --end-->

        ZonedDateTime expected = ZonedDateTime.of(
            LocalDateTime.of(2018, 8, 3, 0, 0, 0),
            ZoneId.of("Asia/Shanghai"));

        // What is the behavior if we use assertEquals?
        assertTrue(parsed.isEqual(expected));
    }

    private static void delayOneSecond() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    void get_time_difference_between_two_time() {
        LocalTime start = LocalTime.of(6, 20, 0);
        LocalTime end = start.withHour(10);
        long hourDifference = Duration.between(start, end).get(ChronoUnit.HOURS); //only accept nanos and seconds
        long hourDifference1 = ChronoUnit.HOURS.between(start, end);
        System.out.println(hourDifference);
        System.out.println(hourDifference1);
    }

    @Test
    void flight_test() {
        LocalDateTime of = LocalDateTime.now();
        Locale locale = new Locale("zh", "cn");
        DateTimeFormatter date = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(locale);
        String format1 = date.format(of);
        System.out.println("--------"+format1);
        OffsetDateTime of1 = OffsetDateTime.of(of, ZoneOffset.of("+02:00"));
        String format = date.format(of);
        System.out.println(format);
        System.out.println(of1);
    }
}





/*
 * - What is an `Instant` anyway.
 * epoch
 * - How do you convert `LocalDateTime/LocalDate` to `Instant`.
 *
 */