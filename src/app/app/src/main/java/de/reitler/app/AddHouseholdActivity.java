package de.reitler.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import de.reitler.app.adapter.HttpAdapter;
import de.reitler.app.model.Household;
import de.reitler.app.model.Roommate;
import de.reitler.app.ui.dialog.CreateHouseholdDialogFragment;
import de.reitler.app.ui.login.LoginActivity;
import de.reitler.app.viewmodel.OverallViewModel;

public class AddHouseholdActivity extends AppCompatActivity implements CreateHouseholdDialogFragment.CreateHouseholdDialogListener {

    HttpAdapter adapter = new HttpAdapter();
    String userId;
    String householdName;
    OverallViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_household);
        Intent intent = getIntent();
        userId = intent.getStringExtra(LoginActivity.USER_ID);
        EditText input = findViewById(R.id.add_household);
        Button addHousehold = findViewById(R.id.button_add_household);
        Button createHousehold = findViewById(R.id.button_create_household);

        addHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.addUserToHousehold(userId,input.getText().toString())!= null){
                   redirectToApp(userId);
                }
            }
        });

        createHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: PopUP mit Eingabefeld, anschließend httpadapter createHousehold und addRoommate
                DialogFragment dialog = new CreateHouseholdDialogFragment();
                dialog.show(getSupportFragmentManager(), "CreateHouseholdDialog");
            }
        });
    }

    private void redirectToApp(String userId) {
        viewModel = OverallViewModel.init(userId);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }


    @Override
    public void onDialogPositiveClick(String input) {
        householdName = input;
        if(householdName != null){
             Household household = adapter.createHousehold(householdName);
             List<Roommate> roommates = adapter.addUserToHousehold(userId, household.getId());
             redirectToApp(userId);
        }
    }


}