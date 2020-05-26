package com.example.uibeautifulcollection2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uibeautifulcollection2.item.ItemTours;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ItemInfoActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvCountry;
    private TextView tvDate1;
    private TextView tvDate2;
    private TextView tvParticipants;
    private ImageView img;
    private TextView tvDetail;
    private ImageView imgComeback;
    private ImageView imgAddList;
    private DatabaseReference databaseReferenceInfo;
    final StorageReference storageReferenceInfo = FirebaseStorage.getInstance().getReference("Favorites");
    static String name;
    static String image;
    static String country;
    static String detail;
    static String date1;
    static String date2;
    static long number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        databaseReferenceInfo = FirebaseDatabase.getInstance().getReference();
        tvName = (TextView)findViewById(R.id.tv_nameifo);
        tvCountry = (TextView)findViewById(R.id.tv_addressifo);
        tvDate1 = (TextView)findViewById(R.id.tv_date1);
        tvDate2 = (TextView)findViewById(R.id.tv_date2);
        tvParticipants = (TextView)findViewById(R.id.tv_participants);
        img = (ImageView)findViewById(R.id.img_showinfo);
        tvDetail = (TextView)findViewById(R.id.tv_descripinfo);
        imgComeback = (ImageView)findViewById(R.id.img_combackinfo);
        imgAddList = (ImageView)findViewById(R.id.img_addtours);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle!=null){
            ItemTours tours = (ItemTours)bundle.getSerializable("Item");
            name= tours.getTextT();
            tvName.setText(name);
            image = tours.getImageT();
            Picasso.get().load(image).into(img);
            country = tours.getCountry();
            tvCountry.setText(country);
            detail = tours.getDetailInfo();
            tvDetail.setText(detail);
            date1 = tours.getDateStart();
            tvDate1.setText(date1);
            date2 = tours.getDateEnd();
            tvDate2.setText(date2);
            number = tours.getParticipants();
            String np = String.valueOf(number);
            tvParticipants.setText(np);
        }
        imgComeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ItemInfoActivity.this,HomeActivity.class));
            }
        });
        imgAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToTheDatabase();
                imgAddList.setImageResource(R.drawable.ic_addedlistr);
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.img_addtours:
//                uploadToTheDatabase();
//                imgAddList.setImageResource(R.drawable.ic_addedlistr);
//                break;
//            case R.id.img_combackinfo:
//                finish();
//                startActivity(new Intent(ItemInfoActivity.this,HomeActivity.class));
//        }
//    }

    private void uploadToTheDatabase() {
        ItemTours tours = new ItemTours(image,name,detail,country,date1,date2,number);
        databaseReferenceInfo.child("Favorites").push().setValue(tours, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError==null){
                    Toast.makeText(ItemInfoActivity.this,"Successfully saved data",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ItemInfoActivity.this,"Fail to save in database",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
