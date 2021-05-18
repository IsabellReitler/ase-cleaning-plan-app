package de.reitler.application.handlers;

import de.reitler.application.dtos.TaskDTO;
import de.reitler.application.services.RoommateServiceImpl;
import de.reitler.application.services.TaskServiceImpl;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;
import de.reitler.domain.repositories.RoommateRepository;
import de.reitler.domain.repositories.TaskRepository;
import de.reitler.domain.services.RoommateService;
import de.reitler.domain.services.TaskService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RoommateHandlerTest {

    RoommateHandler handler = new RoommateHandler();

    @Mock
    TaskRepository taskRepository;

    @Mock
    RoommateRepository roommateRepository;


    TaskService taskService = new TaskServiceImpl(taskRepository,roommateRepository);
    //@InjectMocks
    RoommateService roommateService =new RoommateServiceImpl(roommateRepository, taskService);
    @Mock
    static Task task;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void getDailyTasksTest(){
        task.setId(UUID.randomUUID());
        taskService.create(task);
        List<TaskDTO> dailyTasks = new ArrayList<>();
        TaskDTO dto = new TaskDTO(task.getId().toString(),task.getTitle(), task.getDescription(), task.getStartsAt(), task.getDeadline(), task.getDoneAt(),task.getTimeIntervall(), task.isSwitchRoommate(), task.getRoommate().getId());
        dailyTasks.add(dto);
        assertEquals(dailyTasks, handler.getAllDailyTasks(task.getRoommate().getId()));
    }
}
