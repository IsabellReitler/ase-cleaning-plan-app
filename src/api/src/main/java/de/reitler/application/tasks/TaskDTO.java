package de.reitler.application.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.reitler.domain.entities.Roommate;

import java.util.Calendar;

public class TaskDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("starts_at")
    private Calendar startsAt;

    @JsonProperty("deadline")
    private Calendar deadline;

    @JsonProperty("done_at")
    private Calendar doneAt;

    @JsonProperty("time_interval")
    private Calendar timeInterval; //null or 0 if task isn't repetitve

    @JsonProperty("switch_roommate")
    private boolean switchRoommate; //null or 0 if task isn't repetitve

    @JsonProperty("roommate")
    private String roommate;

    public TaskDTO(){

    }

    public TaskDTO(String id, String title, String description, Calendar startsAt, Calendar deadline, String roommateId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.deadline = deadline;
        this.roommate = roommateId;
    }

    public TaskDTO(String id, String title, String description, Calendar startsAt, Calendar doneAt, Calendar timeInterval, boolean switchRoommate, String roommate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.doneAt = doneAt;
        this.timeInterval = timeInterval;
        this.switchRoommate = switchRoommate;
        this.roommate = roommate;
    }

    public TaskDTO(String id, String title, String description, Calendar startsAt, Calendar deadline, Calendar doneAt, Calendar timeInterval, boolean switchRoommate, String roommate) {
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
