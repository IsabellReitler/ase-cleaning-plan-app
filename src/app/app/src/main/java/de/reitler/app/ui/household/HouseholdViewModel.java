package de.reitler.app.ui.household;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;

public class HouseholdViewModel extends ViewModel {

    private MutableLiveData<String> mHousehold = new MutableLiveData<>();
    private MutableLiveData<List<Roommate>> mRoommates= new MutableLiveData<>();
    private MutableLiveData<List<Task>> mTasks= new MutableLiveData<>();


    public MutableLiveData<String> getmHousehold() {

        return mHousehold;
    }
}