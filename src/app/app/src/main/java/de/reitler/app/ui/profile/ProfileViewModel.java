package de.reitler.app.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.repositories.HouseholdRepository;
import de.reitler.app.repositories.RoommateRepository;

public class ProfileViewModel extends AndroidViewModel {

    private LiveData<Roommate> roommate;
    private LiveData<Household> household;

    private HouseholdRepository householdRepository;
    private RoommateRepository roommateRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        roommateRepository = RoommateRepository.getInstance();
        householdRepository = HouseholdRepository.getInstance();
        roommate = roommateRepository.getRoommateMutableLiveData();
        household =householdRepository.getHouseholdMutableLiveData();
    }

    public void updateRoommate(String roommateId){
        roommateRepository.getRoommate(roommateId);
    }

    public LiveData<Roommate> getRoommate() {
        return roommate;
    }

    public void updateHousehold(String householdId){
        householdRepository.getHouseholdById(householdId);
    }

    public LiveData<Household> getHousehold() {
        return household;
    }
}