package de.reitler.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;

import de.reitler.app.adapter.HttpAdapter;
import de.reitler.app.model.Roommate;
import de.reitler.app.ui.login.LoginActivity;

public class AddHousehold extends AppCompatActivity {

    String userId;

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
                HttpAdapter adapter = new HttpAdapter();
                if(adapter.addUserToHousehold(userId,input.getText().toString())!= null){
                  redirectToApp(userId);
                }
            }
        });

        createHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: PopUP mit Eingabefeld, anschließend httpadapter createHousehold und addRoommate

            }
        });
    }

    private void redirectToApp(String userId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("USERID", userId);
        startActivity(intent);
    }


}