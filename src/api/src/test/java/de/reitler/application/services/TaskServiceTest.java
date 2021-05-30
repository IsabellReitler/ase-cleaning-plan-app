package de.reitler.application.services;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import de.reitler.core.DateCalculator;
import de.reitler.domain.entities.Household;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;

import de.reitler.domain.repositories.HouseholdRepository;
import de.reitler.domain.repositories.RoommateRepository;
import de.reitler.domain.repositories.TaskRepository;
import de.reitler.plugin.ApiApplication;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
public class TaskServiceTest {

    Household household;
    Roommate roommate1;
    Roommate roommate2;
    static Task task1;
    static Task task2;
    static Task task3;

    List<Task> tasks;

    DateCalculator calculator = new DateCalculator();

    @InjectMocks
    TaskServiceImpl service;

    @Mock
    HouseholdRepository householdRepository;
    @Mock
    TaskRepository taskRepository;
    @Mock
    RoommateRepository roommateRepository;

    @BeforeEach
    void before(){
        MockitoAnnotations.initMocks(this);

        household = new Household("Household");
        household.setId("asdf");
        roommate1 = new Roommate("abcde", "roommate 1", "roommate@roommate.com", "roommate.png" );

        roommate2 = new Roommate ("fghijk", "roommate2", "roommate2@xyz.de", "roommate2.png");

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

        roommateRepository.save(roommate1);
        roommateRepository.save(roommate2);
        householdRepository.save(household);
        roommate1.setHousehold(household);
        roommate2.setHousehold(household);
        roommateRepository.save(roommate1);
        roommateRepository.save(roommate2);
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        service = new TaskServiceImpl(taskRepository,roommateRepository );
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

}
