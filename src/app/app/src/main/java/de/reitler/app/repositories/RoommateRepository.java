package de.reitler.app.repositories;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.reitler.app.apiservice.HolidayService;
import de.reitler.app.apiservice.RoommateRequestBody;
import de.reitler.app.apiservice.RoommateService;
import de.reitler.app.model.HolidayMode;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoommateRepository {

    private static final String BASE_URL="http://192.168.120.3:8080/";

    HolidayService holidayService;
    RoommateService roommateService;
    MutableLiveData<Roommate> roommateMutableLiveData;
    MutableLiveData<List<Task>> dailyTasksMutableLiveData;
    MutableLiveData<List<Task>> weeklyTasksMutableLiveData;
    MutableLiveData<List<Task>> allTasksMutableLiveData;

    public RoommateRepository(){
        roommateMutableLiveData = new MutableLiveData<>();
        allTasksMutableLiveData = new MutableLiveData<>();
        dailyTasksMutableLiveData = new MutableLiveData<>();
        weeklyTasksMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        holidayService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(HolidayService.class);
        roommateService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RoommateService.class);
    }

    public void createRoommate(String id, String displayName, String email, String picture){
        roommateService.createRoommate(new RoommateRequestBody(id, displayName, email, picture))
                .enqueue(new Callback<Roommate>() {
                    @Override
                    public void onResponse(Call<Roommate> call, Response<Roommate> response) {
                        if (response.body() != null){
                            roommateMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Roommate> call, Throwable t) {
                        roommateMutableLiveData.postValue(null);
                    }
                });
    }

    public void getRoommate(String id){
        roommateService.getRoommateById(id)
                .enqueue(new Callback<Roommate>() {
                    @Override
                    public void onResponse(Call<Roommate> call, Response<Roommate> response) {
                        if (response.body() != null){
                            roommateMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Roommate> call, Throwable t) {
                        roommateMutableLiveData.postValue(null);
                    }
                });
    }

    public void updateRoommate(Roommate newRoommate){
        roommateService.updateRoommate(newRoommate.getId(), newRoommate)
                .enqueue(new Callback<Roommate>() {
                    @Override
                    public void onResponse(Call<Roommate> call, Response<Roommate> response) {
                        if (response.body() != null){
                            roommateMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Roommate> call, Throwable t) {
                        roommateMutableLiveData.postValue(null);
                    }
                });
    }

    public void getTasksFromRoommate(String id){
        roommateService.getAllTasksFromRoommate(id)
                .enqueue(new Callback<List<Task>>() {
                    @Override
                    public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                        if (response.body() != null){
                            allTasksMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Task>> call, Throwable t) {
                        allTasksMutableLiveData.postValue(null);
                    }
                });
    }

    public void getDailyTasksFromRoommate(String id){
        roommateService.getAllDailyTasksFromRoommate(id)
                .enqueue(new Callback<List<Task>>() {
                    @Override
                    public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                        if (response.body() != null){
                            dailyTasksMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Task>> call, Throwable t) {
                        dailyTasksMutableLiveData.postValue(null);
                    }
                });
    }

    public void getWeeklyTasksFromRoommate(String id){
        roommateService.getAllWeeklyTasksFromRoommate(id)
                .enqueue(new Callback<List<Task>>() {
                    @Override
                    public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                        if (response.body() != null){
                            weeklyTasksMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Task>> call, Throwable t) {
                        weeklyTasksMutableLiveData.postValue(null);
                    }
                });
    }

    public void setHolidayMode(String roommateId, Calendar endDate){
        HolidayMode mode = new HolidayMode(roommateId, endDate.getTime());
        holidayService.createHolidayMode(mode).enqueue(new Callback<Roommate>() {
            @Override
            public void onResponse(Call<Roommate> call, Response<Roommate> response) {
                if (response.body() != null){
                    roommateMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Roommate> call, Throwable t) {
                //TODO
            }
        });
    }

    public void deleteHolidayMode(String roommateId){
        holidayService.deleteHolidayMode(roommateId);
    }

    public void getHolidayMode(String roommateId){
        holidayService.getHolidayModeFromRoommate(roommateId)
                .enqueue(new Callback<Roommate>() {
            @Override
            public void onResponse(Call<Roommate> call, Response<Roommate> response) {
                if (response.body() != null){
                    roommateMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Roommate> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Roommate> getRoommateMutableLiveData() {
        return roommateMutableLiveData;
    }

    public MutableLiveData<List<Task>> getDailyTasksMutableLiveData() {
        return dailyTasksMutableLiveData;
    }

    public MutableLiveData<List<Task>> getWeeklyTasksMutableLiveData() {
        return weeklyTasksMutableLiveData;
    }

    public MutableLiveData<List<Task>> getAllTasksMutableLiveData() {
        return allTasksMutableLiveData;
    }

}
