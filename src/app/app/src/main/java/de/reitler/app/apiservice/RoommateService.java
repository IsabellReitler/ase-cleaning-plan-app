package de.reitler.app.apiservice;

import java.util.List;

import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RoommateService {

    @POST("/roommate")

    Call<Roommate> createRoommate(@Body RoommateRequestBody roommate);

    @GET("/roommate/{roommateId}")
    Call<Roommate> getRoommateById(@Path("roommateId")String roommateId);

    @PUT("/roommate/{roommateId}")
    Call<Roommate> updateRoommate(@Path("roommateId") String roommateId, @Body Roommate roommate);

    @GET("/roommate/{id}/tasks")
    Call<List<Task>> getAllTasksFromRoommate(@Path("id") String id);

    @GET("/roommate/{id}/tasks/daily")
    Call<List<Task>> getAllDailyTasksFromRoommate(@Path("id") String id);

    @GET("/roommate/{id}/tasks/weekly")
    Call<List<Task>> getAllWeeklyTasksFromRoommate(@Path("id") String id);

}

