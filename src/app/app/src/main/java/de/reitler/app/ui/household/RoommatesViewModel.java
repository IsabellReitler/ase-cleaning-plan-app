package de.reitler.app.ui.household;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.reitler.app.model.Roommate;
import de.reitler.app.repositories.HouseholdRepository;

public class RoommatesViewModel extends AndroidViewModel {

    private HouseholdRepository repo;
    private LiveData<List<Roommate>> roommates;

    public RoommatesViewModel(@NonNull Application application) {
        super(application);
        repo = HouseholdRepository.getInstance();
        roommates = repo.getRoommatesMutableLiveData();
    }

    public void updateRoommates(String householdId){
        repo.getRoommates(householdId);
    }

    public LiveData<List<Roommate>> getRoommates(){
        return roommates;
    }
}
