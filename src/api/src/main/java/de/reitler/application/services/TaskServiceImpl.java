package de.reitler.application.services;

import de.reitler.application.handlers.HolidayModeHandler;
import de.reitler.core.DateCalculator;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;
import de.reitler.domain.repositories.RoommateRepository;
import de.reitler.domain.repositories.TaskRepository;
import de.reitler.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 */

@Service
public class TaskServiceImpl implements TaskService{

    public TaskRepository repo;
    public RoommateRepository roommateRepository;

    @Autowired
    private static HolidayModeHandler holidayModeHandler;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, RoommateRepository roommateRepository){
        this.repo = taskRepository;
        this.roommateRepository = roommateRepository;
    }

    //@Override
    public Task create(Task task) {
        return repo.save(task);
    }

   // @Override
    public Task update(Task task) {
        Task t = repo.findById(task.getId()).get();
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setDeadline(task.getDeadline());
        t.setDoneAt(task.getDoneAt());
        t.setStartsAt(task.getStartsAt());
        t.setSwitchRoommate(task.isSwitchRoommate());
        t.setTimeIntervall(task.getTimeIntervall());
        t.setRoommate(task.getRoommate());
        return repo.save(t);
    }

    //@Override
    public void delete(Task task) {
        Roommate roommate = repo.findById(task.getId()).get().getRoommate();
        roommateRepository.findById(roommate.getId()).get().removeTask(task);
        repo.delete(task);
    }

    //Override
    public void delete(String id) {
        Roommate roommate = repo.findById(UUID.fromString(id)).get().getRoommate();
        roommateRepository.findById(roommate.getId()).get().removeTask(repo.findById(UUID.fromString(id)).get());
        repo.deleteById(UUID.fromString(id));
    }

    //@Override
    public Task getById(String id) {
        return repo.getOne(UUID.fromString(id));
    }

    public void sendRepetitiveTasksToNextRoommate(Roommate roommate){
        List<Task> tasks = roommate.getTasks();
        for (Task task: tasks) {
            sendTaskToNextRoommate(task);
        }
    }

    public void sendTaskToNextRoommate(Task task){
        if(task.isSwitchRoommate()) {
            List<Roommate> roommateList = task.getRoommate().getHousehold().getRoommates();
            //beginne bei dem aktuellen Roommate
            //der erste roommate, der nicht im ferienmodus ist erhält den Task
            //prüfe für jeden Roommate hinter dem aktuellen, ob er im Ferienmodus ist
            //wenn keiner der Roommates, die hinter dem aktuellen in der Liste sind in frage kommt beginne die Liste von vorne
            // wenn wieder beim aktuellen angekommen beende die prüfung und ändere nichts
            int index = roommateList.indexOf(task.getRoommate());
            if(index+1<roommateList.size()){
                for(int i=index+1; i<roommateList.size(); i++){
                    if (roommateList.get(i).getHolidayMode() == null || holidayModeHandler.isHolidayModeExpired(roommateList.get(i).getHolidayMode())) {
                        task.setRoommate(roommateList.get(i));
                        task.setDoneAt(null);
                        update(task);
                        return;
                    }
                }
            }
            for(int i=0; i<index; i++){
                if (roommateList.get(i).getHolidayMode() == null || holidayModeHandler.isHolidayModeExpired(roommateList.get(i).getHolidayMode())) {
                    task.setRoommate(roommateList.get(i));
                    update(task);
                    return;
                }
            }

            for (Roommate r : roommateList) {
                //Logik verbessern
                //aktuellen Roommate aus der Liste herausnehmen
                if (r.getHolidayMode() == null || holidayModeHandler.isHolidayModeExpired(r.getHolidayMode())) {
                    task.setRoommate(r);
                    update(task);
                    return;
                }
            }

        }
    }

    protected boolean isTaskDoneYesterday(Task task){
        DateCalculator calculator = new DateCalculator();
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);
        Calendar yesterday = Calendar.getInstance();
        yesterday.set(Calendar.HOUR, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);
        Date startOfDay = calculator.add(yesterday.getTime(), -1);
        Date endOfDay = calculator.add(today.getTime(), -1);
        if(task.getDoneAt() != null && task.getDoneAt().before(endOfDay) && task.getDoneAt().after(startOfDay)){
            return true;
        }
        return false;
    }

    protected void setNewDeadline(Task task){
        DateCalculator calculator = new DateCalculator();
        if(isTaskDoneYesterday(task)){
            task.setDeadline(calculator.add(task.getDoneAt(), task.getTimeIntervall()));
        }
    }

    public List<Task> getAllTasksDoneYesterday(List<Task> tasks){
        return tasks.stream().filter(task -> isTaskDoneYesterday(task)).collect(Collectors.toList());
    }

    public void deleteSimpleTasks(List<Task> tasks){
        try {
            tasks.stream().filter(task -> task.getTimeIntervall()==0).forEach((t)->{delete(t);});
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleRepetitiveTasks(List<Task> tasks){
       tasks.stream().filter(task -> task.getTimeIntervall()!= 0).forEach((t)->{sendTaskToNextRoommate(t); setNewDeadline(t); t.setDoneAt(null);repo.save(t);});
    }
}
