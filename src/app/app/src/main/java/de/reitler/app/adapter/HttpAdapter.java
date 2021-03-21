package de.reitler.app.adapter;

import android.content.res.Resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.reitler.app.R;
import de.reitler.app.model.Household;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpAdapter {

    OkHttpClient client = new OkHttpClient();
    ObjectMapper mapper;


    public void sendUserToBackend(FirebaseUser user){
       // String url = Resources.getSystem().getString(R.string.API_URL)+Resources.getSystem().getString(R.string.LOGIN_ENDPOINT);
        String url = "http://192.168.120.3:8080/roommate";
        String bodyJSON = "";

        try {
            bodyJSON = new JSONObject()
                    .put("id", user.getUid())
                    .put("display_name", user.getDisplayName())
                    .put("email", user.getEmail())
                    .put("picture", user.getPhotoUrl())
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, bodyJSON );
        Request request = new Request.Builder().url(url).method("POST", body).build();

        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200){
                throw new IOException("Request was not successful! Statuscode: "+response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public Household createHousehold(String name){
        String url = "http://192.168.120.3:8080/household";
        String bodyJSON = "";

        try {
            bodyJSON = new JSONObject()
                    .put("name", name)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, bodyJSON );
        Request request = new Request.Builder().url(url).method("POST", body).build();

        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200){
                throw new IOException("Request was not successful! Statuscode: "+response.code());
            }
            String responseBody = response.body().string();
            System.out.println(responseBody);
            mapper = new ObjectMapper();
            Household household = mapper.readValue(responseBody, Household.class);
            return household;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return null;
    }

    public String addUserToHousehold(String userId, String householdId){
        String url = "http://192.168.120.3:8080/household/"+householdId+"/addRoommate";
        String bodyJSON = "";

        try {
            bodyJSON = new JSONObject()
                    .put("roommate_id", userId)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, bodyJSON );
        Request request = new Request.Builder().url(url).method("PUT", body).build();
        try {
            Response response = client.newCall(request).execute();
            if(response.code() != 200){
                throw new IOException("Request was not successful! Statuscode: "+response.code());
            }
            String responseBpdy =response.body().string();
            System.out.println(responseBpdy);
            return responseBpdy;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
