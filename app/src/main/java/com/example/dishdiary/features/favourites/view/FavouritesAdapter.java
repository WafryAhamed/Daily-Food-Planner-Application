package com.example.dishdiary.features.favourites.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdiary.R;
import com.example.dishdiary.model.Meal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    private final Context context;
    private final OnFavouritesClickListener listener;
    private List<Meal> values;

    private static final String TAG = "FavRecyclerView";

    public FavouritesAdapter(Context context, List<Meal> values, OnFavouritesClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }

    public void setList(List<Meal> meals) {
        this.values = meals;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgView;
        private final TextView txtName, txtCategory, txtArea;
        private final Button btnRemoveFromFav;
        private final FloatingActionButton fabAddToCal;
        private final View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            txtName = v.findViewById(R.id.txtName);
            txtCategory = v.findViewById(R.id.txtCategory);
            txtArea = v.findViewById(R.id.txtArea);
            imgView = v.findViewById(R.id.imgView);
            btnRemoveFromFav = v.findViewById(R.id.btnAddToFav);
            fabAddToCal = v.findViewById(R.id.fabAddToCal);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.card_meal, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== ViewHolder Created ====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = values.get(position);
        

        Glide.with(context).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.imgView);

        holder.txtName.setText(meal.getStrMeal());
        holder.txtCategory.setText(meal.getStrCategory());
        holder.txtArea.setText(meal.getStrArea());
        holder.btnRemoveFromFav.setForeground(ContextCompat.getDrawable(context, R.drawable.baseline_bookmark_remove_24));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onLayoutClick(meal);
            }
        });


        holder.btnRemoveFromFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are you sure?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.onRemoveFromFavClick(meal);
                                Toast.makeText(view.getContext(), "Meal removed from favourites", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setAnchorView(R.id.btnAddToFav).show();
            }
        });

        holder.fabAddToCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { listener.onAddToCalendar(meal); }
        });

        Log.i(TAG, "===== ViewHolder Binded ====");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
