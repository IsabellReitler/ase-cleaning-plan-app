package de.reitler.app.apiservice;

import de.reitler.app.model.Task;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TaskService {

    @POST("/task")
    Call<Task> createTask(@Body Task task);

    @GET("/task/{id}")
    Call<Task> getTaskById(@Path("id") String id);

    @PUT("/task/{id}")
    Call<Task> updateTask(@Path("id") String id, @Body Task task);

    @DELETE("/task/{id}")
    Call deleteTask(@Path("id") String id);

}
