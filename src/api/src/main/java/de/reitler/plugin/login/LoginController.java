package de.reitler.plugin.login;

import de.reitler.application.login.LoginHandler;
import de.reitler.application.login.UserDataInput;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.services.HouseholdService;
import de.reitler.domain.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
public class LoginController {
    Account account;
    @Autowired
    RoommateService roommate;

    @Autowired
    HouseholdService household;

    @PostMapping(value="/signin")
    public ResponseEntity SignIn(@RequestBody String userData) throws GeneralSecurityException, IOException, InterruptedException {

        LoginHandler handler = new LoginHandler(roommate, household, new JsonUserInputMapper());
        UserDataInput account = handler.signIn(userData);
        System.out.println(account.getDisplayName());
        if(account != null){
            return new ResponseEntity<UserDataInput>(account, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
