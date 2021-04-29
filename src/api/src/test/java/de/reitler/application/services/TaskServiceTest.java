package de.reitler.application.services;

import de.reitler.core.DateCalculator;
import de.reitler.domain.entities.Household;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;

import de.reitler.domain.repositories.TaskRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    Household household;
    Roommate roommate1;
    Roommate roommate2;
    Task task1;
    Task task2;
    Task task3;

    List<Task> tasks;

    DateCalculator calculator = new DateCalculator();
    TaskServiceImpl service = new TaskServiceImpl();
    TaskRepository taskRepository;
    @Before
    public void init() {
        taskRepository = Mockito.mock(TaskRepository.class);
        service.repo = taskRepository;
        MockitoAnnotations.initMocks(this);
        when(service.create(task1)).thenReturn(task1);
        when(service.create(task2)).thenReturn(task2);
        when(service.create(task3)).thenReturn(task3);
        when(service.update(task1)).thenReturn(task1);
        when(service.update(task2)).thenReturn(task2);
        when(service.update(task3)).thenReturn(task3);
        when(taskRepository.findById(task1.getId())).thenReturn(Optional.of(task1));
        when(taskRepository.findById(task2.getId())).thenReturn(Optional.of(task2));
        when(taskRepository.findById(task3.getId())).thenReturn(Optional.of(task3));
    }

    @BeforeEach
    void before(){
        household = new Household("Household");
        household.setId("asdf");
        roommate1 = new Roommate("abcde", "roommate 1", "roommate@roommate.com", "roommate.png" );
        roommate1.setHousehold(household);
        roommate2 = new Roommate ("fghijk", "roommate2", "roommate2@xyz.de", "roommate2.png");
        roommate2.setHousehold(household);
        List<Roommate> roommates = new ArrayList<>();
        roommates.add(roommate1);
        roommates.add(roommate2);
        household.setRoommates(roommates);
        task1 = new Task("title", "description", 3, true, roommate1);
        task2 = new Task("title", "description", 0, false, roommate1);
        task3 = new Task("title", "description", Calendar.getInstance().getTime(),  roommate1);
        tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    @Test
    void isTaskDoneYesterdayTest1(){
        task1.setDoneAt(calculator.add(Calendar.getInstance().getTime(), -1));
        assertTrue(service.isTaskDoneYesterday(task1));
    }

    @Test
    void isTaskDoneYesterdayTest2(){
        task1.setDoneAt(Calendar.getInstance().getTime());
        assertFalse(service.isTaskDoneYesterday(task1));
    }

    @Test
    void isTaskDoneYesterdayTest3(){
        task1.setDoneAt(calculator.add(Calendar.getInstance().getTime(), -2));
        assertFalse(service.isTaskDoneYesterday(task1));
    }


    @Test
    public void setNewDeadlineTest1(){
        task1.setDoneAt(calculator.add(Calendar.getInstance().getTime(), -1));
        service.setNewDeadline(task1);
        assertEquals(calculator.add(Calendar.getInstance().getTime(), 2),task1.getDeadline());
    }

    @Test
    public void getAllTasksDoneYesterdayTest1(){
        task1.setDoneAt(calculator.add(Calendar.getInstance().getTime(), -1));
        task2.setDeadline(calculator.add(Calendar.getInstance().getTime(), -2));
        List<Task> list = new ArrayList<Task>();
        list.add(task1);
        assertEquals(list, service.getAllTasksDoneYesterday(tasks));
    }

    //@Test
    public void handleRepetitiveTasksTest1(){
        service.handleRepetitiveTasks(tasks);
        Task t = taskRepository.findById(task1.getId()).get();
        assertEquals(roommate2, t.getRoommate());
        service.delete(task1);
        service.delete(task2);
        service.delete(task3);
    }

    @Test
    public void deleteSimpleTaskTest1(){

    }

    @Test
    public void sendTaskToNextRoommateTest1(){

    }
}
