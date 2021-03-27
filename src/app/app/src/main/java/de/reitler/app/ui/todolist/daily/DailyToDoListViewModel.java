package de.reitler.app.ui.todolist.daily;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import de.reitler.app.model.Task;

public class DailyToDoListViewModel extends ViewModel {
    private MutableLiveData<List<Task>> tasks;

    public DailyToDoListViewModel(){
        this.tasks = new MutableLiveData<>();
    }

    public MutableLiveData<List<Task>> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.setValue(tasks);
    }

}