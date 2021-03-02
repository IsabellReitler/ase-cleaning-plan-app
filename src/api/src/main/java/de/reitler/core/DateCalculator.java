package de.reitler.core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateCalculator {

    public static Calendar add(Calendar startDate, Calendar timeIntervall){

        Calendar cTotal = (Calendar) startDate.clone();
        cTotal.set(Calendar.ERA, GregorianCalendar.AD);
        timeIntervall.set(Calendar.ERA, GregorianCalendar.AD);
        cTotal.add(Calendar.YEAR, timeIntervall.get(Calendar.YEAR-1)-1); // Because of the functionality of the gregorian calendar
        cTotal.add(Calendar.MONTH, timeIntervall.get(Calendar.MONTH)); // Zero-based months
        cTotal.add(Calendar.DATE, timeIntervall.get(Calendar.DATE));
        cTotal.add(Calendar.HOUR_OF_DAY, timeIntervall.get(Calendar.HOUR_OF_DAY));
        cTotal.add(Calendar.MINUTE, timeIntervall.get(Calendar.MINUTE));
        cTotal.add(Calendar.SECOND, timeIntervall.get(Calendar.SECOND));
        cTotal.add(Calendar.MILLISECOND, timeIntervall.get(Calendar.MILLISECOND));

        return cTotal;
    }
}
