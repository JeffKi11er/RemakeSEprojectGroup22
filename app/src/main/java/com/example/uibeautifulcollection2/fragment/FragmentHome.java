package com.example.uibeautifulcollection2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uibeautifulcollection2.R;
import com.example.uibeautifulcollection2.adapter.DashboardAdapter;
import com.example.uibeautifulcollection2.item.DashboardItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    private FirebaseAuth firebaseAuth;
    private ArrayList<DashboardItem>dashboardItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private TextView tvName;
    private TextView tvEmail;
    private ImageView imgFace;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashbroad,container,false);
        firebaseAuth = FirebaseAuth.getInstance();
        tvName = (TextView)v.findViewById(R.id.tv_namedas) ;
        tvEmail = (TextView)v.findViewById(R.id.tv_emaildas);
        imgFace = (ImageView) v.findViewById(R.id.header_face);
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
        loadInformation();
        return v;
    }
    private void loadInformation() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            if(user.getDisplayName()!=null){
                tvName.setText(user.getDisplayName());
            }
            if (user.getEmail()!=null){
                tvEmail.setText(user.getEmail());
            }
            if(user.getPhotoUrl()!=null){
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imgFace);
            }
        }
    }
}
