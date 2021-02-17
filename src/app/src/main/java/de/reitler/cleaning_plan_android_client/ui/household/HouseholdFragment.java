package de.reitler.cleaning_plan_android_client.ui.household;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import de.reitler.cleaning_plan_android_client.R;

public class HouseholdFragment extends Fragment {

    private HouseholdViewModel householdViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        householdViewModel =
                new ViewModelProvider(this).get(HouseholdViewModel.class);
        View root = inflater.inflate(R.layout.fragment_household, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        householdViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}