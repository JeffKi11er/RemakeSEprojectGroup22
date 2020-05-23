package com.example.uibeautifulcollection2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddNewActivity extends AppCompatActivity implements View.OnClickListener {
    private static int CHOOSE_IMAGE = 102;
    private ImageView imgAddBtn;
    private TextView tvAd;
    private TextView tvLei;
    private TextView tvFood;
    private TextView tvGoBack;
    private Uri uriLandscape;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_trip);
        init();
    }

    private void init() {
        imgAddBtn = findViewById(R.id.img_btnaddimg);
        tvAd = findViewById(R.id.tv_btnad);
        tvLei = findViewById(R.id.tv_btnlei);
        tvFood = findViewById(R.id.tv_btnfood);
        tvGoBack = findViewById(R.id.tv_back);
        imgAddBtn.setOnClickListener(this);
        tvAd.setOnClickListener(this);
        tvLei.setOnClickListener(this);
        tvFood.setOnClickListener(this);
        tvGoBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_btnad:
                break;
            case R.id.tv_btnlei:
                break;
            case R.id.tv_btnfood:
                break;
            case R.id.tv_back:
                finish();
                startActivity(new Intent(this,HomeActivity.class));
                break;
            case R.id.img_btnaddimg:
                chooseLanscape();
                break;
        }
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
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
