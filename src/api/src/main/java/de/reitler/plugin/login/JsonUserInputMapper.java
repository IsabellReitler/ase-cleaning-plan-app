package de.reitler.plugin.login;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.reitler.application.login.UserDataInput;
import de.reitler.application.login.UserInputMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUserInputMapper implements UserInputMapper {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserDataInput mapToUserDataInput(String input) throws JsonProcessingException {

        UserDataInput user = mapper.readValue(input, Account.class);
        return user;
    }
}
