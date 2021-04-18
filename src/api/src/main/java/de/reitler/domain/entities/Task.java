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
    private Date startsAt;

    @Column(name="deadline")
    private Date deadline;

    @Column(name="done_at")
    private Date doneAt;

    @Column(name="time_intervall")
    private int timeIntervall;

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

    public Task(UUID id, String title, String description, Date startsAt, Date deadline, Date doneAt, int timeIntervall, boolean switchRoommate, Roommate roommate) {
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
    public Task(String title, String description, int timeIntervall, boolean switchRoommate, Roommate roommate){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.switchRoommate = switchRoommate;
        this.doneAt = null;
        this.startsAt = Calendar.getInstance().getTime();
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
    public Task(String title, String description, Date deadline, Roommate roommate){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.startsAt = Calendar.getInstance().getTime();
        this.deadline = deadline;
        this.doneAt = null;
        this.switchRoommate = false;
        this.timeIntervall = 0;
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

    public int getTimeIntervall() {
        return timeIntervall;
    }

    public void setTimeIntervall(int timeIntervall) {
        this.timeIntervall = timeIntervall;
    }

    public boolean isSwitchRoommate() {
        return switchRoommate;
    }

    public void setSwitchRoommate(boolean switchRoommate) {
        this.switchRoommate = switchRoommate;
    }

}
