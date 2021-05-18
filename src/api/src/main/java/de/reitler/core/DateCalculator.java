package de.reitler.core;

import java.util.Calendar;
import java.util.Date;

public class DateCalculator {

    public static Date add(Date startDate, int timeInterval){
        Calendar cTotal = Calendar.getInstance();
        cTotal.setTime(startDate);
        cTotal.add(Calendar.DATE,timeInterval );
        return cTotal.getTime();
    }
}
