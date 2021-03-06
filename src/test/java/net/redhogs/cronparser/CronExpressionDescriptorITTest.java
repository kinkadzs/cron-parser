package net.redhogs.cronparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class CronExpressionDescriptorITTest {

    private static final Locale ITALIAN = Locale.ITALIAN;

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("Ogni secondo", CronExpressionDescriptor.getDescription("* * * * * *", ITALIAN));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("Ogni 45 secondi", CronExpressionDescriptor.getDescription("*/45 * * * * *", ITALIAN));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Ogni minuto tra 11:00 AM e 11:10 AM", CronExpressionDescriptor.getDescription("0-10 11 * * *", ITALIAN));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Ogni minuto", CronExpressionDescriptor.getDescription("* * * * *", ITALIAN));
        Assert.assertEquals("Ogni minuto", CronExpressionDescriptor.getDescription("*/1 * * * *", ITALIAN));
        Assert.assertEquals("Ogni minuto", CronExpressionDescriptor.getDescription("0 0/1 * * * ?", ITALIAN));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Ogni 5 minuti", CronExpressionDescriptor.getDescription("*/5 * * * *", ITALIAN));
        Assert.assertEquals("Ogni 5 minuti", CronExpressionDescriptor.getDescription("0 */5 * * * *", ITALIAN));
        Assert.assertEquals("Ogni 10 minuti", CronExpressionDescriptor.getDescription("0 0/10 * * * ?", ITALIAN));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Ogni ora", CronExpressionDescriptor.getDescription("0 0 * * * ?", ITALIAN));
        Assert.assertEquals("Ogni ora", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", ITALIAN));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("Alle 11:30 AM", CronExpressionDescriptor.getDescription("30 11 * * *", ITALIAN));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("Alle 11:00 PM, lunedì fino a venerdì", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", ITALIAN));
        Assert.assertEquals("Alle 11:30 AM, lunedì fino a venerdì", CronExpressionDescriptor.getDescription("30 11 * * 1-5", ITALIAN));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Ogni minuto, solo in marzo", CronExpressionDescriptor.getDescription("* * * 3 *", ITALIAN));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Ogni minuto, solo in marzo e giugno", CronExpressionDescriptor.getDescription("* * * 3,6 *", ITALIAN));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("Alle 2:30 PM e 4:30 PM", CronExpressionDescriptor.getDescription("30 14,16 * * *", ITALIAN));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("Alle 6:30 AM, 2:30 PM e 4:30 PM", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", ITALIAN));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("Alle 9:46 AM, solo di lunedì", CronExpressionDescriptor.getDescription("46 9 * * 1", ITALIAN));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("Alle 12:23 PM, nel 15 giorno del mese", CronExpressionDescriptor.getDescription("23 12 15 * *", ITALIAN));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("Alle 12:23 PM, solo in gennaio", CronExpressionDescriptor.getDescription("23 12 * JAN *", ITALIAN));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("Alle 12:23 PM, solo in gennaio", CronExpressionDescriptor.getDescription("23 12 ? JAN *", ITALIAN));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("Alle 12:23 PM, gennaio fino a febbraio", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", ITALIAN));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("Alle 12:23 PM, gennaio fino a marzo", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", ITALIAN));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("Alle 12:23 PM, solo di domenica", CronExpressionDescriptor.getDescription("23 12 * * SUN", ITALIAN));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Ogni 5 minuti, 3:00 PM, lunedì fino a venerdì", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", ITALIAN));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Ogni minuto, nel terzo lunedì del mese", CronExpressionDescriptor.getDescription("* * * * MON#3", ITALIAN));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Ogni minuto, nell ultimo {0} del mese", CronExpressionDescriptor.getDescription("* * * * 4L", ITALIAN));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Ogni 5 minuti, nell' ultimo giorno del mese, solo in gennaio", CronExpressionDescriptor.getDescription("*/5 * L JAN *", ITALIAN));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("Alle 2:02:30 PM", CronExpressionDescriptor.getDescription("30 02 14 * * *", ITALIAN));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        //TODO Flip 5 & seconds
        Assert.assertEquals("5 secondi fino a 10 dopo il minute", CronExpressionDescriptor.getDescription("5-10 * * * * *", ITALIAN));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        //TODO Flip 5 & seconds
        Assert.assertEquals("5 secondi fino a 10 dopo il minute, da 30 fino a 35 minute dopo l'ora, tra 10:00 AM e 12:00 PM",
                CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", ITALIAN));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("30 secondi dopo il minuto, ogni 5 minuti", CronExpressionDescriptor.getDescription("30 */5 * * * *", ITALIAN));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("30 minuti dopo l'ora, tra 10:00 AM e 1:00 PM, solo di mercoledì e venerdì",
                CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", ITALIAN));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("10 secondi dopo il minuto, ogni 5 minuti", CronExpressionDescriptor.getDescription("10 0/5 * * * ?", ITALIAN));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Ogni 3 minuti, da 02 fino a 59 minute dopo l'ora, 1:00 AM, 9:00 AM, e 10:00 PM, compreso tra i giorni 11 e 26 del mese, gennaio fino a giugno",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", ITALIAN));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("Alle 6:00 AM", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", ITALIAN));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("05 minuti dopo l'ora", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", ITALIAN));
    }

}
