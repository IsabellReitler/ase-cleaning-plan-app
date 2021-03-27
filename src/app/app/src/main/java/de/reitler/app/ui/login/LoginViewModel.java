package de.reitler.app.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import de.reitler.app.model.Household;
import de.reitler.app.repositories.HouseholdRepository;

public class LoginViewModel extends AndroidViewModel {

    private HouseholdRepository householdRepository;

    private LiveData<Household> household;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        householdRepository = new HouseholdRepository();
        household = householdRepository.getHouseholdMutableLiveData();
    }

    public void findHouseholdFromRoommate(String roommateId){
        householdRepository.getHouseholdFromRoommate(roommateId);
    }

    public LiveData<Household> getHousehold(){
        return household;
    }
}
