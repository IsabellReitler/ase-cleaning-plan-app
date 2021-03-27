package de.reitler.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import de.reitler.app.repositories.HouseholdRepository;
import de.reitler.app.repositories.RoommateRepository;
import de.reitler.app.repositories.TaskRepository;

public class MainActivityViewModel extends AndroidViewModel {

    LiveData<Roommate> user;
    LiveData<Household> household;
    LiveData<List<Task>> dailyTask;
    LiveData<List<Task>> weeklyTask;

    RoommateRepository roommateRepository;
    HouseholdRepository householdRepository;
    TaskRepository taskRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        roommateRepository = new RoommateRepository();
        householdRepository = new HouseholdRepository();
        taskRepository = new TaskRepository();
        user = roommateRepository.getRoommateMutableLiveData();
        household = householdRepository.getHouseholdMutableLiveData();
        dailyTask = roommateRepository.getDailyTasksMutableLiveData();
        weeklyTask = roommateRepository.getWeeklyTasksMutableLiveData();
    }

    public void getRoommateInfo(String roommate){
        roommateRepository.getRoommate(roommate);
    }

    public void getHouseholdInfo(String household){
        householdRepository.getHouseholdById(household);
    }

    public void getDailyTasksInfo(String roommateId){
        roommateRepository.getTasksFromRoommate(roommateId);
    }

    public void getWeeklyTaskInfo(String roommateId){
        roommateRepository.getWeeklyTasksFromRoommate(roommateId);
    }

    public LiveData<Roommate> getUser() {
        return user;
    }

    public LiveData<Household> getHousehold() {
        return household;
    }

    public LiveData<List<Task>> getDailyTask() {
        return dailyTask;
    }

    public LiveData<List<Task>> getWeeklyTask() {
        return weeklyTask;
    }
}
