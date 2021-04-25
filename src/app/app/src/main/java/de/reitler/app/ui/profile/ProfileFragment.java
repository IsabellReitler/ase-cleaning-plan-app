package de.reitler.app.ui.profile;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import de.reitler.app.MainActivity;
import de.reitler.app.R;
import de.reitler.app.model.Roommate;
import jp.wasabeef.picasso.transformations.MaskTransformation;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private MainActivity activity;
    private ImageView picture;
    private TextView name;
    private TextView email;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        activity = (MainActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        picture = view.findViewById(R.id.profile_picture);
        name = view.findViewById(R.id.profile_username);
        email = view.findViewById(R.id.profile_email);

        profileViewModel.getRoommate().observe(getViewLifecycleOwner(), new Observer<Roommate>() {
            @Override
            public void onChanged(Roommate roommate) {
                Uri uri = Uri.parse(roommate.getPicture().toString());
                final Transformation transformation = new MaskTransformation(view.getContext(), R.drawable.rounded_convers_transformation);
                Picasso.get()
                        .load(uri)
                        .transform(transformation)
                        //.resize(150, 150)
                        //.centerCrop()
                        .into(picture);
                name.setText(roommate.getName());
                email.setText(roommate.getEmail());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        profileViewModel.updateRoommate(activity.userId);
    }
}