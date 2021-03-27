package de.reitler.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class HolidayMode{

    @SerializedName("roommate_id")
    @Expose
    private String id;

    @SerializedName("end_date")
    @Expose
    private Calendar endDate;


    public HolidayMode(String id, Calendar endDate) {
        this.id = id;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
}
