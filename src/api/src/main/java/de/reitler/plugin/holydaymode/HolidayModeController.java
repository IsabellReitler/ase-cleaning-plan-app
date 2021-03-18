package de.reitler.plugin.holydaymode;

import de.reitler.application.exceptions.HolidayModeException;
import de.reitler.application.holidaymode.HolidayModeDTO;
import de.reitler.application.holidaymode.HolidayModeHandler;
import de.reitler.application.household.HouseholdHandler;
import de.reitler.application.roommate.RoommateDTO;
import de.reitler.application.roommate.RoommateHandler;
import de.reitler.plugin.roommate.RoommateController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/holidaymode")
public class HolidayModeController {

    @Autowired
    HolidayModeHandler holidayModeHandler;

    @Autowired
    RoommateHandler roommateHandler;

    @Autowired
    HouseholdHandler householdHandler;

    /**
     *
     * @param body: JSON (roommateId, enddate)
     * @return JSON- (roommateId, enddate)
     */
    @PostMapping
    public HttpEntity setHolidayMode(@RequestBody HolidayModeDTO body){
        try {
            RoommateDTO dto = holidayModeHandler.setHolidayMode(body.getRoommateId(), body.getEndDate());
            dto.add(linkTo(methodOn(RoommateController.class).getRoommate(dto.getId())).withSelfRel());
            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (HolidayModeException ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     *
     * @return List of all holidaymodes currently set, can be null
     */
    @GetMapping("/{id}")
    public HttpEntity getAllHolidayModes(@PathVariable(name="id") String householdId){
        holidayModeHandler.updateHolidayMode();
        List<RoommateDTO> roommates = householdHandler.getAllRoommates(householdId);
        List<HolidayModeDTO> dtos = roommates
                                        .stream()
                                        .filter(r -> r.getHolidayMode()!= null)
                                        .map(r -> new HolidayModeDTO(r.getId(),r.getHolidayMode()))
                                        .collect(Collectors.toList());
        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    /**
     *
     * @param roommateId Id of the Roommate
     * @return  the HolidayModeObject of the given Roommate, can be null
     */
    @GetMapping("/roommate/{id}")
    public HttpEntity getHolidayMode(@PathVariable(name="id") String roommateId){
            holidayModeHandler.updateHolidayMode();
        HolidayModeDTO dto = null;
        try {
            dto = new HolidayModeDTO(roommateId, roommateHandler.getById(roommateId).getHolidayMode() );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     *
     * @param roommateId
     * @return
     */
    @DeleteMapping("/{id}")
    public HttpEntity removeHolidayMode(@PathVariable(name="id") String roommateId){
        try {
            holidayModeHandler.setHolidayMode(roommateId, null);
        } catch (HolidayModeException ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
