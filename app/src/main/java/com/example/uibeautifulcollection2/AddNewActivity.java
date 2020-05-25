package com.example.uibeautifulcollection2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uibeautifulcollection2.fragment.FragmentAddNew;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddNewActivity extends AppCompatActivity implements View.OnClickListener {
    private static int CHOOSE_IMAGE = 102;
    private ImageView imgAddBtn;
//    private TextView tvAd;
//    private TextView tvLei;
//    private TextView tvFood;
    private ImageView imgFlag;
    private TextView tvBtnSeemore;
    private TextView tvSave;
    private TextView tvCancel;
    private ImageView imgBtnCal1;
    private ImageView imgBtnCal2;
    private EditText edtName;
    private EditText edtDescrition;
    private TextView tvGoBack;
    private ImageView imgGoBack;
    private Uri uriLandscape;
    private ImageView imgAddPic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_trip);
        init();
    }

    private void init() {
        imgAddBtn = findViewById(R.id.img_btnaddimg);
        imgFlag = findViewById(R.id.img_flag);
        tvBtnSeemore = findViewById(R.id.tv_seemore);
        tvSave = findViewById(R.id.tv_btnsaving);
        tvCancel = findViewById(R.id.tv_newbtncancel);
        imgBtnCal1 = findViewById(R.id.btn_cal1);
        imgBtnCal2 = findViewById(R.id.btn_cal2);
        edtName = findViewById(R.id.edt_saynametrip);
        edtDescrition = findViewById(R.id.edt_describle);
//        tvAd = findViewById(R.id.tv_btnad);
//        tvLei = findViewById(R.id.tv_btnlei);
//        tvFood = findViewById(R.id.tv_btnfood);
        imgGoBack = findViewById(R.id.img_comeback);
//        imgAddPic = findViewById(R.id.img_addimage);
        imgAddBtn.setOnClickListener(this);
        imgFlag.setOnClickListener(this);
        tvBtnSeemore.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        imgBtnCal1.setOnClickListener(this);
        imgBtnCal2.setOnClickListener(this);
//        tvAd.setOnClickListener(this);
//        tvLei.setOnClickListener(this);
//        tvFood.setOnClickListener(this);
        imgGoBack.setOnClickListener(this);
//        imgAddPic.setOnClickListener(this);
        //getSupportFragmentManager().beginTransaction().replace(R.id.container_addtour,new FragmentAddNew()).commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.tv_btnad:
//                break;
//            case R.id.tv_btnlei:
//                break;
//            case R.id.tv_btnfood:
//                break;
            case R.id.img_comeback:
                finish();
                startActivity(new Intent(this,HomeActivity.class));
                break;
            case R.id.img_btnaddimg:
                chooseLanscape();
                break;
//            case R.id.img_addimage:
//                saveToFragment();
//                break;
        }
    }

    private void saveToFragment() {
        imgAddPic.setImageResource(R.drawable.ic_addedlistr);
    }

    private void chooseLanscape() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select landscape image"),CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uriLandscape = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriLandscape);
                imgAddBtn.setImageBitmap(bitmap);
//                Bundle bundle = new Bundle();
//                bundle.putString("urlImage", uriLandscape.toString());
//                FragmentAddNew fragmentAddNew = new FragmentAddNew();
//                fragmentAddNew.setArguments(bundle);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
