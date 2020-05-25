package com.example.uibeautifulcollection2.fragment;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uibeautifulcollection2.R;

public class FragmentAddNew extends Fragment implements View.OnClickListener {
    private ImageView imgFlag;
    private TextView tvBtnSeemore;
    private TextView tvSave;
    private TextView tvCancel;
    private ImageView imgBtnCal1;
    private ImageView imgBtnCal2;
    private EditText edtName;
    private EditText edtDescrition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_addnewtour,container,false);
        imgFlag = (ImageView)v.findViewById(R.id.img_flagcountry);
        imgBtnCal1 = (ImageView)v.findViewById(R.id.btn_calendar1);
        imgBtnCal2 = (ImageView)v.findViewById(R.id.btn_calendar2);
        edtName = (EditText)v.findViewById(R.id.edt_nametrip);
        edtDescrition = (EditText)v.findViewById(R.id.edt_description);
        tvBtnSeemore = (TextView)v.findViewById(R.id.btn_seemore);
        tvSave = (TextView)v.findViewById(R.id.tv_btnsave);
        tvCancel = (TextView)v.findViewById(R.id.tv_btncancel);
        imgFlag.setOnClickListener(this);
        imgBtnCal1.setOnClickListener(this);
        imgBtnCal2.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_flagcountry:
                break;
            case R.id.btn_calendar1:
                break;
            case R.id.btn_calendar2:
                break;
            case R.id.edt_nametrip:
                break;
            case R.id.edt_description:
                break;
            case R.id.btn_seemore:
                break;
            case R.id.tv_btnsave:
                if(getArguments().getString("urlImage")!=null) {
                    String urlImage = getArguments().getString("urlImage");
                    Log.d("AAAA", "onComplete: Url: "+ urlImage);
                }
                break;
            case R.id.tv_btncancel:
                break;
        }
    }
}
