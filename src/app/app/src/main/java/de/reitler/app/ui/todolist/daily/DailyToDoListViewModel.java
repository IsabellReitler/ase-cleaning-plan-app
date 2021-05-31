package de.reitler.app.ui.todolist.daily;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.reitler.app.model.Task;
import de.reitler.app.repositories.RoommateRepository;

public class DailyToDoListViewModel extends AndroidViewModel {

    private RoommateRepository repo;
    private LiveData<List<Task>> dailyTasks;

    public DailyToDoListViewModel(@NonNull Application application) {
        super(application);
        repo = RoommateRepository.getInstance();
        dailyTasks = repo.getDailyTasksMutableLiveData();
    }

    public void updateDailyTasks(String roommateId){
        repo.getDailyTasksFromRoommate(roommateId);
    }

    public LiveData<List<Task>> getDailyTasks(){
        return dailyTasks;
    }

}
