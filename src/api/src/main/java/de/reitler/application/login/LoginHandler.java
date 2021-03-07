package de.reitler.application.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.reitler.application.services.RoommateServiceImpl;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginHandler {
    //@Autowired
    //RoommateRepository roommate;
    @Autowired
    RoommateServiceImpl roommateService;

    private HouseholdService household;
    private UserInputMapper mapper;

    UserDataInput input;

    public LoginHandler(UserInputMapper mapper){
        //this.roommate = roommateRepository;
        //this.household = householdService;
        this.mapper = mapper;
    }

    /**
     * Validates the Token and creates a new Roommate if the token is valid
     * @param userData
     * @return the UserData or null (if the Token isn't valid or something went wrong)
     */
    public UserDataInput signIn(String userData){
        try {
            input = mapper.mapToUserDataInput(userData);
            System.out.println("UserInput-Name: "+input.getDisplayName());
        if(input != null){
                Roommate roommate1 = new Roommate(input.getId(), input.getDisplayName(), input.getEmail(), input.getPicture());
                System.out.println("Roommate-Objekt: "+roommate1.toString()+"; Roommate-Name: "+roommate1.getDisplayname());
                roommateService.create(roommate1);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return input;
    }

}
