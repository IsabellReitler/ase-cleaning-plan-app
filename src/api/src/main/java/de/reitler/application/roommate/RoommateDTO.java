package de.reitler.application.roommate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class RoommateDTO extends RepresentationModel<RoommateDTO> {

    @JsonProperty("id")
    private String id;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("picture")
    private String picture;

    @JsonCreator
    public RoommateDTO(String id, String displayName, String email, String picture){
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.picture = picture;
    }


    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }
}
