package de.reitler.app.ui.household;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import de.reitler.app.model.Household;
import de.reitler.app.repositories.HouseholdRepository;

public class HouseholdFragmentViewModel extends AndroidViewModel {

    private HouseholdRepository householdRepository;
    private LiveData<Household> household;

    public HouseholdFragmentViewModel(@NonNull Application application) {
        super(application);
        householdRepository = HouseholdRepository.getInstance();
        household = householdRepository.getHouseholdMutableLiveData();
    }

    public void updateHousehold(String householdId){
        householdRepository.getHouseholdById(householdId);
    }

    public LiveData<Household> getHousehold(){
        return household;
    }
}
