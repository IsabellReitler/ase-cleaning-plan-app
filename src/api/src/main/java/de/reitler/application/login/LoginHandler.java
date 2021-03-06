package de.reitler.application.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.reitler.domain.repositories.HouseholdRepository;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.repositories.RoommateRepository;
import de.reitler.domain.services.HouseholdService;
import de.reitler.domain.services.RoommateService;


public class LoginHandler {
    private RoommateService roommate;
    private HouseholdService household;
    private UserInputMapper mapper;

    UserDataInput input;

    public LoginHandler(RoommateService roommateService, HouseholdService householdService, UserInputMapper mapper){
        this.roommate = roommateService;
        this.household = householdService;
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
        if(input != null){
                roommate.create(new Roommate(input.getId(), input.getDisplayName(), input.getEmail(), input.getPicture()));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return input;
    }

}
