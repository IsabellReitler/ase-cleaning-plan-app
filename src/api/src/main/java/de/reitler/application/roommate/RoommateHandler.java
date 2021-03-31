package de.reitler.application.roommate;


import de.reitler.application.tasks.TaskDTO;
import de.reitler.core.DateCalculator;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;
import de.reitler.domain.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoommateHandler {

    @Autowired
    private RoommateService roommateService;

    private RoommateDTO input;

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
        List<Task> dailyTasks = roommateService.getAllTasks(id);
        Calendar endOfDay = Calendar.getInstance();
        endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        endOfDay.set(Calendar.MINUTE, 59);
        endOfDay.set(Calendar.SECOND, 59);
        endOfDay.set(Calendar.MILLISECOND, 999);
        return dailyTasks
                .stream()
                .filter(x -> x.getDeadline().compareTo(endOfDay) < 0)
                .map(x -> new TaskDTO(x.getId().toString(), x.getTitle(), x.getDescription(), x.getStartsAt(), x.getDeadline(), x.getTimeIntervall(), x.isSwitchRoommate(), x.getRoommate().getId()))
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getAllWeeklyTasks(String id) {
        List<Task> weeklyTasks = roommateService.getAllTasks(id);
        Calendar startOfToday = Calendar.getInstance();
        Calendar timeIntervall = Calendar.getInstance();
        timeIntervall.set(Calendar.YEAR, 0);
        timeIntervall.set(Calendar.MONTH, -1);
        timeIntervall.set(Calendar.DAY_OF_MONTH, 6);
        timeIntervall.set(Calendar.HOUR_OF_DAY, 0);
        timeIntervall.set(Calendar.MINUTE, 0);
        timeIntervall.set(Calendar.SECOND, 0);
        timeIntervall.set(Calendar.MILLISECOND, 0);
        DateCalculator calculator = new DateCalculator();
        Calendar endOfWeek = calculator.add(startOfToday, startOfToday);
        endOfWeek.set(Calendar.HOUR_OF_DAY, 0);
        endOfWeek.set(Calendar.MINUTE, 0);
        endOfWeek.set(Calendar.SECOND, 0);
        endOfWeek.set(Calendar.MILLISECOND, 0);
        return weeklyTasks
                .stream()
                .filter(x -> x.getDeadline().compareTo(endOfWeek) < 0)
                .map(x -> new TaskDTO(x.getId().toString(), x.getTitle(), x.getDescription(), x.getStartsAt(), x.getDeadline(), x.getTimeIntervall(), x.isSwitchRoommate(), x.getRoommate().getId()))
                .collect(Collectors.toList());
    }
}
