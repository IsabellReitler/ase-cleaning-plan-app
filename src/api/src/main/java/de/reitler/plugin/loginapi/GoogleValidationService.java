package de.reitler.plugin.loginapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.reitler.application.login.UserDataInput;
import de.reitler.application.login.ValidationService;
import de.reitler.domain.Roommate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;

public class GoogleValidationService implements ValidationService {
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public UserDataInput validateToken(String token) throws IOException, InterruptedException {
        IDToken idToken = mapper.readValue(token,IDToken.class);
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token="+idToken.getToken();

        HttpRequest request = newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<Account> response = HttpClient.newHttpClient().send(request, new JsonBodyHandler<>(Account.class));
        Account account = response.body();

        return new Account();
    }
}
