package de.reitler.app.ui.household;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;


public class RoommateViewHolder extends RecyclerView.ViewHolder {

  //  private ImageView picture;
    private TextView name;

    public RoommateViewHolder(TextView name, View view) {
        super(view);
       // this.picture = picture;
        this.name = name;
    }

    //public ImageView getPicture() {
    //    return picture;
    //}

    //public void setPicture(ImageView picture) {
    //    this.picture = picture;
    //}

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }
}
