package de.reitler.application.roommate;


import de.reitler.application.tasks.TaskDTO;
import de.reitler.core.DateCalculator;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;
import de.reitler.domain.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
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
        Calendar startOfDay = Calendar.getInstance();
        startOfDay.set(Calendar.HOUR_OF_DAY, 0);
        startOfDay.set(Calendar.MINUTE, 0);
        startOfDay.set(Calendar.SECOND, 0);
        startOfDay.set(Calendar.MILLISECOND, 0);
        endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        endOfDay.set(Calendar.MINUTE, 59);
        endOfDay.set(Calendar.SECOND, 59);
        endOfDay.set(Calendar.MILLISECOND, 999);
        return dailyTasks
                .stream()
                .filter(x -> x.getDeadline().compareTo(endOfDay.getTime()) < 0)
                .filter(x->x.getDeadline().compareTo(startOfDay.getTime())>0)
                //    public TaskDTO(String id, String title, String description, Calendar startsAt, Calendar deadline, Calendar doneAt, Calendar timeInterval, boolean switchRoommate, String roommate) {
                .map(x -> new TaskDTO(x.getId().toString(), x.getTitle(), x.getDescription(), x.getStartsAt(), x.getDeadline(),x.getDoneAt(), x.getTimeIntervall(), x.isSwitchRoommate(), x.getRoommate().getId()))
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getAllWeeklyTasks(String id) {
        List<Task> weeklyTasks = roommateService.getAllTasks(id);
        Calendar startOfToday = Calendar.getInstance();
        Calendar endOfWeek = Calendar.getInstance();
        Calendar startOfDay = Calendar.getInstance();
        startOfDay.set(Calendar.HOUR_OF_DAY, 0);
        startOfDay.set(Calendar.MINUTE, 0);
        startOfDay.set(Calendar.SECOND, 0);
        startOfDay.set(Calendar.MILLISECOND, 0);
        DateCalculator calculator = new DateCalculator();
        endOfWeek.setTime(calculator.add(startOfToday.getTime(), 7));
        endOfWeek.set(Calendar.HOUR_OF_DAY, 0);
        endOfWeek.set(Calendar.MINUTE, 0);
        endOfWeek.set(Calendar.SECOND, 0);
        endOfWeek.set(Calendar.MILLISECOND, 0);
        return weeklyTasks
                .stream()
                .filter(x -> x.getDeadline().compareTo(endOfWeek.getTime()) < 0)
                .filter(x->x.getDeadline().compareTo(startOfDay.getTime())>0)
                .map(x -> new TaskDTO(x.getId().toString(), x.getTitle(), x.getDescription(), x.getStartsAt(), x.getDeadline(),x.getDoneAt(), x.getTimeIntervall(), x.isSwitchRoommate(), x.getRoommate().getId()))
                .collect(Collectors.toList());
    }
}
