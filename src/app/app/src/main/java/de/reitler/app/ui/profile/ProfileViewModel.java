package de.reitler.app.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import de.reitler.app.model.Roommate;
import de.reitler.app.repositories.RoommateRepository;

public class ProfileViewModel extends AndroidViewModel {

    private LiveData<Roommate> roommate;
    private RoommateRepository roommateRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        roommateRepository = RoommateRepository.getInstance();
        roommate = roommateRepository.getRoommateMutableLiveData();
    }

    public void updateRoommate(String roommateId){
        roommateRepository.getRoommate(roommateId);
    }

    public LiveData<Roommate> getRoommate() {
        return roommate;
    }
}