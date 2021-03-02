package de.reitler.domain.entities;

import de.reitler.core.DateCalculator;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Task {

    private UUID id;
    private String title;
    private String description;
    private Calendar startsAt;
    private Calendar deadline;
    private Calendar doneAt;
    private Calendar timeIntervall; //null or 0 if task isn't repetitve
    private boolean switchRoommate; //null or 0 if task isn't repetitve

    /**
     * Constructor for creating repetitive tasks
     * @param title
     * @param description
     * @param timeIntervall
     * @param switchRoommate
     */
    public Task(String title, String description, Date timeIntervall, boolean switchRoommate){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.switchRoommate = switchRoommate;
        this.doneAt = null;
        this.startsAt = Calendar.getInstance();
        this.startsAt.setTime(new Date());
        this.timeIntervall = Calendar.getInstance();
        this.timeIntervall.setTime(timeIntervall);
        this.deadline = DateCalculator.add(startsAt, this.timeIntervall);
    }

    /**
     * Constructor for creating simple (non repetitive) tasks
     * @param title
     * @param description
     * @param deadline
     */
    public Task(String title, String description, Date deadline){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.startsAt = Calendar.getInstance();
        this.startsAt.setTime(new Date());
        this.deadline = Calendar.getInstance();
        this.deadline.setTime(deadline);
        this.doneAt = null;
        this.switchRoommate = false;
        this.timeIntervall = null;
    }

    public UUID getId() {
        return id;
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

    public Calendar getTimeIntervall() {
        return timeIntervall;
    }

    public void setTimeIntervall(Calendar timeIntervall) {
        this.timeIntervall = timeIntervall;
    }

    public boolean isSwitchRoommate() {
        return switchRoommate;
    }

    public void setSwitchRoommate(boolean switchRoommate) {
        this.switchRoommate = switchRoommate;
    }

}
