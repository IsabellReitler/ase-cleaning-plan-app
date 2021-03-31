package de.reitler.app.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

import java.io.OptionalDataException;
import java.util.Calendar;
import java.util.Date;

public class Task {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("starts_at")
    @Expose
    private Date startsAt;

    @Nullable
    @SerializedName("deadline")
    @Expose
    private Date deadline;

    @Nullable
    @SerializedName("done_at")
    @Expose
    private Date doneAt;

    @Nullable
    @SerializedName("time_interval")
    @Expose
    private Date timeInterval; //null or 0 if task isn't repetitve

    @SerializedName("switch_roommate")
    @Expose
    private boolean switchRoommate; //null or 0 if task isn't repetitve

    @SerializedName("roommateID")
    @Expose
    private String roommate;

    public Task(){

    }

    public Task(String id, String title, String description, Date startsAt, Date deadline, String roommateId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.deadline = deadline;
        this.roommate = roommateId;
        this.switchRoommate = false;
    }

    public Task(String id, String title, String description, Date startsAt, Date doneAt, Date timeInterval, boolean switchRoommate, String roommate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.doneAt = doneAt;
        this.timeInterval = timeInterval;
        this.switchRoommate = switchRoommate;
        this.roommate = roommate;
    }

    public Task(String id, String title, String description, Date startsAt, Date deadline, Date doneAt, Date timeInterval, boolean switchRoommate, String roommate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.deadline = deadline;
        this.doneAt = doneAt;
        this.timeInterval = timeInterval;
        this.switchRoommate = switchRoommate;
        this.roommate = roommate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(Date doneAt) {
        this.doneAt = doneAt;
    }

    public Date getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Date timeInterval) {
        this.timeInterval = timeInterval;
    }

    public boolean isSwitchRoommate() {
        return switchRoommate;
    }

    public void setSwitchRoommate(boolean switchRoommate) {
        this.switchRoommate = switchRoommate;
    }

    public String getRoommateID() {
        return roommate;
    }

    public void setRoommateId(String roommate) {
        this.roommate = roommate;
    }

}
