package de.reitler.app.ui.household;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.reitler.app.R;


public class RoommateViewHolder extends RecyclerView.ViewHolder {

    private ImageView picture;
    private TextView name;

    public RoommateViewHolder(TextView name, View view) {
        super(view);
        this.name = name;
        this.picture = view.findViewById(R.id.roommate_picture);
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }
}
