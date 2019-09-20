package com.example.testapp.views.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.testapp.R;

public class Constants {


    public enum CONTACT_STATUS{
        CONTACT("contact"),FAVOURITE("favourite"),DELETED("deleted");

        private String status;

        CONTACT_STATUS(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }}

    public static final String DATABASE_NAME="person_db";
    public static final String TABLE_NAME="person";

    public static final String NAME="name";
    public static final String CONTACT_NUMBER="contactNumber";
    public static final String IMAGE_PATH="imagePath";
    public static final String STATUS="status";




    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .apply( new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                )
                .into(view);
    }



}
