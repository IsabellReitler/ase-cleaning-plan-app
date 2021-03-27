package de.reitler.app.model;

import androidx.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

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
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar startsAt;

    @Nullable
    @SerializedName("deadline")
    @Expose
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar deadline;

    @Nullable
    @SerializedName("done_at")
    @Expose
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar doneAt;

    @Nullable
    @SerializedName("time_interval")
    @Expose
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar timeInterval; //null or 0 if task isn't repetitve

    @SerializedName("switch_roommate")
    @Expose
    private boolean switchRoommate; //null or 0 if task isn't repetitve

    @SerializedName("roommate")
    @Expose
    private String roommate;

    public Task(){

    }

    public Task(String id, String title, String description, Calendar startsAt, Calendar deadline, String roommateId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.deadline = deadline;
        this.roommate = roommateId;
        this.switchRoommate = false;
    }

    public Task(String id, String title, String description, Calendar startsAt, Calendar doneAt, Calendar timeInterval, boolean switchRoommate, String roommate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.doneAt = doneAt;
        this.timeInterval = timeInterval;
        this.switchRoommate = switchRoommate;
        this.roommate = roommate;
    }

    public Task(String id, String title, String description, Calendar startsAt, Calendar deadline, Calendar doneAt, Calendar timeInterval, boolean switchRoommate, String roommate) {
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

    public Calendar getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Calendar startsAt) {
        this.startsAt = startsAt;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public Calendar getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(Calendar doneAt) {
        this.doneAt = doneAt;
    }

    public Calendar getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Calendar timeInterval) {
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
