package de.reitler.application.roommate;


import de.reitler.application.tasks.TaskDTO;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoommateHandler {

    @Autowired
    private RoommateService roommateService;

    private RoommateDTO input;

    public RoommateHandler(){
    }

    /**
     * Validates the Token and creates a new Roommate if the token is valid
     * @param userData
     * @return the UserData or null (if the Token isn't valid or something went wrong)
     */
    public RoommateDTO create(RoommateDTO userData){
        input = userData;
        if(input != null){
                roommateService.create(new Roommate(input.getId(), input.getDisplayName(), input.getEmail(), input.getPicture()));
            }
        return input;
    }

    public RoommateDTO update(RoommateDTO newRoommate){
        Roommate result = roommateService.update(roommateService.getById(newRoommate.getId()));
        return new RoommateDTO(result.getId(), result.getDisplayname(), result.getEmail(), result.getPicture());
    }

    public RoommateDTO getById(String id){
        Roommate roommate =  roommateService.getById(id);
        return new RoommateDTO(roommate.getId(), roommate.getDisplayname(), roommate.getEmail(), roommate.getPicture());
    }

    public List<TaskDTO> getAllTasks(String id){
         //List list = roommateService.getAllTasks(id);
         //list.stream().map(x -> new TaskDTO() {
         //})
        return null;
    }

}
