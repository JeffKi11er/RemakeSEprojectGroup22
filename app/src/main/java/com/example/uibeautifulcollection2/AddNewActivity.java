package com.example.uibeautifulcollection2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uibeautifulcollection2.check.Upload;
import com.example.uibeautifulcollection2.fragment.FragmentAddNew;
import com.example.uibeautifulcollection2.item.ItemTours;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class AddNewActivity extends AppCompatActivity implements View.OnClickListener {
    private static int CHOOSE_IMAGE = 102;
    private ImageView imgAddBtn;
//    private TextView tvAd;
//    private TextView tvLei;
//    private TextView tvFood;
    private ImageView imgFlag;
    private TextView tvBtnSeemore;
    private TextView tvSave;
    private ImageView imgBtnCal1;
    private ImageView imgBtnCal2;
    private EditText edtName;
    private EditText edtDescrition;
    private TextView tvGoBack;
    private ImageView imgGoBack;
    private Uri uriLandscape;
    private ImageView imgAddPic;
    private ProgressBar progressBarAdd;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReferenceTours;
    private String lanscapeUrl;
    final StorageReference storageReferenceTours = FirebaseStorage.getInstance().getReference("toursEvents");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_trip);
        databaseReferenceTours = FirebaseDatabase.getInstance().getReference();
        init();
    }

    private void init() {
        imgAddBtn = findViewById(R.id.img_btnaddimg);
        imgFlag = findViewById(R.id.img_flag);
        tvBtnSeemore = findViewById(R.id.tv_seemore);
        tvSave = findViewById(R.id.tv_btnsaving);
        imgBtnCal1 = findViewById(R.id.btn_cal1);
        imgBtnCal2 = findViewById(R.id.btn_cal2);
        edtName = findViewById(R.id.edt_saynametrip);
        edtDescrition = findViewById(R.id.edt_describle);
//        tvAd = findViewById(R.id.tv_btnad);
//        tvLei = findViewById(R.id.tv_btnlei);
//        tvFood = findViewById(R.id.tv_btnfood);
        imgGoBack = findViewById(R.id.img_comeback);
//        imgAddPic = findViewById(R.id.img_addimage);
        progressBarAdd = (ProgressBar)findViewById(R.id.progress_add);
        Sprite fading = new FadingCircle();
        progressBarAdd.setIndeterminateDrawable(fading);
        firebaseAuth = FirebaseAuth.getInstance();
        imgAddBtn.setOnClickListener(this);
        imgFlag.setOnClickListener(this);
        tvBtnSeemore.setOnClickListener(this);
        tvSave.setOnClickListener(this);
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
            case R.id.tv_btnsaving:
                uploadToDatabaseTours();
                break;
//            case R.id.img_addimage:
//                saveToFragment();
//                break;
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

    private void uploadToDatabaseTours() {
        if(uriLandscape!=null){
            progressBarAdd.setVisibility(View.VISIBLE);
            Calendar calendar = Calendar.getInstance();
            final StorageReference fileReference = storageReferenceTours.child("Image"+calendar.getTimeInMillis()+".png");
            imgAddBtn.setDrawingCacheEnabled(true);
            imgAddBtn.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable)imgAddBtn.getDrawable()).getBitmap();
            ByteArrayOutputStream baps = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baps);
            byte[]data = baps.toByteArray();
            UploadTask uploadTask = fileReference.putBytes(data);
            Task<Uri>uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw  task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        Log.d("AAAA","onComplete: Url: "+downloadUri.toString());
                        lanscapeUrl = downloadUri.toString();
                        ItemTours itemTours = new ItemTours(String.valueOf(downloadUri),edtName.getText().toString(),edtDescrition.getText().toString(),"Vietnam","Sep 3,2016","Nov 17,2016",2300);
                        databaseReferenceTours.child("toursPath").push().setValue(itemTours, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                progressBarAdd.setVisibility(View.GONE);
                                if(databaseError==null){
                                    Toast.makeText(AddNewActivity.this,"Successfully saved data"
                                            ,Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(AddNewActivity.this,"Fail to save in database",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(AddNewActivity.this,"Fail to getDownloadUrl", Toast.LENGTH_LONG).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddNewActivity.this,"fail to upload", Toast.LENGTH_LONG).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Toast.makeText(AddNewActivity.this,"Successfully upload",Toast.LENGTH_LONG).show();
                }
            });
        }else {
            progressBarAdd.setVisibility(View.GONE);
            Toast.makeText(this,"No file selected", Toast.LENGTH_LONG).show();
        }
    }
}
