package com.example.dishdiary.features.meal_details.view;

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
import com.example.dishdiary.features.explore.view.OnExploreClickListener;
import com.example.dishdiary.model.Ingredient;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsIngredientsAdapter extends RecyclerView.Adapter<DetailsIngredientsAdapter.ViewHolder> {

    private final Context context;
    private final OnMealDetailsClickListener listener;
    private List<Ingredient> values;

    private final String ingredientsBaseUrl;

    private static final String TAG = "RecyclerView";

    public DetailsIngredientsAdapter(Context context, List<Ingredient> values, OnMealDetailsClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
        ingredientsBaseUrl = "https://www.themealdb.com/images/ingredients/";
    }

    public void setList(List<Ingredient> ingredients) {
        this.values = ingredients;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView imgDetIngredient;
        private final TextView txtDetIngredientName;
        private final TextView txtMeasure;
        private final View layout;


        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            imgDetIngredient = v.findViewById(R.id.imgDetIngredient);
            txtDetIngredientName = v.findViewById(R.id.txtDetIngredientName);
            txtMeasure = v.findViewById(R.id.txtMeasure);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.item_detail_ingredient, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== ViewHolder Created ====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = values.get(position);

        Glide.with(context).load(ingredientsBaseUrl + ingredient.getStrIngredient() + ".png")
                .apply(new RequestOptions().override(150, 150)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.imgDetIngredient);


        holder.txtDetIngredientName.setText(ingredient.getStrIngredient());
        holder.txtMeasure.setText(ingredient.getStrMeasure());

//        holder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onIngredientClick(ingredient);
//            }
//        });

        Log.i(TAG, "===== ViewHolder Binded ====");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
