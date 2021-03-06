package com.example.uibeautifulcollection2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uibeautifulcollection2.ItemInfoActivity;
import com.example.uibeautifulcollection2.R;
import com.example.uibeautifulcollection2.adapter.AlsoTourAdapter;
import com.example.uibeautifulcollection2.adapter.ToursAdapter;
import com.example.uibeautifulcollection2.item.ItemTours;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentTours extends Fragment implements ToursAdapter.ItemListener,AlsoTourAdapter.ItemTourListener{
    private RecyclerView rclTours;
    private RecyclerView rclHol1;
    private RecyclerView rclHol2;
    private ArrayList<ItemTours>tours;
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
        tours = new ArrayList<>();
        rclTours = (RecyclerView)v.findViewById(R.id.rcl_tours);
        rclHol1 = (RecyclerView)v.findViewById(R.id.rcl_ho1);
        rclHol2 = (RecyclerView)v.findViewById(R.id.rcl_ho2);
        adapterT = new ToursAdapter(this.getContext(),tours);
        adapterH1 = new AlsoTourAdapter(this.getContext(),tours);
        adapterH2 = new AlsoTourAdapter(this.getContext(),tours);
        rclTours.setAdapter(adapterT);
        rclHol1.setAdapter(adapterH1);
        rclHol2.setAdapter(adapterH2);
        loadData();
        adapterT.setListener(this);
        adapterH1.setListener(this);
        adapterH2.setListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rclTours.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rclHol1.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rclHol2.setLayoutManager(linearLayoutManager2);

        return v;
    }

    private void loadData() {
        mData.child("toursPath").addChildEventListener(new ChildEventListener() {//checkTours
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ItemTours tour = (ItemTours)dataSnapshot.getValue(ItemTours.class);
                tours.add(new ItemTours(tour.getImageT(),tour.getTextT(),tour.getDetailInfo(),tour.getCountry(),tour.getDateStart(),tour.getDateEnd(),tour.getParticipants()));
                adapterT.notifyDataSetChanged();
                adapterH1.notifyDataSetChanged();
                adapterH2.notifyDataSetChanged();
 //               tours.add(new ItemTours(tour.getImageT(),tour.getTextT(),tour.getDetailInfo(),tour.getCountry(),tour.getDateStart(),tour.getDateEnd(),tour.getParticipants()));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(int position) {
//        Toast.makeText(this.getContext(),tours.get(position).getTextT(),Toast.LENGTH_LONG).show();
//        Toast.makeText(this.getContext(),tours.get(position).getCountry(),Toast.LENGTH_LONG).show();
//        Toast.makeText(this.getContext(),tours.get(position).getDetailInfo(),Toast.LENGTH_LONG).show();
//        Toast.makeText(this.getContext(),tours.get(position).getCountry(),Toast.LENGTH_LONG).show();
//        Toast.makeText(this.getContext(),tours.get(position).getDateStart(),Toast.LENGTH_LONG).show();
//        Toast.makeText(this.getContext(),tours.get(position).getDateEnd(),Toast.LENGTH_LONG).show();
        String img = tours.get(position).getImageT();
        String nm = tours.get(position).getTextT();
        String info = tours.get(position).getDetailInfo();
        String nation = tours.get(position).getCountry();
        String dateS = tours.get(position).getDateStart();
        String dateE = tours.get(position).getDateEnd();
        long no = tours.get(position).getParticipants();
        ItemTours item = new ItemTours(img,nm,info,nation,dateS,dateE,no);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Item",item);
        Intent intent = new Intent(this.getContext(), ItemInfoActivity.class);
        intent.putExtra("data",bundle);
        startActivity(intent);
    }

    @Override
    public void onClickItem(int position) {
//        Toast.makeText(this.getContext(),tours.get(position).getTextT(),Toast.LENGTH_LONG).show();
        String img = tours.get(position).getImageT();
        String nm = tours.get(position).getTextT();
        String info = tours.get(position).getDetailInfo();
        String nation = tours.get(position).getCountry();
        String dateS = tours.get(position).getDateStart();
        String dateE = tours.get(position).getDateEnd();
        long no = tours.get(position).getParticipants();
        ItemTours item = new ItemTours(img,nm,info,nation,dateS,dateE,no);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Item",item);
        Intent intent = new Intent(this.getContext(), ItemInfoActivity.class);
        intent.putExtra("data",bundle);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
