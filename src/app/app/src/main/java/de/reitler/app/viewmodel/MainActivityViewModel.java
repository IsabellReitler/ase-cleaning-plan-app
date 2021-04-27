package de.reitler.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import de.reitler.app.model.Roommate;
import de.reitler.app.repositories.HouseholdRepository;
import de.reitler.app.repositories.RoommateRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private static MainActivityViewModel viewModel;

    LiveData<Roommate> roommate;

    RoommateRepository roommateRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        roommateRepository = RoommateRepository.getInstance();
        roommate = roommateRepository.getRoommateMutableLiveData();
    }



    public void updateRoommate(String roommate){
        roommateRepository.getRoommate(roommate);
    }


    public LiveData<Roommate> getRoommate() {
        return roommate;
    }

}
