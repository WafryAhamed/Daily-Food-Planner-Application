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
import com.example.dishdiary.model.Area;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExploreAreasAdapter extends RecyclerView.Adapter<ExploreAreasAdapter.ViewHolder> {

    private final Context context;
    private final OnExploreClickListener listener;
    private List<Area> values;

    private Map<String, String> countries;
    private String flagsBaseUrl = "https://flagcdn.com/w160/";

    private static final String TAG = "RecyclerView";

    public ExploreAreasAdapter(Context context, List<Area> values, OnExploreClickListener listener) {
        this.context = context;
        this.values = values;
        this.listener = listener;
        this.countries = new HashMap<>();

        countries.put("American", "us");
        countries.put("British", "gb");
        countries.put("Canadian", "ca");
        countries.put("Chinese", "cn");
        countries.put("Croatian", "hr");
        countries.put("Dutch", "nl");
        countries.put("Egyptian", "eg");
        countries.put("Filipino", "ph");
        countries.put("French", "fr");
        countries.put("Greek", "gr");
        countries.put("Indian", "in");
        countries.put("Irish", "ie");
        countries.put("Italian", "it");
        countries.put("Jamaican", "jm");
        countries.put("Japanese", "jp");
        countries.put("Kenyan", "ke");
        countries.put("Malaysian", "my");
        countries.put("Mexican", "mx");
        countries.put("Moroccan", "ma");
        countries.put("Polish", "pl");
        countries.put("Portuguese", "pt");
        countries.put("Russian", "ru");
        countries.put("Spanish", "es");
        countries.put("Thai", "th");
        countries.put("Tunisian", "tn");
        countries.put("Turkish", "tr");
        countries.put("Ukrainian", "ua");
//        countries.put("Unknown", "us-nv");
        countries.put("Vietnamese", "vn");
    }

    public void setList(List<Area> areas) {
        this.values = areas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView imgArea;
        private final TextView txtAreaName;
        private final View layout;


        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            imgArea = v.findViewById(R.id.imgArea);
            txtAreaName = v.findViewById(R.id.txtAreaName);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.item_area, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== ViewHolder Created ====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Area area = values.get(position);

        if(!area.getStrArea().equals("Unknown")) {
            Glide.with(context).load(flagsBaseUrl + countries.get(area.getStrArea()) + ".png")
                    .apply(new RequestOptions()//.override(200, 200)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(holder.imgArea);
        } else {
            holder.imgArea.setImageResource(R.drawable.ic_launcher_foreground);
        }

        holder.txtAreaName.setText(area.getStrArea());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAreaClick(area);
            }
        });

        Log.i(TAG, "===== ViewHolder Binded ====");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
