package de.reitler.app.ui.todolist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import de.reitler.app.model.Task;

public class ToDoListViewModel extends ViewModel {
    

    private LiveData<String> roommateId;

    public ToDoListViewModel() {
        roommateId = new MutableLiveData<>();
    }

    public LiveData<String> getRoommateId() {
        return roommateId;
    }

    public void setRoommateId(String roommateId) {

    }
}