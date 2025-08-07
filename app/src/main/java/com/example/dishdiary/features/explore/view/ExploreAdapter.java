package com.example.dishdiary.features.explore.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdiary.R;
import com.example.dishdiary.model.Category;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    private final Context context;
    private final OnExploreClickListener listener;
    private List<Category> values;

    private static final String TAG = "RecyclerView";

    public ExploreAdapter(Context context, List<Category> values, OnExploreClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
    }

    public void setList(List<Category> categories) {
        this.values = categories;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgCategory;
        private final TextView txtCategName;
        private final View layout;


        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            txtCategName = v.findViewById(R.id.txtCategName);
            imgCategory = v.findViewById(R.id.imgCategory);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.card_category, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== ViewHolder Created ====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = values.get(position);

        Glide.with(context).load(category.getStrCategoryThumb())
                .apply(new RequestOptions()//.override(200, 200)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.imgCategory);

        holder.txtCategName.setText(category.getStrCategory());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCategoryLayoutClick(category);
            }
        });

        Log.i(TAG, "===== ViewHolder Binded ====");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
