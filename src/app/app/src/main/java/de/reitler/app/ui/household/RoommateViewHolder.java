package de.reitler.app.ui.household;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;


public class RoommateViewHolder extends RecyclerView.ViewHolder {

    private CircularRevealCardView picture;
    private TextView name;

    public RoommateViewHolder(CircularRevealCardView picture,TextView name, View view) {
        super(view);
        this.picture = picture;
        this.name = name;
    }
}
