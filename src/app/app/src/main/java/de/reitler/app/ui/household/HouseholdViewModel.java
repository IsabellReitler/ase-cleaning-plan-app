package de.reitler.app.ui.household;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import de.reitler.app.model.Task;
import de.reitler.app.repositories.HouseholdRepository;


public class HouseholdViewModel extends AndroidViewModel {

    private LiveData<List<Task>> allTasksFromHousehold;
    private HouseholdRepository householdRepository;

    public HouseholdViewModel(@NonNull Application application) {
        super(application);
        householdRepository = new HouseholdRepository();
        allTasksFromHousehold = householdRepository.getAllTasksFromHousehold();
    }

    public void getAllTasksFromHouseholdLiveData(String householdId){
        householdRepository.getAllTasksFromHousehold(householdId);
        System.out.println("All Tasks From Household: "+allTasksFromHousehold.getValue());
    }

    public LiveData<List<Task>> getAllTasksFromHousehold() {
        return allTasksFromHousehold;
    }
}
