package de.reitler.application.handlers;


import de.reitler.application.dtos.RoommateDTO;
import de.reitler.application.exceptions.HolidayModeException;
import de.reitler.core.DateCalculator;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;
import de.reitler.domain.services.HouseholdService;
import de.reitler.domain.services.RoommateService;
import de.reitler.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HolidayModeHandler {

    @Autowired
    RoommateService roommateService;

    @Autowired
    HouseholdService householdService;

    @Autowired
    private static TaskService taskService;

    public RoommateDTO setHolidayMode(String id, Calendar endDate) throws HolidayModeException, URISyntaxException {
        if(endDate != null && isHolidayModeExpired(endDate)){
          throw new HolidayModeException("The date can't be in the past");
        }
        Roommate roommate = roommateService.getById(id);
        roommate.setHolidayMode(endDate);
        roommateService.update(roommate);
        return new RoommateDTO(roommate.getId(), roommate.getDisplayname(), roommate.getEmail(), new URI(roommate.getPicture()), roommate.getHolidayMode());

    }

    public void updateHolidayMode(){
        householdService.getAllHouseholds()
                .forEach(household -> household.getRoommates()
                        .forEach(roommate ->{ if(roommate.getHolidayMode()!= null && isHolidayModeExpired(roommate.getHolidayMode())){
                            try {
                                setHolidayMode(roommate.getId(), null);
                            } catch (HolidayModeException | URISyntaxException e) {
                                e.getStackTrace();
                            }
                        }}));
    }

    public static boolean isHolidayModeExpired(Calendar endDate){
        if(endDate.compareTo(Calendar.getInstance())<0){
            return true;
        } else {
            return false;
        }
    }
}
