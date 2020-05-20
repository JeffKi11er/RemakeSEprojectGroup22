package com.example.uibeautifulcollection2.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uibeautifulcollection2.R;
import com.example.uibeautifulcollection2.item.ItemTours;

import java.util.List;

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.TourHolder> {
    private Context context;
    private List<ItemTours>tours;

    public ToursAdapter(Context context, List<ItemTours> tours) {
        this.context = context;
        this.tours = tours;
    }

    @NonNull
    @Override
    public TourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_toursver,parent,false);
        return new TourHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TourHolder holder, int position) {
        holder.tvTours.setText(tours.get(position).getTextT());
        holder.imgTours.setImageResource(tours.get(position).getImageT());
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public static class TourHolder extends RecyclerView.ViewHolder{
        private TextView tvTours;
        private ImageView imgTours;
        public TourHolder(@NonNull View itemView) {
            super(itemView);
            tvTours = (TextView)itemView.findViewById(R.id.tv_nametour);
            imgTours = (ImageView)itemView.findViewById(R.id.img_show);
        }
    }
}
