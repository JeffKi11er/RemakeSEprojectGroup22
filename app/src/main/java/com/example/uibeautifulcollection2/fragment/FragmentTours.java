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
import com.example.uibeautifulcollection2.adapter.AlsoTourAdapter;
import com.example.uibeautifulcollection2.adapter.ToursAdapter;
import com.example.uibeautifulcollection2.item.ItemTours;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentTours extends Fragment {
    private RecyclerView rclTours;
    private RecyclerView rclHol1;
    private RecyclerView rclHol2;
    private ArrayList<ItemTours>tours = new ArrayList<>();
    private ToursAdapter adapterT;
    private AlsoTourAdapter adapterH1;
    private AlsoTourAdapter adapterH2;
    private DatabaseReference mData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tours,container,false);
//        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
//        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
//        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
//        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
//        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
//        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
//        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
//        tours.add(new ItemTours(R.drawable.haiphong,"Hai Phong"));
        mData = FirebaseDatabase.getInstance().getReference();
        rclTours = (RecyclerView)v.findViewById(R.id.rcl_tours);
        rclHol1 = (RecyclerView)v.findViewById(R.id.rcl_ho1);
        rclHol2 = (RecyclerView)v.findViewById(R.id.rcl_ho2);
        adapterT = new ToursAdapter(this.getContext(),tours);
        adapterH1 = new AlsoTourAdapter(this.getContext(),tours);
        adapterH2 = new AlsoTourAdapter(this.getContext(),tours);
        rclTours.setAdapter(adapterT);
        rclHol1.setAdapter(adapterH1);
        rclHol2.setAdapter(adapterH2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rclTours.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rclHol1.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rclHol2.setLayoutManager(linearLayoutManager2);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
