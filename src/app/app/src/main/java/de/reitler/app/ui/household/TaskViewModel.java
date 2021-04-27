package de.reitler.app.ui.household;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import de.reitler.app.model.Task;
import de.reitler.app.repositories.HouseholdRepository;


public class TaskViewModel extends AndroidViewModel {

    private LiveData<List<Task>> allTasksFromHousehold;
    private HouseholdRepository householdRepository;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        householdRepository = HouseholdRepository.getInstance();
        allTasksFromHousehold = householdRepository.getAllTasksFromHousehold();
    }

    public void updateAllTasksFromHousehold(String householdId){
        householdRepository.getAllTasksFromHousehold(householdId);
    }

    public LiveData<List<Task>> getAllTasksFromHousehold() {
        return allTasksFromHousehold;
    }
}

