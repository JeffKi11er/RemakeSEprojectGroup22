package com.example.uibeautifulcollection2.adapter;

import android.content.Context;
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

public class AlsoTourAdapter extends RecyclerView.Adapter<AlsoTourAdapter.TourHorHolder> {
    private Context context;
    private List<ItemTours>itemTours;

    public AlsoTourAdapter(Context context, List<ItemTours> itemTours) {
        this.context = context;
        this.itemTours = itemTours;
    }

    @NonNull
    @Override
    public TourHorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tourshor,parent,false);
        return new TourHorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TourHorHolder holder, int position) {
        holder.tvHolTour.setText(itemTours.get(position).getTextT());
        holder.imgHol.setImageResource(itemTours.get(position).getImageT());
    }

    @Override
    public int getItemCount() {
        return itemTours.size();
    }

    public static class TourHorHolder extends RecyclerView.ViewHolder{
        private TextView tvHolTour;
        private ImageView imgHol;
        public TourHorHolder(@NonNull View itemView) {
            super(itemView);
            tvHolTour = (TextView)itemView.findViewById(R.id.tv_nametourhor);
            imgHol = (ImageView)itemView.findViewById(R.id.img_watch);
        }
    }
}
