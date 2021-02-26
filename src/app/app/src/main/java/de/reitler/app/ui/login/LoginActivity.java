package de.reitler.app.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 0;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.CLIENT_ID))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,gSignInOptions);
        account = GoogleSignIn.getLastSignedInAccount(this);
        redirectToApp(account);
        SignInButton signInButton = findViewById(R.id.google_sign_in_button);
        findViewById(R.id.google_sign_in_button).setOnClickListener(v -> signIn());
    }

    /**
     * creating a sign-in intent with the getSignInIntent method, and starting the intent with startActivityForResult
     */
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * Redirects to the MainActivity
     * @param account
     */
    public void redirectToApp(GoogleSignInAccount account){
        if(account != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();

            String jsonString = new JSONObject()
                    .put("idToken", idToken)
                    .toString();
            sendTokenToBackend(jsonString);
        } catch (ApiException e) {
            Log.w("ERROR","signInResult:failed code=" + e.getStatusCode());
            redirectToApp(null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendTokenToBackend(String bodyJSON){
        OkHttpClient client = new OkHttpClient();
        String url = getString(R.string.API_URL)+ getString(R.string.LOGIN_ENDPOINT);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, bodyJSON );

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            client.newCall(request).execute().body().string();
            redirectToApp(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}