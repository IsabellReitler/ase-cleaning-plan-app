package de.reitler.app.apiservice;

import java.util.List;

import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HouseholdService {

    @POST("/household")
    Call<Household> createHousehold(@Body HouseholdRequestBody householdName);

    @PUT("/household/{id}")
    Call<Household> updateHousehold(@Path("id") String id, @Body Household household);

    @GET("/household/{id}")
    Call<Household> getHouseholdById(@Path("id") String id);

    @DELETE("/household/{id}")
    Call deleteHousehold(@Path("id") String id);

    @GET("/household/{id}/roommates")
    Call<List<Roommate>> getAllRoommatesFromHousehold(@Path("id") String id);

    @PUT("/household/{id}/addRoommate")
    Call<List<Roommate>> addRoommateToHousehold(@Path("id") String householdId, @Body RoommateIDJSON roommate);

    @DELETE("/household/{householdId}/removeRoommate/{roommateId}")
    Call<Household> removeRoommateFromHousehold(@Path("householdId") String householdId, @Path("roommateId") String roommateId);

    @GET("/household/withRoommate/{roommateId}")
    Call<Household> getHouseholdFromRoommate(@Path("roommateId") String roommateId);

    @GET("/household/{id}/allTasks")
    Call<List<Task>> getAllTasksFromHousehold(@Path("id") String id);
}

