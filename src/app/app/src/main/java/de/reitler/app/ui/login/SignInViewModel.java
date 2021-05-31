package de.reitler.app.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import de.reitler.app.model.Roommate;
import de.reitler.app.repositories.RoommateRepository;

public class SignInViewModel extends AndroidViewModel {

    RoommateRepository roommateRepository;
    LiveData<Roommate> roommate;

    public SignInViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        roommateRepository = RoommateRepository.getInstance();
        roommate = roommateRepository.getRoommateMutableLiveData();
    }

    public void createRoommate(String userId, String name, String email, String picture){
        roommateRepository.createRoommate(userId, name, email, picture);
    }

    public LiveData<Roommate> getRoommate(){
        return roommate;
    }
}
