package de.reitler.application.login;


import java.io.IOException;

public interface ValidationService {
    public UserDataInput validateToken(String token) throws IOException, InterruptedException;
}
