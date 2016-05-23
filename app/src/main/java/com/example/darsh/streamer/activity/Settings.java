package com.example.darsh.streamer.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.darsh.streamer.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Settings extends AppCompatActivity {
    private static final int PICK_IMAGE=1;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        profileImage= (ImageView) findViewById(R.id.profileImage);
    }
    public void profilePic(View v){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE){
            Uri image=data.getData();
            try {
                Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(image));
                int width=bitmap.getWidth();
                int height=bitmap.getHeight();
                int crop=(width-height)/2;
                Bitmap cropImg=Bitmap.createBitmap(bitmap);
                storeImage(cropImg);
                if(cropImg!=null) {
                    ImageView profile = (ImageView)findViewById(R.id.settingImage);
                    Bitmap profilePic=getImage();
                    if (profile != null) {
                        profile.setImageBitmap(profilePic);
                    }
                }else{
                    Log.e("No BItmap Found", "abc");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void storeImage(Bitmap cropImg) throws IOException {
        File folder=new File(Environment.getExternalStorageDirectory()+File.separator+"Streamer");
        folder.mkdirs();
        File profile=new File(folder,"profile.png");
        FileOutputStream fos=new FileOutputStream(profile);
        Bitmap profileImage = cropImg;
        profileImage.compress(Bitmap.CompressFormat.PNG,100,fos);
        fos.flush();
        fos.close();
        Toast.makeText(Settings.this, "File Stored", Toast.LENGTH_SHORT).show();
    }

    public Bitmap getImage(){
        String folder=Environment.getExternalStorageDirectory()+File.separator+"Streamer";
        Bitmap profile=BitmapFactory.decodeFile(folder+File.separator+"profile.png");
        return profile;

    }
}
