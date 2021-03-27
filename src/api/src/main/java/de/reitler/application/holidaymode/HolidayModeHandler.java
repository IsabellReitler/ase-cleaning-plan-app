package de.reitler.application.holidaymode;


import de.reitler.application.exceptions.HolidayModeException;
import de.reitler.application.household.HouseholdDTO;
import de.reitler.application.roommate.RoommateDTO;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.services.HouseholdService;
import de.reitler.domain.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

@Component
public class HolidayModeHandler {

    @Autowired
    RoommateService roommateService;

    @Autowired
    HouseholdService householdService;

    public RoommateDTO setHolidayMode(String id, Calendar endDate) throws HolidayModeException, URISyntaxException {
        if(isHolidayModeExpired(endDate)){
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

    private boolean isHolidayModeExpired(Calendar endDate){
        if(endDate.compareTo(Calendar.getInstance())<0){
            return true;
        } else {
            return false;
        }
    }
}
