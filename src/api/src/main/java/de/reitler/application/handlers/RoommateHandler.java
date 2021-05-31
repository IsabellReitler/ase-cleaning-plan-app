package de.reitler.application.handlers;


import de.reitler.application.dtos.RoommateDTO;
import de.reitler.application.dtos.TaskDTO;
import de.reitler.core.DateCalculator;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;
import de.reitler.domain.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RoommateHandler {

    @Autowired
    private RoommateService roommateService;

    private RoommateDTO input;

    private final int WEEK_DAYS_COUNT = 7;

    private final int FIRST_HOUR_OF_DAY = 0;
    private final int FIRST_MINUTE_OF_DAY = 0;
    private final int FIRST_SECOND_OF_DAY = 0;
    private final int FIRST_MILLISECOND_OF_DAY = 0;

    private final int LAST_HOUR_OF_DAY = 23;
    private final int LAST_MINUTE_OF_DAY = 59;
    private final int LAST_SECOND_OF_DAY = 59;
    private final int LAST_MILLISECOND_OF_DAY = 999;

    public RoommateHandler() {
    }

    /**
     * Validates the Token and creates a new Roommate if the token is valid
     *
     * @param userData
     * @return the UserData or null (if the Token isn't valid or something went wrong)
     */
    public RoommateDTO create(RoommateDTO userData) {
        input = userData;
        if (input != null) {
            roommateService.create(new Roommate(input.getId(), input.getDisplayName(), input.getEmail(), input.getPicture()));
        }
        return input;
    }

    public RoommateDTO update(RoommateDTO newRoommate) throws URISyntaxException {
        Roommate result = roommateService.update(roommateService.getById(newRoommate.getId()));
        return new RoommateDTO(result.getId(), result.getDisplayname(), result.getEmail(), new URI(result.getPicture()));
    }

    public RoommateDTO getById(String id) throws  URISyntaxException {
        Roommate roommate = roommateService.getById(id);
        return new RoommateDTO(roommate.getId(), roommate.getDisplayname(), roommate.getEmail(), new URI(roommate.getPicture()));
    }

    public List<TaskDTO> getAllTasks(String id) {
        List<Task> tasks = roommateService.getAllTasks(id);
        return tasks
                .stream()
                .map(x -> new TaskDTO(x.getId().toString(), x.getTitle(), x.getDescription(), x.getStartsAt(), x.getDeadline(),x.getDoneAt(), x.getTimeIntervall(), x.isSwitchRoommate(), x.getRoommate().getId()))
                .collect(Collectors.toList());

    }

    public List<TaskDTO> getAllDailyTasks(String id) {
        if(roommateService.getById(id).getHolidayMode()==null||!HolidayModeHandler.isHolidayModeExpired(roommateService.getById(id).getHolidayMode())) {
            return new ArrayList<TaskDTO>();
        }
        List<Task> dailyTasks = roommateService.getAllTasks(id);
        return dailyTasks
                .stream()
                .filter(x -> x.getDeadline().compareTo(getEndOIfDay()) < 0)
                .filter(x -> x.getDeadline().compareTo(getStartOfDay())>0)
                .map(x -> new TaskDTO(x.getId().toString(), x.getTitle(), x.getDescription(), x.getStartsAt(), x.getDeadline(),x.getDoneAt(), x.getTimeIntervall(), x.isSwitchRoommate(), x.getRoommate().getId()))
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getAllWeeklyTasks(String id) {
        if(roommateService.getById(id).getHolidayMode()==null||!HolidayModeHandler.isHolidayModeExpired(roommateService.getById(id).getHolidayMode())) {
            return new ArrayList<TaskDTO>();
        }
        List<Task> weeklyTasks = roommateService.getAllTasks(id);

        DateCalculator calculator = new DateCalculator();
        Date endOfWeek =  calculator.add(getEndOIfDay(), WEEK_DAYS_COUNT);
        return weeklyTasks
                .stream()
                .filter(x -> x.getDeadline().compareTo(endOfWeek) < 0)
                .filter(x->x.getDeadline().compareTo(getStartOfDay())>0)
                .map(x -> new TaskDTO(x.getId().toString(), x.getTitle(), x.getDescription(), x.getStartsAt(), x.getDeadline(),x.getDoneAt(), x.getTimeIntervall(), x.isSwitchRoommate(), x.getRoommate().getId()))
                .collect(Collectors.toList());
    }

    private Date getEndOIfDay() {
        Calendar endOfWeek = Calendar.getInstance();
        endOfWeek.set(Calendar.HOUR_OF_DAY, LAST_HOUR_OF_DAY);
        endOfWeek.set(Calendar.MINUTE, LAST_MINUTE_OF_DAY);
        endOfWeek.set(Calendar.SECOND, LAST_SECOND_OF_DAY);
        endOfWeek.set(Calendar.MILLISECOND, LAST_MILLISECOND_OF_DAY);
        return endOfWeek.getTime();
    }

    private Date getStartOfDay() {
        Calendar startOfDay = Calendar.getInstance();
        startOfDay.set(Calendar.HOUR_OF_DAY, FIRST_HOUR_OF_DAY);
        startOfDay.set(Calendar.MINUTE, FIRST_MINUTE_OF_DAY);
        startOfDay.set(Calendar.SECOND, FIRST_SECOND_OF_DAY);
        startOfDay.set(Calendar.MILLISECOND, FIRST_MILLISECOND_OF_DAY);
        return startOfDay.getTime();
    }
}
