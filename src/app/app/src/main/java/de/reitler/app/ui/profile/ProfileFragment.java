package de.reitler.app.ui.profile;

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

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.model.Roommate;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        activity = (MainActivity) getActivity();
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        profileViewModel.updateRoommate(activity.userId);
    }
}