package de.reitler.app.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import de.reitler.app.ui.add_household.AddHouseholdActivity;
import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.model.Household;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "FIREBASE";
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth mAuth;
    public static final String USER_ID = "USER_ID";
    LoginViewModel loginViewModel;
    SignInViewModel signInViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        signInViewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.CLIENT_ID))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);

        findViewById(R.id.google_sign_in_button).setOnClickListener(v -> signIn());
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        redirectToApp(currentUser);
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
     * @param user
     */
    public void redirectToApp(FirebaseUser user){
        if(user != null){

            loginViewModel.init();
            loginViewModel.getHousehold().observe(this, new Observer<Household>() {
                @Override
                public void onChanged(Household household) {
                    startMainActivity(household.getId(), user.getUid());
                    loginViewModel.getHousehold().removeObserver(this);
                }
            });
            loginViewModel.findHouseholdFromRoommate(user.getUid());

        }
    }

    private void startMainActivity(String household, String user) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", user);
                intent.putExtra("HOUSEHOLD_ID", household);
                startActivity(intent);
            }
        });
    }

    private void redirectToCreateHousehold(FirebaseUser user) {
        signInViewModel.init();
        signInViewModel.createRoommate(user.getUid(), user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString());
        Intent intent = new Intent(this, AddHouseholdActivity.class);
        intent.putExtra(USER_ID, user.getUid());
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }

        }
        
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            redirectToCreateHousehold(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            redirectToApp(null);
                        }
                    }
                });
    }



}

  