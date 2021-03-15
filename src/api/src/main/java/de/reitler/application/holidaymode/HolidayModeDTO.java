package de.reitler.application.holidaymode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

import java.util.Calendar;

public class HolidayModeDTO extends RepresentationModel<HolidayModeDTO> {
    @JsonProperty("roommate_id")
    private String roommateId;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar endDate;

    public HolidayModeDTO(String id, Calendar holidayMode) {
        this.roommateId = id;
        this.endDate = holidayMode;
    }

    public String getRoommateId() {
        return roommateId;
    }

    public void setRoommateId(String roommateId) {
        this.roommateId = roommateId;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

}
