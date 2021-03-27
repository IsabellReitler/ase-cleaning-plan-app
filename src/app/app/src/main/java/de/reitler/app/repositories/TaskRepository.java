package de.reitler.app.repositories;

import androidx.lifecycle.MutableLiveData;

import de.reitler.app.apiservice.HouseholdService;
import de.reitler.app.apiservice.TaskService;
import de.reitler.app.model.Task;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskRepository {

    private static final String BASE_URL="http://192.168.120.3:8080/task/";

    TaskService taskService;
    MutableLiveData<Task> taskMutableLiveData;

    public TaskRepository(){
        taskMutableLiveData = new MutableLiveData<>();

        OkHttpClient client = new OkHttpClient.Builder().build();

        taskService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TaskService.class);

    }

    public void createTask(Task task){
        taskService.createTask(task)
                .enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        if(response.body() != null){
                            taskMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        taskMutableLiveData.postValue(null);
                    }
                });
    }

    public void updateTask(Task task){
        taskService.updateTask(task.getId(), task)
                .enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        if(response.body() != null){
                            taskMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        taskMutableLiveData.postValue(null);
                    }
                });
    }

    public void getTaskById(String id){
        taskService.getTaskById(id)
                .enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        if(response.body() != null){
                            taskMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        taskMutableLiveData.postValue(null);
                    }
                });
    }

    public void deleteTask(String id){
        taskService.deleteTask(id);
    }
}
