package com.example.darsh.streamer.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.darsh.streamer.adapters.ImageAdapter;
import com.example.darsh.streamer.activity.ImageSlider;
import com.example.darsh.streamer.R;

/**
 * Created by Darsh on 07-03-2016.
 */
public class ImageFragment extends android.support.v4.app.Fragment {
    View imageFragment;

    //Import
    private int count;
    private Bitmap[] thumbnails;
    private boolean[] thumbnailsselection;
    private String[] arrPath;
    private ImageAdapter imageAdapter;
    //Change made by me in the app


    //Import Ends
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageFragment = inflater.inflate(R.layout.image_fragment, container, false);
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media._ID;
        Cursor imagecursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
        final int image_column_index = imagecursor.getColumnIndex(MediaStore.Images.Media._ID);
        this.count = imagecursor.getCount();
        this.thumbnails = new Bitmap[this.count];
        this.arrPath = new String[this.count];
        this.thumbnailsselection = new boolean[this.count];

        for (int i = 0; i < this.count; i++) {
            imagecursor.moveToPosition(i);
            int id = imagecursor.getInt(image_column_index);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(
                    getActivity().getApplicationContext().getContentResolver(), id,
                    MediaStore.Images.Thumbnails.MICRO_KIND, null);
            arrPath[i] = imagecursor.getString(dataColumnIndex);
        }
        GridView imagegrid = (GridView) imageFragment.findViewById(R.id.imageGrid);
        imageAdapter = new ImageAdapter(count, getContext(), thumbnailsselection, thumbnails, arrPath);
        imagegrid.setAdapter(imageAdapter);
        imagecursor.close();

        final Button selectBtn = (Button) imageFragment.findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                final int len = thumbnailsselection.length;
                int cnt = 0;
                int j = 0;
                String imgName[];

                Log.d("Networking","the value of len is:"+len);

                imgName = new String[len];
                String selectImages = "";
                for (int i = 0; i < len;i++ ) {
                    if (thumbnailsselection[i]) {

                        imgName[j] = arrPath[i];
                        Log.d("Selected", imgName[j]);
                        cnt++;
                        j++;

                    } else {
                        //Log.d("File:", "File not Found");
                    }
                }
                //Log.d("Networking", "working till here");
                if (cnt == 0) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please select at least one image",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "You've selected Total " + cnt + " image(s).",
                            Toast.LENGTH_LONG).show();
                    Log.d("Selected", selectImages);
                }
                //Oldd code not using any more
                /*TCPComm tcom = new TCPComm();
                tcom.execute();
                tcom.getcnt(cnt);*/
                //new TCPComm().execute();
                //new TCPComm().getcnt(5);
                //Log.d("Networking", "working till here");

                //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //FullScreenFragment fsf = new FullScreenFragment();
                Bundle bundle = new Bundle();
                //Log.d("Image[0]",arrPath[0]);
                bundle.putInt("count", cnt);
                Log.d("cnt", String.valueOf(cnt));
                int i;
                bundle.putStringArray("img", imgName);

                /*for (i = 0; i < cnt; i++) {
                    Log.d("Transferred", imgName[i]);
                }*/
                //fsf.setArguments(bundle);
                //fragmentManager.beginTransaction().replace(R.id.containerFrame, fsf).addToBackStack(null).commit();
                Log.d("", "left ImageFragment");
                Log.d("Networking", "working till here");
                Intent intent=new Intent(getActivity(),ImageSlider.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        return imageFragment;
    }
}