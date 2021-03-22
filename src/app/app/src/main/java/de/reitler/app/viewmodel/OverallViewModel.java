package de.reitler.app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import de.reitler.app.adapter.HttpAdapter;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;

public class OverallViewModel extends ViewModel {
    HttpAdapter adapter;

    private MutableLiveData<Roommate> mutableLiveData;
    private static OverallViewModel myViewModel;
    private static String id;

    private OverallViewModel(String userId) {
        adapter = new HttpAdapter();
        if(mutableLiveData == null){
            mutableLiveData =new MutableLiveData<>();
        }
        RequestInformation(userId);
    }

    private void RequestInformation(String userId) {
        Roommate roommate = adapter.getUser(userId);
        List<Task> tasks = adapter.getAllTasksFromHousehold(roommate.getHousehold().getId());
        roommate.getHousehold().setTasks(tasks);
        mutableLiveData.setValue(roommate);
    }

    public static synchronized OverallViewModel getInstance() {
        if (myViewModel == null) {
            throw new AssertionError("You have to call init first");
        }

        return myViewModel;
    }

    public static synchronized OverallViewModel init(String userId) {
        if(id != null) {
            throw new AssertionError("You already initialized me");
        }
        myViewModel = new OverallViewModel(userId);
        return myViewModel;
    }

    public MutableLiveData<Roommate> getData(){
        return mutableLiveData;
    }

}
