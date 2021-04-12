package de.reitler.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import de.reitler.app.repositories.HouseholdRepository;
import de.reitler.app.repositories.RoommateRepository;
import de.reitler.app.repositories.TaskRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private static MainActivityViewModel viewModel;

    LiveData<Roommate> user;
    LiveData<Household> household;
    LiveData<List<Task>> dailyTask;
    LiveData<List<Task>> weeklyTask;
    LiveData<List<Roommate>> roommates;

    RoommateRepository roommateRepository;
    HouseholdRepository householdRepository;
    TaskRepository taskRepository;

    private MainActivityViewModel(@NonNull Application application) {
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
        roommates = householdRepository.getRoommatesMutableLiveData();
    }

    public static MainActivityViewModel getInstance(Application application){
        if(viewModel == null){
            viewModel = new MainActivityViewModel(application);
        }
        return viewModel;
    }

    public void getRoommateInfo(String roommate){
        roommateRepository.getRoommate(roommate);
    }

    public synchronized void getHouseholdInfo(String household){
        householdRepository.getHouseholdById(household);
    }

    public void getDailyTasksInfo(String roommateId){
        roommateRepository.getDailyTasksFromRoommate(roommateId);
    }

    public void getWeeklyTaskInfo(String roommateId){
        roommateRepository.getWeeklyTasksFromRoommate(roommateId);
    }

    public synchronized void getRoommatesInfo(){
        householdRepository.getRoommates(getHousehold().getValue().getId());
        System.out.println("Roommates: "+roommates.getValue());
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

    public LiveData<List<Roommate>> getRoommates() {
        return roommates;
    }

}
