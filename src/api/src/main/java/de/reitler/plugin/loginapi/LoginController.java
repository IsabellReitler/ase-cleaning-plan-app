package de.reitler.plugin.loginapi;

import de.reitler.application.HouseholdRepoIml;
import de.reitler.application.RoommateRepoImpl;
import de.reitler.application.login.LoginHandler;
import de.reitler.application.login.UserDataInput;
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

    @PostMapping(value="/signin")
    public ResponseEntity SignIn(@RequestBody String idTokenString) throws GeneralSecurityException, IOException, InterruptedException {

        LoginHandler handler = new LoginHandler(new RoommateRepoImpl(), new HouseholdRepoIml(), new JsonUserInputMapper());
        UserDataInput account = handler.signIn(idTokenString);
        System.out.println(account.getDisplayName());

        if(account != null){
            return new ResponseEntity<UserDataInput>(account, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
