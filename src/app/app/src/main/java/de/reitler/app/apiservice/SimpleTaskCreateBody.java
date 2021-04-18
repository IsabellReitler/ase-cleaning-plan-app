package de.reitler.app.apiservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SimpleTaskCreateBody {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("deadline")
    @Expose
    private Date deadline;

    @SerializedName("roommateID")
    private String roommate;

    public SimpleTaskCreateBody(String title, String description, Date deadline, String roommate) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.roommate = roommate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getRoommate() {
        return roommate;
    }

    public void setRoommate(String roommate) {
        this.roommate = roommate;
    }
}
