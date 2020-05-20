package com.example.uibeautifulcollection2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uibeautifulcollection2.R;
import com.example.uibeautifulcollection2.adapter.ToursAdapter;
import com.example.uibeautifulcollection2.item.ItemTours;

import java.util.ArrayList;

public class FragmentTours extends Fragment {
    private RecyclerView rclTours;
    private ArrayList<ItemTours>tours = new ArrayList<>();
    private ToursAdapter adapterT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tours,container,false);
        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        rclTours = (RecyclerView)v.findViewById(R.id.rcl_tours);
        adapterT = new ToursAdapter(this.getContext(),tours);
        rclTours.setAdapter(adapterT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rclTours.setLayoutManager(linearLayoutManager);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
