package de.reitler.application.tasks;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;

import java.util.Calendar;
import java.util.Date;

public class TaskDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("starts_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startsAt;

    @Nullable
    @JsonProperty("deadline")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;

    @Nullable
    @JsonProperty("done_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date doneAt;

    @Nullable
    @JsonProperty("time_interval")
    private int timeInterval; //null or 0 if task isn't repetitve

    @JsonProperty("switch_roommate")
    private boolean switchRoommate; //null or 0 if task isn't repetitve

    @JsonProperty("roommateID")
    private String roommate;

    public TaskDTO(){

    }

    public TaskDTO(String id, String title, String description, Date startsAt, Date deadline, String roommateId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.deadline = deadline;
        this.roommate = roommateId;
        this.switchRoommate = false;
    }

    public TaskDTO(String id, String title, String description, Date startsAt, Date doneAt, int timeInterval, boolean switchRoommate, String roommate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.doneAt = doneAt;
        this.timeInterval = timeInterval;
        this.switchRoommate = switchRoommate;
        this.roommate = roommate;
    }

    public TaskDTO(String id, String title, String description, Date startsAt, Date deadline, Date doneAt, int timeInterval, boolean switchRoommate, String roommate) {
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

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
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
