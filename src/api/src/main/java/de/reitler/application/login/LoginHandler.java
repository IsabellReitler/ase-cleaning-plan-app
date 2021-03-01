package de.reitler.application.login;

import de.reitler.domain.HouseholdRepository;
import de.reitler.domain.Roommate;
import de.reitler.domain.RoommateRepository;

import java.io.IOException;
import java.util.Optional;

public class LoginHandler {
    private RoommateRepository roommateRepo;
    private HouseholdRepository householdRepo;
    private ValidationService validationService;

    UserDataInput input;

    public LoginHandler(RoommateRepository roommateRepo, ValidationService validationService, HouseholdRepository householdRepo){
        this.roommateRepo = roommateRepo;
        this.householdRepo = householdRepo;
        this.validationService = validationService;
    }

    /**
     * Validates the Token and creates a new Roommate if the token is valid
     * @param token
     * @return the UserData or null (if the Token isn't valid or something went wrong)
     */
    public UserDataInput signIn(String token){
        try {
            input = validationService.validateToken(token);
            System.out.println("Validate token output: "+input);
            if(input != null){
                roommateRepo.create(new Roommate(input.getFirstName(), input.getLastName(), input.getEmail(), input.getPicture()));
            }
        } catch (IOException| InterruptedException e) {
            input = null;
        }
        return input;
    }

}
