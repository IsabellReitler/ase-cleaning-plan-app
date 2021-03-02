package de.reitler.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Calendar;
import java.util.Date;

public class DateCalculatorTest {

    private static Calendar startDate;
    private static Calendar timeIntervall;

    @BeforeAll
    public static void initializeDates(){
        startDate = Calendar.getInstance();
        timeIntervall = Calendar.getInstance();
        startDate.setTime( new Date(2020, Calendar.JANUARY,01,01,01,00));
        timeIntervall.setTime(new Date(0,0,01,0,0,0));
    }

    @Test
    public void addDatesTest(){
        Assertions.assertEquals(new Date(2020,Calendar.JANUARY,02,01,01,00), DateCalculator.add(startDate, timeIntervall).getTime());
    }

    @Test
    public void testNewDate(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Assertions.assertEquals(c.get(Calendar.YEAR), 2021);
    }
}
