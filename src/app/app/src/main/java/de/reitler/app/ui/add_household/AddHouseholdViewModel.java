package de.reitler.app.ui.add_household;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.internal.$Gson$Preconditions;

import java.util.List;

import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.repositories.HouseholdRepository;

public class AddHouseholdViewModel extends AndroidViewModel {

    LiveData<Household> householdLiveData;
    LiveData<List<Roommate>> roommates;

    HouseholdRepository householdRepository;

    public AddHouseholdViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        householdRepository = HouseholdRepository.getInstance();
        householdLiveData = householdRepository.getHouseholdMutableLiveData();
        roommates = householdRepository.getRoommatesMutableLiveData();
    }

    public void createHousehold(String name){
        householdRepository.createHousehold(name);
    }

    public void addRoommate(String household, String roommate){
        householdRepository.addRoommateToHousehold(household, roommate);
    }

    public void getHouseholdFromRoommate(String roommate){
        householdRepository.getHouseholdFromRoommate(roommate);
    }

    public LiveData<Household> getHouseholdLiveData(){
        return householdLiveData;
    }

    public LiveData<List<Roommate>> getRoommatesLiveData(){
        return roommates;
    }
}
