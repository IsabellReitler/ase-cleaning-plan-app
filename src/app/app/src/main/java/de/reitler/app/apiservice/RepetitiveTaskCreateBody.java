package de.reitler.app.apiservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RepetitiveTaskCreateBody {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("time_interval")
    @Expose
    private int timeInterval;

    @SerializedName("switch_roommate")
    @Expose
    private boolean switchRoommate;

    @SerializedName("roommateID")
    @Expose
    private String roommate;

    public RepetitiveTaskCreateBody(String title, String description, int timeInterval, boolean switchRoommate, String roommate) {
        this.title = title;
        this.description = description;
        this.timeInterval = timeInterval;
        this.switchRoommate = switchRoommate;
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

    public String getRoommate() {
        return roommate;
    }

    public void setRoommate(String roommate) {
        this.roommate = roommate;
    }
}
