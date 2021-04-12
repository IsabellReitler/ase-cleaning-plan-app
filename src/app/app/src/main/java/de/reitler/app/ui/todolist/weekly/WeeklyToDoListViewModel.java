package de.reitler.app.ui.todolist.weekly;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.reitler.app.model.Task;
import de.reitler.app.repositories.RoommateRepository;

public class WeeklyToDoListViewModel extends AndroidViewModel {

    private RoommateRepository repo;
    private LiveData<List<Task>> weeklyTasks;

    public WeeklyToDoListViewModel(@NonNull Application application) {
        super(application);
        repo = new RoommateRepository();
        weeklyTasks = repo.getWeeklyTasksMutableLiveData();
    }

    public void updateWeeklyTasks(String roommateId){
        repo.getWeeklyTasksFromRoommate(roommateId);
    }

    public LiveData<List<Task>> getWeeklyTasks(){
        return weeklyTasks;
    }

}
