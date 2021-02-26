package de.reitler.plugin.loginapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;

import static java.net.http.HttpRequest.newBuilder;

@RestController
public class LoginController {

    ObjectMapper mapper = new ObjectMapper();
    Account account;

    @PostMapping(value="/signin")
    public ResponseEntity SignIn(@RequestBody String idTokenString) throws GeneralSecurityException, IOException, InterruptedException {

        IDToken idToken = mapper.readValue(idTokenString,IDToken.class);
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token="+idToken.getToken();

        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<Account> response = HttpClient.newHttpClient().send(request, new JsonBodyHandler<>(Account.class));
        account = response.body();

        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }
}
