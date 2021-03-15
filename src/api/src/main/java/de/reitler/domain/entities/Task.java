package de.reitler.domain.entities;

import de.reitler.core.DateCalculator;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="task")
public class Task {

    @Id
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="starts_at")
    private Calendar startsAt;

    @Column(name="deadline")
    private Calendar deadline;

    @Column(name="done_at")
    private Calendar doneAt;

    @Column(name="time_intervall")
    private Calendar timeIntervall; //null or 0 if task isn't repetitve

    @Column(name= "switch_roommate")
    private boolean switchRoommate; //null or 0 if task isn't repetitve

    public Roommate getRoommate() {
        return roommate;
    }

    public void setRoommate(Roommate roommate) {
        this.roommate = roommate;
    }

    @ManyToOne
    private Roommate roommate;

    public Task(){

    }

    public Task(UUID id, String title, String description, Calendar startsAt, Calendar deadline, Calendar doneAt, Calendar timeIntervall, boolean switchRoommate, Roommate roommate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.deadline = deadline;
        this.doneAt = doneAt;
        this.timeIntervall = timeIntervall;
        this.switchRoommate = switchRoommate;
        this.roommate = roommate;
    }

    /**
     * Constructor for creating repetitive tasks
     * @param title
     * @param description
     * @param timeIntervall
     * @param switchRoommate
     */
    public Task(String title, String description, Calendar timeIntervall, boolean switchRoommate, Roommate roommate){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.switchRoommate = switchRoommate;
        this.doneAt = null;
        this.startsAt = Calendar.getInstance();
        this.startsAt.setTime(new Date());
        this.timeIntervall = timeIntervall;
        this.deadline = DateCalculator.add(startsAt, this.timeIntervall);
        this.roommate = roommate;
    }

    /**
     * Constructor for creating simple (non repetitive) tasks
     * @param title
     * @param description
     * @param deadline
     */
    public Task(String title, String description, Calendar deadline, Roommate roommate){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.startsAt = Calendar.getInstance();
        this.startsAt.setTime(new Date());
        this.deadline = deadline;
        this.doneAt = null;
        this.switchRoommate = false;
        this.timeIntervall = null;
        this.roommate =roommate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
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
