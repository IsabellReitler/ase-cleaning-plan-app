package de.reitler.app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import de.reitler.app.apiservice.HouseholdService;
import de.reitler.app.apiservice.RepetitiveTaskCreateBody;
import de.reitler.app.apiservice.SimpleTaskCreateBody;
import de.reitler.app.apiservice.TaskService;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskRepository {

    private static final String BASE_URL="http://192.168.120.3:8080/";

    TaskService taskService;
    MutableLiveData<Task> taskMutableLiveData;
HouseholdRepository householdRepository = HouseholdRepository.getInstance();
    RoommateRepository roommateRepository = RoommateRepository.getInstance();

    private static TaskRepository instance;

    public static TaskRepository getInstance(){
        if(instance==null){
            instance = new TaskRepository();
        }
        return instance;
    }

    public TaskRepository(){
        taskMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        taskService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TaskService.class);

    }

    public void createTask(RepetitiveTaskCreateBody task){
        taskService.createTask(task)
                .enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        if(response.body() != null){
                            taskMutableLiveData.postValue(response.body());
                            householdRepository.getAllTasksFromHousehold(householdRepository.householdMutableLiveData.getValue().getId());

                            householdRepository.getAllTasksFromHousehold(householdRepository.householdMutableLiveData.getValue().getId());

                        }
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        taskMutableLiveData.postValue(null);
                    }
                });
    }

    public void createTask(SimpleTaskCreateBody task){
        taskService.createTask(task)
                .enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        if(response.body() != null){
                            taskMutableLiveData.postValue(response.body());
                            householdRepository.getAllTasksFromHousehold(householdRepository.householdMutableLiveData.getValue().getId());

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
                            roommateRepository.getDailyTasksFromRoommate(response.body().getRoommateID());
                            roommateRepository.getWeeklyTasksFromRoommate(response.body().getRoommateID());
                            householdRepository.getAllTasksFromHousehold(householdRepository.householdMutableLiveData.getValue().getId());

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

    public void deleteTask(String id) throws IOException {
        taskService.deleteTask(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                taskMutableLiveData.postValue(null);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}
