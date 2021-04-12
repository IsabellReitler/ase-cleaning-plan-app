package de.reitler.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

public class HolidayMode{

    @SerializedName("roommate_id")
    @Expose
    private String id;

    @SerializedName("end_date")
    @Expose
    private Date endDate;


    public HolidayMode(String id, Date endDate) {
        this.id = id;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
