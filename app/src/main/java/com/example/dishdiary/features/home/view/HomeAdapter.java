package com.example.dishdiary.features.home.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import com.example.dishdiary.R;
import com.example.dishdiary.model.Meal;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final Context context;
    private final OnHomeClickListener listener;
    private List<Meal> values;

    private static final String TAG = "RecyclerView";

    public HomeAdapter(Context context, List<Meal> values, OnHomeClickListener listener) {
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
        private final Button btnAddToFav;
        private final View layout;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            txtName = v.findViewById(R.id.txtName);
            txtCategory = v.findViewById(R.id.txtCategory);
            txtArea = v.findViewById(R.id.txtArea);
            imgView = v.findViewById(R.id.imgView);
            btnAddToFav = v.findViewById(R.id.btnAddToFav);
        }
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.home_meal, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== ViewHolder Created ====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Meal meal = values.get(position);

        Glide.with(context).load(meal.getStrMealThumb())
                .apply(new RequestOptions()//.override(200, 200)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.imgView);

        holder.txtName.setText(meal.getStrMeal());
        holder.txtCategory.setText(meal.getStrCategory());
        holder.txtArea.setText(meal.getStrArea());
        holder.btnAddToFav.setEnabled(!meal.isFav());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onLayoutClick(meal);
            }
        });


        holder.btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAddToFavClick(meal);
//                listener.openDetails();
            }
        });

        Log.i(TAG, "===== ViewHolder Binded ====");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
