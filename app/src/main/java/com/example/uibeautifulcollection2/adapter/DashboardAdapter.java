package com.example.uibeautifulcollection2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uibeautifulcollection2.R;
import com.example.uibeautifulcollection2.item.DashboardItem;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashHolder> {
    private Context context;
    private List<DashboardItem>dashboardItems;

    public DashboardAdapter(Context context, List<DashboardItem> dashboardItems) {
        this.context = context;
        this.dashboardItems = dashboardItems;
    }

    @NonNull
    @Override
    public DashHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_dashboard,parent,false);
        return new DashHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DashHolder holder, int position) {
        holder.tvDash.setText(dashboardItems.get(position).getTextD());
        holder.imgDash.setImageResource(dashboardItems.get(position).getImageD());
    }

    @Override
    public int getItemCount() {
        return dashboardItems.size();
    }

    public static class DashHolder extends RecyclerView.ViewHolder{
        private TextView tvDash;
        private ImageView imgDash;
        public DashHolder(@NonNull View itemView) {
            super(itemView);
            tvDash = (TextView) itemView.findViewById(R.id.tv_dashboard);
            imgDash = (ImageView)itemView.findViewById(R.id.img_dashboard);
        }
    }
}
