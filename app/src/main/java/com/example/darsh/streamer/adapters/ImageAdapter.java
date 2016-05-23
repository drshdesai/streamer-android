package com.example.darsh.streamer.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.darsh.streamer.R;

/**
 * Created by Darsh on 07-03-2016.
 */
public class ImageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    int count;
    Context context;
    boolean[] thumbnailsselection;
    Bitmap[] thumbnails;
    private  String[] arrPath;

    public ImageAdapter(int count, Context context, boolean[] thumbnailsselection, Bitmap[] thumbnails, String[] arrPath) {
        this.count=count;
        this.arrPath=arrPath;
        this.context=context;
        this.thumbnailsselection=thumbnailsselection;
        this.thumbnails=thumbnails;
    }

    public int getCount() {
        return this.count;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.image_grid_container, null);
            holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.itemCheckBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkbox.setId(position);
        holder.imageview.setId(position);
        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                CheckBox cb = (CheckBox) v;
                int id = cb.getId();
                if (thumbnailsselection[id]) {
                    cb.setChecked(false);
                    thumbnailsselection[id] = false;
                } else {
                    cb.setChecked(true);
                    thumbnailsselection[id] = true;
                }
            }
        });


        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.checkbox.setBackgroundResource(R.drawable.checkbox_checked);
                }
                else{
                    holder.checkbox.setBackgroundResource(R.drawable.checkbox_unchecked);
                }
            }
        });







        holder.imageview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
               int id = v.getId();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + arrPath[id]), "image/*");
                context.startActivity(intent);
            }
        });
        holder.imageview.setImageBitmap(thumbnails[position]);
        holder.checkbox.setChecked(thumbnailsselection[position]);
        holder.id = position;
        return convertView;
    }
}

class ViewHolder {
    ImageView imageview;
    CheckBox checkbox;
    int id;
}
