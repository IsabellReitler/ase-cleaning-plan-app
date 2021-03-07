package de.reitler.plugin.household;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.reitler.application.household.HouseholdDTO;
import de.reitler.application.household.HouseholdHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
public class HouseholdController {

    ResponseEntity response;

    @PostMapping
    public HttpEntity createHousehold(@RequestBody String name){
        HouseholdHandler handler = new HouseholdHandler(new HouseholdDTOMapper());
        try {
            HouseholdDTO dto = handler.createHousehold(name);
            response = new ResponseEntity<HouseholdDTO>(dto, HttpStatus.OK);
            return response;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
