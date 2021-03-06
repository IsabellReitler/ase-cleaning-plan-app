package de.reitler.app.adapter;

import android.content.res.Resources;

import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.reitler.app.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpAdapter {
    

    private static String url = Resources.getSystem().getString(R.string.API_URL)+Resources.getSystem().getString(R.string.LOGIN_ENDPOINT);
    private static String bodyJSON = "";

    public static void sendUserToBackend(FirebaseUser user){

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
        OkHttpClient client = new OkHttpClient();


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, bodyJSON );
        Request request = new Request.Builder().url(url).method("POST", body).build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
