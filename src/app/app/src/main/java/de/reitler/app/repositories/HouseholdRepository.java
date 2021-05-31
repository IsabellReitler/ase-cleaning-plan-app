package de.reitler.app.repositories;

import android.content.Context;
import android.content.res.Resources;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import de.reitler.app.R;
import de.reitler.app.apiservice.HolidayService;
import de.reitler.app.apiservice.HouseholdRequestBody;
import de.reitler.app.apiservice.HouseholdService;
import de.reitler.app.apiservice.RoommateIDJSON;
import de.reitler.app.apiservice.RoommateService;
import de.reitler.app.config.Configuration;
import de.reitler.app.model.HolidayMode;
import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HouseholdRepository {

    Resources resources;

    private static final String BASE_URL = Configuration.BASE_URL;

    HouseholdService householdService;
    HolidayService holidayService;
    RoommateService roommateService;

    MutableLiveData<Household> householdMutableLiveData;
    MutableLiveData<List<Roommate>> roommatesMutableLiveData;
    MutableLiveData<List<Task>> allTasksFromHousehold;

    private static HouseholdRepository instance;

    public static HouseholdRepository getInstance(){
        if(instance == null){
            instance = new HouseholdRepository();
        }
        return instance;
    }

    private HouseholdRepository(){
        householdMutableLiveData = new MutableLiveData<>();
        roommatesMutableLiveData = new MutableLiveData<>();
        allTasksFromHousehold = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();


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

        householdService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(HouseholdService.class);
    }

    public void createHousehold(String name){
        householdService.createHousehold(new HouseholdRequestBody(name))
                .enqueue(new Callback<Household>() {
                    @Override
                    public void onResponse(Call<Household> call, Response<Household> response) {
                        if(response.body() != null){
                            householdMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Household> call, Throwable t) {
                        householdMutableLiveData.postValue(null);
                    }
                });
    }

    public void updateHousehold(Household newHousehold){
        householdService.updateHousehold(newHousehold.getId(), newHousehold)
                .enqueue(new Callback<Household>() {
                    @Override
                    public void onResponse(Call<Household> call, Response<Household> response) {
                        if(response.body() != null){
                            householdMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Household> call, Throwable t) {
                        //householdMutableLiveData.postValue(null);
                    }
                });
    }

    public void getHouseholdById(String id){
        householdService.getHouseholdById(id)
                .enqueue(new Callback<Household>() {
                    @Override
                    public void onResponse(Call<Household> call, Response<Household> response) {
                        if(response.body() != null){
                            householdMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Household> call, Throwable t) {
                        householdMutableLiveData.postValue(null);
                    }
                });
    }

    public void getHouseholdFromRoommate(String roommateId){
        householdService.getHouseholdFromRoommate(roommateId)
                .enqueue(new Callback<Household>() {
                    @Override
                    public void onResponse(Call<Household> call, Response<Household> response) {
                        if(response.body() != null){
                            householdMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Household> call, Throwable t) {
                        householdMutableLiveData.postValue(null);
                    }
                });
    }

    public void deleteHousehold(String id){
        householdService.deleteHousehold(id);
    }

    public synchronized void getRoommates(String id){
        householdService.getAllRoommatesFromHousehold(id)
                .enqueue(new Callback<List<Roommate>>() {
                    @Override
                    public void onResponse(Call<List<Roommate>> call, Response<List<Roommate>> response) {
                        if(response.body() != null){
                            roommatesMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Roommate>> call, Throwable t) {
                        roommatesMutableLiveData.postValue(null);
                    }
                });
    }

    public void addRoommateToHousehold(String householdId, String roommateId){
        householdService.addRoommateToHousehold(householdId, new RoommateIDJSON(roommateId))
                .enqueue(new Callback<List<Roommate>>() {
                    @Override
                    public void onResponse(Call<List<Roommate>> call, Response<List<Roommate>> response) {
                        if(response.body() != null){
                            roommatesMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Roommate>> call, Throwable t) {
                        roommatesMutableLiveData.postValue(null);
                    }
                });
    }

    public void removeRoommateFromHousehold(String householdId, String roommateId){
        householdService.removeRoommateFromHousehold(householdId, roommateId)
                .enqueue(new Callback<Household>() {
                    @Override
                    public void onResponse(Call<Household> call, Response<Household> response) {
                        if(response.body() != null){
                            householdMutableLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Household> call, Throwable t) {
                        //TODO: Exception
                    }
                });
    }

    public void getAllHolidayModes(String householdId){
        holidayService.getAllHolidayModes(householdId).enqueue(new Callback<List<HolidayMode>>() {
            @Override
            public void onResponse(Call<List<HolidayMode>> call, Response<List<HolidayMode>> response) {
                List<Roommate> roommates = new ArrayList<>();
                if(response.body() != null){
                    for(HolidayMode h: response.body()){
                        roommateService.getRoommateById(h.getId()).enqueue(new Callback<Roommate>() {
                            @Override
                            public void onResponse(Call<Roommate> call, Response<Roommate> response) {
                                roommates.add(response.body());
                            }

                            @Override
                            public void onFailure(Call<Roommate> call, Throwable t) {

                            }
                        });
                    }
                    roommatesMutableLiveData.postValue(roommates);
                }
            }

            @Override
            public void onFailure(Call<List<HolidayMode>> call, Throwable t) {
                //
            }
        });
    }

    public void getAllTasksFromHousehold(String householdId){
        householdService.getAllTasksFromHousehold(householdId).enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if(response.body() != null) {
                    allTasksFromHousehold.postValue(response.body());
                }
                else{
                    System.out.println("response body null");
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Household> getHouseholdMutableLiveData() {
        return householdMutableLiveData;
    }

    public synchronized MutableLiveData<List<Roommate>> getRoommatesMutableLiveData() {
        return roommatesMutableLiveData;
    }

    public MutableLiveData<List<Task>> getAllTasksFromHousehold() {
        return allTasksFromHousehold;
    }
}
