package de.reitler.plugin.holydaymode;

import de.reitler.application.holidaymode.HolidayModeDTO;
import de.reitler.application.household.HouseholdHandler;
import de.reitler.application.roommate.RoommateDTO;
import de.reitler.application.roommate.RoommateHandler;
import de.reitler.domain.entities.Roommate;
import de.reitler.plugin.roommate.RoommateController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/holidaymode")
public class HolidayModeController {

    @Autowired
    RoommateHandler handler;

    @Autowired
    HouseholdHandler householdHandler;

    /**
     *
     * @param body: JSON (roommateId, enddate)
     * @return JSON- (roommateId, enddate)
     */
    @PostMapping
    public HttpEntity setHolidayMode(@RequestBody HolidayModeDTO body){
        RoommateDTO dto = handler.setHolidayMode(body.getRoommateId(), body.getEndDate());
        dto.add(linkTo(methodOn(RoommateController.class).getRoommate(dto.getId())).withSelfRel());
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     *
     * @return List of all holidaymodes currently set, can be null
     */
    @GetMapping("/{id}")
    public HttpEntity getAllHolidayModes(@PathVariable(name="id") String householdId){
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
     * @return  the HolydayModeObject of the given Roommate, can be null
     */
    @GetMapping("/roommate/{id}")
    public HttpEntity getHolidayMode(@PathVariable(name="id") String roommateId){
        HolidayModeDTO dto = new HolidayModeDTO(roommateId,handler.getById(roommateId).getHolidayMode() );
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     *
     * @param roommateId
     * @return
     */
    @DeleteMapping("/{id}")
    public HttpEntity removeHolidayMode(@PathVariable(name="id") String roommateId){
        handler.setHolidayMode(roommateId, null);
        return new ResponseEntity(HttpStatus.OK);
    }
}
