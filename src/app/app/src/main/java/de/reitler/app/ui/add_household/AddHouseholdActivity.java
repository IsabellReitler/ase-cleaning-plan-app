package de.reitler.app.ui.add_household;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.ui.dialog.CreateHouseholdDialogFragment;
import de.reitler.app.ui.login.LoginActivity;

public class AddHouseholdActivity extends AppCompatActivity implements CreateHouseholdDialogFragment.CreateHouseholdDialogListener {


    String userId;
    String householdName;
    AddHouseholdViewModel viewModel;
    LifecycleOwner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        owner = this;
        setContentView(R.layout.activity_add_household);
        Intent intent = getIntent();
        userId = intent.getStringExtra(LoginActivity.USER_ID);
        viewModel = ViewModelProviders.of(this).get(AddHouseholdViewModel.class);
        viewModel.init();
        EditText input = findViewById(R.id.add_household);
        Button addHousehold = findViewById(R.id.button_add_household);
        Button createHousehold = findViewById(R.id.button_create_household);

        addHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getHouseholdLiveData().observe(owner, new Observer<Household>() {
                    @Override
                    public void onChanged(Household household) {
                        redirectToApp(userId,household.getId());
                        viewModel.getHouseholdLiveData().removeObserver(this);
                    }
                });
                viewModel.addRoommate(input.getText().toString(), userId);
                viewModel.getRoommatesLiveData().observe(owner, new Observer<List<Roommate>>() {
                    @Override
                    public void onChanged(List<Roommate> roommates) {
                        viewModel.getHouseholdFromRoommate(userId);
                    }
                });
            }
        });

        createHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new CreateHouseholdDialogFragment();
                dialog.show(getSupportFragmentManager(), "CreateHouseholdDialog");
            }
        });
    }

    private void startMainActivity(String household, String userId) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AddHouseholdActivity.this, MainActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("HOUSEHOLD_ID", household);
                AddHouseholdActivity.this.startActivity(intent);
            }
        });
    }

    private void redirectToApp(String userId, String householdId) {
        startMainActivity(householdId, userId);
    }


    @Override
    public void onDialogPositiveClick(String input) {
        householdName = input;
        if(householdName != null){
            viewModel.createHousehold(householdName);
            viewModel.getHouseholdLiveData().observe(this, new Observer<Household>() {
                @Override
                public void onChanged(Household household) {
                    viewModel.addRoommate(household.getId(), userId);
                    redirectToApp(userId, household.getId());
                }
            });

        }
    }


}