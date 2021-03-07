package de.reitler.plugin.login;

import de.reitler.application.login.LoginHandler;
import de.reitler.application.login.UserDataInput;
import de.reitler.application.login.UserInputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
@RequestMapping("/signin")
public class LoginController {

   // @Autowired
   // RoommateRepository repository;
    @Autowired
    UserInputMapper mapper;
    @Autowired
    LoginHandler handler;

    @PostMapping
    public ResponseEntity SignIn(@RequestBody String userData) throws GeneralSecurityException, IOException, InterruptedException {

        System.out.println("Handler-Objekt: "+handler.toString());
        UserDataInput account = handler.signIn(userData);
        System.out.println(account.getDisplayName());
        if(account != null){
            return new ResponseEntity<UserDataInput>(account, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
