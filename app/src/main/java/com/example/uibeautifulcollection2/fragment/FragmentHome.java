package com.example.uibeautifulcollection2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uibeautifulcollection2.R;
import com.example.uibeautifulcollection2.adapter.DashboardAdapter;
import com.example.uibeautifulcollection2.item.DashboardItem;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private ArrayList<DashboardItem>dashboardItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashbroad,container,false);
        dashboardItems.add(new DashboardItem(R.drawable.calendar,"Lịch"));
        dashboardItems.add(new DashboardItem(R.drawable.bug,"Báo lỗi"));
        dashboardItems.add(new DashboardItem(R.drawable.chat,"Trò Chuyện"));
        dashboardItems.add(new DashboardItem(R.drawable.status,"Tâm Trạng"));
        dashboardItems.add(new DashboardItem(R.drawable.emergency,"Khẩn Cấp"));
        dashboardItems.add(new DashboardItem(R.drawable.ticket,"Đặt Vé"));
        dashboardItems.add(new DashboardItem(R.drawable.your_events,"Sự Kiện"));
        recyclerView = (RecyclerView)v.findViewById(R.id.rcl_dashboard);
        adapter = new DashboardAdapter(this.getContext(),dashboardItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        return v;
    }
}
