package de.reitler.core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateCalculator {

    public static Date add(Date startDate, int timeIntervall){

        Calendar cTotal = Calendar.getInstance();
        cTotal.setTime(startDate);
        cTotal.add(Calendar.DATE,timeIntervall );
        return cTotal.getTime();
    }
}
