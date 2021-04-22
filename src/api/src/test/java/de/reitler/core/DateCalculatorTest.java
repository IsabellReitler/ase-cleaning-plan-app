package de.reitler.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Calendar;
import java.util.Date;

public class DateCalculatorTest {

    private static Date startDate;
    private static int timeIntervall;

    @BeforeAll
    public static void initializeDates(){
        timeIntervall = 1;
        startDate = new Date(2020, Calendar.JANUARY,01,01,01,00);
    }

    @Test
    public void addDatesTest(){
        Assertions.assertEquals(new Date(2020,Calendar.JANUARY,02,01,01,00), DateCalculator.add(startDate, timeIntervall));
    }

    @Test
    public void testNewDate(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Assertions.assertEquals(c.get(Calendar.YEAR), 2021);
    }
}
