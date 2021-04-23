package de.reitler.app;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.model.Task;
import de.reitler.app.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public String userId;
    public String householdId;
    public MainActivityViewModel viewModel;
    public Application application;

    TextView navHeaderEmail;
    TextView navHeaderTitle;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getApplication();
        viewModel = new MainActivityViewModel(application);
        Intent intent = getIntent();
        userId = intent.getStringExtra("USER_ID");
        householdId = intent.getStringExtra("HOUSEHOLD_ID");
        viewModel.getRoommate().observe(this, new Observer<Roommate>() {
            @Override
            public void onChanged(Roommate roommate) {
                updateUI(roommate);
            }
        });
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_todolist, R.id.nav_household, R.id.nav_profile, R.id.nav_settings, R.id.nav_about)
                .setDrawerLayout(findViewById(R.id.drawer_layout))
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View hView =  navigationView.getHeaderView(0);
        navHeaderTitle = hView.findViewById(R.id.nav_header_title);
        navHeaderEmail = hView.findViewById(R.id.nav_header_email);
        viewModel.updateRoommate(userId);
    }


    private void updateUI(Roommate roommate) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                navHeaderTitle.setText(roommate.getName());
                navHeaderEmail.setText(roommate.getEmail());
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}