package de.reitler.application.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.reitler.domain.HouseholdRepository;
import de.reitler.domain.Roommate;
import de.reitler.domain.RoommateRepository;



public class LoginHandler {
    private RoommateRepository roommateRepo;
    private HouseholdRepository householdRepo;
    private UserInputMapper mapper;

    UserDataInput input;

    public LoginHandler(RoommateRepository roommateRepo, HouseholdRepository householdRepo, UserInputMapper mapper){
        this.roommateRepo = roommateRepo;
        this.householdRepo = householdRepo;
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
                roommateRepo.create(new Roommate(input.getId(), input.getDisplayName(), input.getEmail(), input.getPicture()));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return input;
    }

}
