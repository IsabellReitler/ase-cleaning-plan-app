package de.reitler.application.login;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserInputMapper {
    public UserDataInput mapToUserDataInput(String input) throws JsonProcessingException;
}
