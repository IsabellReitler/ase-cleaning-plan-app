package de.reitler.plugin.login;

import de.reitler.application.HouseholdService;
import de.reitler.application.RoommateService;
import de.reitler.application.login.LoginHandler;
import de.reitler.application.login.UserDataInput;
import de.reitler.domain.entities.Household;
import de.reitler.plugin.database.HouseholdEntity;
import de.reitler.plugin.database.RoommateDatabaseRepo;
import de.reitler.plugin.database.RoommateEntity;
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
    RoommateDatabaseRepo repo;

    @PostMapping(value="/signin")
    public ResponseEntity SignIn(@RequestBody String userData) throws GeneralSecurityException, IOException, InterruptedException {

        LoginHandler handler = new LoginHandler(new RoommateService(), new HouseholdService(), new JsonUserInputMapper());
        UserDataInput account = handler.signIn(userData);
        System.out.println(account.getDisplayName());
        repo.save(new RoommateEntity(account.getId(), account.getDisplayName(), account.getEmail(), new HouseholdEntity("1234asdf", "test"), account.getPicture()));

        if(account != null){
            return new ResponseEntity<UserDataInput>(account, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
