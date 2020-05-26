package com.example.uibeautifulcollection2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uibeautifulcollection2.item.ItemTours;
import com.squareup.picasso.Picasso;

public class ItemInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvName;
    private TextView tvCountry;
    private TextView tvDate1;
    private TextView tvDate2;
    private TextView tvParticipants;
    private ImageView img;
    private TextView tvDetail;
    private ImageView imgComeback;
    private ImageView imgAddList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
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
            tvName.setText(tours.getTextT());
            Picasso.get().load(tours.getImageT()).into(img);
            tvCountry.setText(tours.getCountry());
            tvDetail.setText(tours.getDetailInfo());
            tvDate1.setText(tours.getDateStart());
            tvDate2.setText(tours.getDateEnd());
            String np = String.valueOf(tours.getParticipants());
            tvParticipants.setText(np);
        }
        imgComeback.setOnClickListener(this);
        imgAddList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_addtours:
                imgAddList.setImageResource(R.drawable.ic_addedlistr);
                break;
            case R.id.img_combackinfo:
                finish();
                startActivity(new Intent(ItemInfoActivity.this,HomeActivity.class));
        }
    }

}
