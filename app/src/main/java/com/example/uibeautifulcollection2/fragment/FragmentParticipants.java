package com.example.uibeautifulcollection2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uibeautifulcollection2.HomeActivity;
import com.example.uibeautifulcollection2.MapActivity;
import com.example.uibeautifulcollection2.R;

import org.w3c.dom.Text;

public class FragmentParticipants extends Fragment implements View.OnClickListener {
    private TextView tvTurnBack;
    private TextView tvGoToDirect;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_participants,container,false);
        tvTurnBack = (TextView)v.findViewById(R.id.tv_back);
        tvGoToDirect = (TextView)v.findViewById(R.id.tv_go);
        tvTurnBack.setOnClickListener(this);
        tvGoToDirect.setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_go:
                startActivity(new Intent(this.getContext(), MapActivity.class));
                break;
            case R.id.tv_back:
                startActivity(new Intent(this.getContext(), HomeActivity.class));
        }
    }
}
