package com.example.uibeautifulcollection2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.uibeautifulcollection2.fragment.FragmentBooked;
import com.example.uibeautifulcollection2.fragment.FragmentHome;
import com.example.uibeautifulcollection2.fragment.FragmentParticipants;
import com.example.uibeautifulcollection2.fragment.FragmentTours;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ImageView imgButtonBar;
    private ImageView imgButtonSearch;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        imgButtonBar = (ImageView)findViewById(R.id.img_buttons);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        imgButtonSearch = (ImageView)findViewById(R.id.img_btn_search);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(botListener);
        //imgButtonBar.setOnClickListener(this);
        imgButtonSearch.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new FragmentTours()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener botListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.bottom_home:
                    selectedFragment = new FragmentHome();
                    break;
                case R.id.bottom_favorite:
                    selectedFragment = new FragmentBooked();
                    break;
                case R.id.bottom_participants:
                    selectedFragment = new FragmentParticipants();
                    break;
                case R.id.bottom_tours:
                    selectedFragment = new FragmentTours();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectedFragment).commit();
            return true;
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_search:
                break;
            case R.id.img_buttons:
                drawerLayout.openDrawer(navView);
                break;
        }
    }
}
