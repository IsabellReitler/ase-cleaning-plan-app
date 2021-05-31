package de.reitler.app.ui.household;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import de.reitler.app.R;
import de.reitler.app.model.Roommate;
import jp.wasabeef.picasso.transformations.MaskTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class RoommateRecyclerViewAdapter extends RecyclerView.Adapter<RoommateViewHolder> {

    private List<Roommate> roommates = new ArrayList<>();
    private Context context;

    public RoommateRecyclerViewAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RoommateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_roommate_viewholder, parent, false);
        //ImageView image = itemView.findViewById(R.id.roommate_picture);
        TextView name = itemView.findViewById(R.id.roommate_name);
        return new RoommateViewHolder( name, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoommateViewHolder holder, int position) {
        //holder.getPicture().setImageURI(Uri.parse(roommates.get(position).getPicture().toString()));

        holder.getName().setText(roommates.get(position).getName());
        Uri uri = Uri.parse(roommates.get(position).getPicture().toString());
        final Transformation transformation = new MaskTransformation(context, R.drawable.rounded_convers_transformation);
        Picasso.get()
                .load(uri)
                .transform(transformation)
                //.resize(150, 150)
                //.centerCrop()
                .into(holder.getPicture());
    }

    @Override
    public int getItemCount() {
        return roommates.size();
    }

    public void setRoommates(List<Roommate> roommates) {
        this.roommates = roommates;
        notifyDataSetChanged();
    }
}
