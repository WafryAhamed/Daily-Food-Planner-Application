package com.example.dishdiary.features.explore.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdiary.R;
import com.example.dishdiary.model.Ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExploreIngredientsAdapter extends RecyclerView.Adapter<ExploreIngredientsAdapter.ViewHolder> {

    private final Context context;
    private final OnExploreClickListener listener;
    private List<Ingredient> values;

    private String ingredientsBaseUrl = "https://www.themealdb.com/images/ingredients/";

    private static final String TAG = "RecyclerView";

    public ExploreIngredientsAdapter(Context context, List<Ingredient> values, OnExploreClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }

    public void setList(List<Ingredient> ingredients) {
        this.values = ingredients;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView imgIngredient;
        private final TextView txtIngredientName;
        private final View layout;


        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            imgIngredient = v.findViewById(R.id.imgIngredient);
            txtIngredientName = v.findViewById(R.id.txtIngredientName);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.item_ingredient, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== ViewHolder Created ====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = values.get(position);

        Glide.with(context).load(ingredientsBaseUrl + ingredient.getStrIngredient() + ".png")
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.imgIngredient);


        holder.txtIngredientName.setText(ingredient.getStrIngredient());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onIngredientClick(ingredient);
            }
        });

        Log.i(TAG, "===== ViewHolder Binded ====");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
