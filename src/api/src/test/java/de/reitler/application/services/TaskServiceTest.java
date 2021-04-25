package de.reitler.application.services;

import de.reitler.core.DateCalculator;
import de.reitler.domain.entities.Household;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskServiceTest {

    Household household;
    Roommate roommate1;
    Roommate roommate2;
    Task task;

    DateCalculator calculator = new DateCalculator();

    TaskServiceImpl service = new TaskServiceImpl();

    @BeforeEach
    void before(){
        household = new Household("Household");
        household.setId("asdf");
        roommate1 = new Roommate("abcde", "roommate 1", "roommate@roommate.com", "roommate.png" );
        roommate1.setHousehold(household);
        roommate2 = new Roommate ("fghijk", "roommate2", "roommate2@xyz.de", "roommate2.png");
        roommate2.setHousehold(household);
    }

    @Test
    void isTaskDoneYesterdayTest1(){
        task = new Task("title", "description", 3, true, roommate1);
        task.setDoneAt(calculator.add(Calendar.getInstance().getTime(), -1));
        assertTrue(service.isTaskDoneYesterday(task));
    }

    @Test
    public void setNewDeadlineTest1(){
        task = new Task("title", "description", 3, true, roommate1);
        Calendar deadline =Calendar.getInstance();
        deadline.set(Calendar.YEAR, 2020);
        deadline.set(Calendar.MONTH, 3);
        deadline.set(Calendar.DATE, 29);
        System.out.println(deadline.getTime());
        task.setDoneAt(deadline.getTime());
        service.setNewDeadline(task);
        assertEquals(calculator.add(deadline.getTime(), 3), task.getDeadline());
    }

    //@Test
    public void getAllTasksDoneYesterdayTest1(){

    }

   // @Test
    public void handleRepetitiveTasksTest1(){

    }

   // @Test
    public void sendTaskToNextRoommateTest1(){

    }
}
