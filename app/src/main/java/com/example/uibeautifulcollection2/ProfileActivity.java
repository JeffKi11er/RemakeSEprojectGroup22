package com.example.uibeautifulcollection2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.style.UpdateLayout;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uibeautifulcollection2.check.Upload;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvInfo;
    private ImageView imgProfile;
    private EditText edtName;
    private Button btnSave;
    private Uri uriProfileImage;
    private ProgressBar progressBarProfile;
    private static int CHOOSE_IMAGE = 101;
    private String profileImageUrl;
    private FirebaseAuth firebaseAuth;
 //   private StorageReference storageReference;
    private DatabaseReference databaseReference;
 //   private StorageTask upLoadTask;
    private TextView tvDirectMove;
    private Uri mUri;
    final StorageReference storageReference = FirebaseStorage.getInstance().getReference("profilePath");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        init();
    }

    private void init() {
        tvInfo = (TextView)findViewById(R.id.tv_cameras);
        imgProfile = (ImageView)findViewById(R.id.img_cameras);
        edtName = (EditText)findViewById(R.id.edt_name_profile);
        btnSave = (Button)findViewById(R.id.btn_save);
        progressBarProfile =(ProgressBar)findViewById(R.id.progress_profile);
        Sprite fading = new FadingCircle();
        progressBarProfile.setIndeterminateDrawable(fading);
        imgProfile.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        tvDirectMove = (TextView)findViewById(R.id.tv_btn_next);
        //storageReference = FirebaseStorage.getInstance().getReference("uploads");
        //databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        tvDirectMove.setOnClickListener(this);
        loadUserInformation();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()==null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
//    private void uploadImageToFirebaseStorage() {
//        final StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/"+System
//        .currentTimeMillis()+".jpg");
//        if(uriProfileImage!=null){
//            progressBarProfile.setVisibility(View.VISIBLE);
//            profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    progressBarProfile.setVisibility(View.GONE);
//                    profileImageRef.putFile(uriProfileImage).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                        @Override
//                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                            if (!task.isSuccessful()) {
//                                throw task.getException();
//                            }
//                            return profileImageRef.getDownloadUrl();
//                        }
//                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Uri> task) {
//                            if (task.isSuccessful()) {
//                                Uri downloadUri = task.getResult();
//                            } else {
//                                Toast.makeText(ProfileActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    progressBarProfile.setVisibility(View.GONE);
//                }
//            });
//        }
//    }
    private void saveUserInformation() {
        String displayName = edtName.getText().toString();
//        if (displayName.isEmpty()){
//            edtName.setError("Field can not be empty");
//            edtName.requestFocus();
//            return;
//        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null && profileImageUrl!=null){
            progressBarProfile.setVisibility(View.VISIBLE);
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressBarProfile.setVisibility(View.GONE);
                        Toast.makeText(ProfileActivity.this,"Profile Updated",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
//    private void uploadImageToFirebaseStorage() {
//        final StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/"+System
//        .currentTimeMillis()+".jpg");
//        if(uriProfileImage!=null) {
//            progressBarProfile.setVisibility(View.VISIBLE);
//            profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    progressBarProfile.setVisibility(View.GONE);
//                    profileImageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
//
//                }
//            })
//            .addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    progressBarProfile.setVisibility(View.GONE);
//                    Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }


    private void findImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"), CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CHOOSE_IMAGE && resultCode == RESULT_OK && data !=null && data.getData()!=null){
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imgProfile.setImageBitmap(bitmap);
                uploadToDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            mUri = data.getData();
//            Picasso.get().load(mUri).into(imgProfile);
//            uploadToDatabase();
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void loadUserInformation() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            if(user.getPhotoUrl()!=null){
                //String photoUrl = user.getPhotoUrl().toString();
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imgProfile);
            }

            if(user.getDisplayName()!=null){
                edtName.setText(user.getDisplayName());
            }
        }
    }
    private void uploadToDatabase(){
//        String displayName = edtName.getText().toString();
//        if (displayName.isEmpty()){
//            edtName.setError("Field can not be empty");
//            edtName.requestFocus();
//            return;
//        }
        if (uriProfileImage!=null){
            progressBarProfile.setVisibility(View.VISIBLE);
//            StorageReference fileReference = storageReference.child(
//                    System.currentTimeMillis()+"."+getFileExtension(mUri));
            Calendar calendar = Calendar.getInstance();
            final StorageReference fileReference = storageReference.child("Image"+calendar.getTimeInMillis()+".png");
            imgProfile.setDrawingCacheEnabled(true);
            imgProfile.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) imgProfile.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = fileReference.putBytes(data);
            //            upLoadTask = fileReference.putFile(mUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            progressBarProfile.setProgress(0);
//                        }
//                    },500);
//                    Toast.makeText(ProfileActivity.this,"Upload Successful",Toast.LENGTH_LONG).show();
//                    Upload upload = new Upload(edtName.getText().toString().trim(),
//                            taskSnapshot.getStorage().getDownloadUrl().toString());
//                    String uploadId = databaseReference.push().getKey();
//                    databaseReference.child(uploadId).setValue(upload);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                }
//            });
            Task<Uri>urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        Log.d("AAAA", "onComplete: Url: "+ downloadUri.toString());
                        profileImageUrl = downloadUri.toString();
                        Upload upload = new Upload(edtName.getText().toString(),String.valueOf(downloadUri));
                        databaseReference.child("ProfilePath").push().setValue(upload, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                progressBarProfile.setVisibility(View.GONE);
                                if(databaseError==null){
                                    Toast.makeText(ProfileActivity.this,"Successfully saved database"
                                            ,Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(ProfileActivity.this,"Fail to save in database"
                                            ,Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(ProfileActivity.this,"Fail to getDownloadUrl",Toast.LENGTH_LONG).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ProfileActivity.this,"fail to upload",Toast.LENGTH_LONG).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Toast.makeText(ProfileActivity.this,"Successfully upload",Toast.LENGTH_LONG).show();
                }
            });
        }else {
            progressBarProfile.setVisibility(View.GONE);
            Toast.makeText(this,"No file selected",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                saveUserInformation();
                break;
            case R.id.img_cameras:
                String displayName = edtName.getText().toString();
                if (displayName.isEmpty()){
                    edtName.setError("Field can not be empty");
                    edtName.requestFocus();
                    return;
                }
                findImageChooser();
                break;
            case R.id.tv_btn_next:
                finish();
                startActivity(new Intent(ProfileActivity.this,HomeActivity.class));
        }
    }
}
