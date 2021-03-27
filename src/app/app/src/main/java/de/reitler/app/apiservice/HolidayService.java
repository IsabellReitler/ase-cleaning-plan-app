package de.reitler.app.apiservice;

import java.util.Calendar;
import java.util.List;

import de.reitler.app.model.HolidayMode;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HolidayService {

    @GET("/holidaymode/{householdId}")
    Call<List<HolidayMode>> getAllHolidayModes(@Path("householdId") String householdId);

    @GET("holidaymode/roommate/{roommateId}")
    Call<Roommate> getHolidayModeFromRoommate(@Path("roommateId") String roommateId);

    @POST("/holidaymode")
    Call<Roommate> createHolidayMode(@Field("roommate_id") String roommateId, @Field("end_date")Calendar endDate);

    @DELETE("/holidaymode/{roommateId}")
    Call deleteHolidayMode(@Path("roommateId") String roommateId);
}
